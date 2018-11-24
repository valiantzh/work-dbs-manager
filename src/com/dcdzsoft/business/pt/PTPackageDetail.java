package com.dcdzsoft.business.pt;

import javax.sql.RowSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 单个包裹查询(验证) 待投递里面中 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PTPackageDetail extends ActionBean {

    public OutParamPTPackageDetail doBusiness(InParamPTPackageDetail in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamPTPackageDetail out = new OutParamPTPackageDetail();

        if(Constant.PARTNER_NAME_TAIHE.equals(ControlParam.getInstance().getSendToPartner())){
            return doTaiheTest(in);
        }
        
        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.TerminalNo)
            || StringUtils.isEmpty(in.PackageID)
            || StringUtils.isEmpty(in.PostmanID))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //////////////////////////////////////////////////////////////////////////////////
        //验证投递员是否存在
        PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
        PMPostman postman = new PMPostman();
        postman.PostmanID = in.PostmanID;
        try {
            postman = postmanDAO.find(postman);
        } catch (Exception e) {
            throw new EduException(ErrorCode.ERR_POSTMANNOTEXISTS);
        }

        //验证柜体权限
        commonDAO.checkManTerminalRight(postman, in.TerminalNo);

        ///////////////////////////////
        PTReadyPackageDAO readyPackDAO = daoFactory.getPTReadyPackageDAO();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        whereCols.add("TerminalNo", in.TerminalNo);
        whereCols.add("PackageID", in.PackageID);
        whereCols.add("PostmanID", in.PostmanID);

        RowSet rset = readyPackDAO.select(whereCols);

        if (RowSetUtils.rowsetNext(rset)) {
            out.PackageID = RowSetUtils.getStringValue(rset, "PackageID");
            out.OrderTime = RowSetUtils.getTimestampValue(rset, "OrderTime");
            out.ExpiredTime = RowSetUtils.getTimestampValue(rset, "ExpiredTime");
            out.CompanyID = RowSetUtils.getStringValue(rset, "CompanyID");
            out.BoxNo = RowSetUtils.getStringValue(rset, "BoxNo");
            out.CustomerID = RowSetUtils.getStringValue(rset, "CustomerID");
            out.CustomerName = RowSetUtils.getStringValue(rset, "CustomerName");
            out.CustomerAddress = RowSetUtils.getStringValue(rset, "CustomerAddress");
            out.CustomerMobile = RowSetUtils.getStringValue(rset, "CustomerMobile");
            out.PosPayFlag = RowSetUtils.getStringValue(rset, "PosPayFlag");
            out.PackageStatus = RowSetUtils.getStringValue(rset, "PackageStatus");
            out.Remark = RowSetUtils.getStringValue(rset, "Remark");
        }else{
            if("2".equals(postman.InputMobileFlag) || "3".equals(postman.InputMobileFlag)){
                out.PackageID = in.PackageID;
                out.CustomerMobile = "";
                out.Remark = "";
            }else{
                throw new EduException(ErrorCode.ERR_PACKAGENOTEXISTS);
            }
            
        }
        
        return out;
    }
    private OutParamPTPackageDetail doTaiheTest(InParamPTPackageDetail in) throws EduException {
        //泰和测试
        OutParamPTPackageDetail out = new OutParamPTPackageDetail();
        
        //注：DynamicCode=“GOODSINFO”：表示查询订单中商品列表信息,泰和生鲜系统中使用20161008
        if("GOODSINFO".equals(in.DynamicCode)){
            //订单中商品列表信息
            return doGetGoods(in);
        }
        
      //1.    验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.TerminalNo)
            || StringUtils.isEmpty(in.PackageID)
            || StringUtils.isEmpty(in.PostmanID))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //////////////////////////////////////////////////////////////////////////////////
        //验证投递员是否存在
        PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
        PMPostman postman = new PMPostman();
        postman.PostmanID = in.PostmanID;
        try {
            postman = postmanDAO.find(postman);
        } catch (Exception e) {
            throw new EduException(ErrorCode.ERR_POSTMANNOTEXISTS);
        }

        //验证柜体权限
        commonDAO.checkManTerminalRight(postman, in.TerminalNo);

        ///////////////////////////////
        PTReadyPackageDAO readyPackDAO = daoFactory.getPTReadyPackageDAO();
        JDBCFieldArray whereCols1 = new JDBCFieldArray();
        whereCols1.add("TerminalNo", in.TerminalNo);
        whereCols1.add("PackageID", in.PackageID);
        whereCols1.add("PostmanID", in.PostmanID);

        RowSet rset1 = readyPackDAO.select(whereCols1);

        if (RowSetUtils.rowsetNext(rset1)) {
            out.PackageID = RowSetUtils.getStringValue(rset1, "PackageID");
            out.OrderTime = RowSetUtils.getTimestampValue(rset1, "OrderTime");
            out.ExpiredTime = RowSetUtils.getTimestampValue(rset1, "ExpiredTime");
            out.CompanyID = RowSetUtils.getStringValue(rset1, "CompanyID");
            out.BoxNo = RowSetUtils.getStringValue(rset1, "BoxNo");
            out.CustomerID = RowSetUtils.getStringValue(rset1, "CustomerID");
            out.CustomerName = RowSetUtils.getStringValue(rset1, "CustomerName");
            out.CustomerAddress = RowSetUtils.getStringValue(rset1, "CustomerAddress");
            out.CustomerMobile = RowSetUtils.getStringValue(rset1, "CustomerMobile");
            out.PosPayFlag = RowSetUtils.getStringValue(rset1, "PosPayFlag");
            out.PackageStatus = RowSetUtils.getStringValue(rset1, "PackageStatus");
            out.Remark = RowSetUtils.getStringValue(rset1, "Remark");
        }else{
            JDBCFieldArray whereCols = new JDBCFieldArray();
            whereCols.add("TerminalNo", in.TerminalNo);
            whereCols.add("PackageID", in.PackageID);
            whereCols.add("PostmanID", in.PostmanID);
            
            PTInBoxPackageDAO inboxPackDAO = daoFactory.getPTInBoxPackageDAO();
            RowSet rset = inboxPackDAO.select(whereCols);

            if (RowSetUtils.rowsetNext(rset)) {
                out.PackageID = RowSetUtils.getStringValue(rset, "PackageID");
                out.OrderTime = RowSetUtils.getTimestampValue(rset, "StoredTime");
                out.ExpiredTime = RowSetUtils.getTimestampValue(rset, "ExpiredTime");
                out.CompanyID = RowSetUtils.getStringValue(rset, "CompanyID");
                out.BoxNo = RowSetUtils.getStringValue(rset, "BoxNo");
                out.CustomerID = RowSetUtils.getStringValue(rset, "CustomerID");
                out.CustomerName = RowSetUtils.getStringValue(rset, "CustomerName");
                out.CustomerAddress = RowSetUtils.getStringValue(rset, "CustomerAddress");
                out.CustomerMobile = RowSetUtils.getStringValue(rset, "CustomerMobile");
                out.PosPayFlag = RowSetUtils.getStringValue(rset, "PosPayFlag");
                out.PackageStatus = "0";//RowSetUtils.getStringValue(rset, "PackageStatus");
                out.Remark = RowSetUtils.getStringValue(rset, "Remark");
            }else{
                throw new EduException(ErrorCode.ERR_PACKAGENOTEXISTS);
            }
        }
        //泰和测试用20160801
        if(out.PackageID.startsWith("25")){
            out.BoxType = "2";
        }else if(out.PackageID.startsWith("99")){
            out.BoxType = "";
        }else{
            out.BoxType = "7";
        }
        System.out.println("OutParamPTPackageDetail:"+out.PackageID+","+out.CustomerMobile+","+out.BoxType);
        return out;
    }
    /**
     * 商品信息查询：用于测试
     * @param in
     * @return
     * @throws EduException
     */
    private OutParamPTPackageDetail doGetGoods(InParamPTPackageDetail in)
            throws EduException {
        
        OutParamPTPackageDetail out = new OutParamPTPackageDetail();
        if (StringUtils.isEmpty(in.TerminalNo)
                || StringUtils.isEmpty(in.PackageID)
                //|| StringUtils.isEmpty(in.PostmanID)
                )
                throw new EduException(ErrorCode.ERR_PARMERR);
        
        
        JDBCFieldArray whereCols = new JDBCFieldArray();
        whereCols.add("TerminalNo", in.TerminalNo);
        //whereCols.add("PackageID", in.PackageID);
        whereCols.add("PackageID", " LIKE ", in.PackageID + "%");
        whereCols.add("PackageStatus", SysDict.PACKAGE_STATUS_OUTNORMAL);
        
        PTDeliverHistoryDAO historyPackDAO = daoFactory.getPTDeliverHistoryDAO();
        RowSet rset1 = historyPackDAO.select(whereCols);

        if (RowSetUtils.rowsetNext(rset1)) {
            out.PackageID = RowSetUtils.getStringValue(rset1, "PackageID");
            out.OrderTime = RowSetUtils.getTimestampValue(rset1, "StoredTime");
            out.ExpiredTime = RowSetUtils.getTimestampValue(rset1, "ExpiredTime");
            out.CompanyID = RowSetUtils.getStringValue(rset1, "CompanyID");
            out.BoxNo = RowSetUtils.getStringValue(rset1, "BoxNo");
            out.CustomerID = RowSetUtils.getStringValue(rset1, "CustomerID");
            out.CustomerName = RowSetUtils.getStringValue(rset1, "CustomerName");
            out.CustomerAddress = RowSetUtils.getStringValue(rset1, "CustomerAddress");
            out.CustomerMobile = RowSetUtils.getStringValue(rset1, "CustomerMobile");
            out.PosPayFlag = RowSetUtils.getStringValue(rset1, "PosPayFlag");
            out.PackageStatus = RowSetUtils.getStringValue(rset1, "PackageStatus");
            
            if(out.PackageID.startsWith("25")){
                out.BoxType = "2";
            }else if(out.PackageID.startsWith("99")){
                out.BoxType = "";
            }else{
                out.BoxType = "7";
            }
            //商品信息：
            JSONArray goodsList = new JSONArray();
            
            JSONObject goods3 = new JSONObject();
            goods3.put("reslut", "0");
            goods3.put("terminalno", in.TerminalNo);
            goods3.put("customermobile", out.CustomerMobile);
            goods3.put("customerid", out.CustomerID);
            goods3.put("packageid", in.PackageID);
            goods3.put("order_id", RandUtils.generateNumber(6));
            goods3.put("rec_id", in.PackageID+"003");
            goods3.put("boxno", out.BoxNo);
            goods3.put("boxtype", out.BoxType);
            goods3.put("goods_name", "商品3");
            goods3.put("goods_price", "4.00");
            goods3.put("goods_value", "5");
            goods3.put("riqi", "2016/09/29");
            
            JSONObject goods2 = new JSONObject();
            goods2.put("reslut", "0");
            goods2.put("terminalno", in.TerminalNo);
            goods2.put("customermobile", out.CustomerMobile);
            goods2.put("customerid", out.CustomerID);
            goods2.put("packageid", in.PackageID);
            goods2.put("order_id", RandUtils.generateNumber(6));
            goods2.put("rec_id", in.PackageID+"002");
            goods2.put("boxno", out.BoxNo);
            goods2.put("boxtype", out.BoxType);
            goods2.put("goods_name", "商品2");
            goods2.put("goods_price", "2.00");
            goods2.put("goods_value", "5");
            goods2.put("riqi", "2016/09/29");
            
            JSONObject goods1 = new JSONObject();
            goods1.put("reslut", "1");
            goods1.put("terminalno", in.TerminalNo);
            goods1.put("customermobile", out.CustomerMobile);
            goods1.put("customerid", out.CustomerID);
            goods1.put("packageid", in.PackageID);
            goods1.put("order_id", RandUtils.generateNumber(6));
            goods1.put("rec_id",  in.PackageID+"001");
            goods1.put("boxno", out.BoxNo);
            goods1.put("boxtype", out.BoxType);
            goods1.put("goods_name", "商品1");
            goods1.put("goods_price", "2.00");
            goods1.put("goods_value", "10");
            goods1.put("riqi", "2016/09/29");
            goodsList.add(goods1);
            goodsList.add(goods2);
            goodsList.add(goods3);
            
            out.Remark = goodsList.toString();//
        }else{
            throw new EduException(ErrorCode.ERR_PACKAGENOTEXISTS);
        }
        return out;
    }
}
