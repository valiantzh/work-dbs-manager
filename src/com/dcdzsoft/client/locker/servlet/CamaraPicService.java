package com.dcdzsoft.client.locker.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.fileupload.FileUploadException;

import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.config.ErrorMsgConfig;
import com.dcdzsoft.business.GServer;
import com.dcdzsoft.util.WebUtils;
import com.dcdzsoft.EduException;



@WebServlet(name = "/CamaraPicService", urlPatterns = { "/camarapicservice" })
public class CamaraPicService extends HttpServlet {
	 private static final String CONTENT_TYPE = "text/xml; charset=UTF-8";
     private static ApplicationConfig apCfg = ApplicationConfig.getInstance();

     //Process the HTTP Post request
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws
           ServletException, IOException {
       request.setCharacterEncoding("UTF-8");
       response.setContentType(CONTENT_TYPE);
       HttpSession session = request.getSession(true);
       PrintWriter out = response.getWriter();

       try{
    	   String terminalNo = WebUtils.getStringParameter("terminalNo", request);
           String uploadType = WebUtils.getStringParameter("uploadType", request);
           String signkey = WebUtils.getStringParameter("signkey", request);

           String sessionKey = GServer.getInstance().getSignKey(terminalNo);
           //非法的请求
           if(StringUtils.isEmpty(terminalNo) ||
        	  StringUtils.isEmpty(uploadType))
        	  //StringUtils.isEmpty(signkey) ||
        	  //!signkey.equalsIgnoreCase(sessionKey))
        	   throw new EduException(ErrorMsgConfig.MSG_SESSION_TIMEOUT);

           String fileName = parseFileRequest(request);

           out.println(returnResult(true,""));

       }catch(EduException e){
           out.println(returnResult(false,e.getMessage()));
       }

       out.close();
   }

   //Clean up resources
   public void destroy() {
   }

   private String parseFileRequest(HttpServletRequest request)
       throws EduException
   {
       // Create a new file upload handler
       ServletFileUpload upload = getfileUpload();
       String terminalNo = WebUtils.getStringParameter("terminalNo", request);
       String uploadType = WebUtils.getStringParameter("uploadType", request);
       String tradeWaterNo = WebUtils.getStringParameter("tradeWaterNo", request);

       String fileName = "";

       // Parse the request
       try
       {
           List items = upload.parseRequest(request);

           // Process the uploaded items
           int count = 0;
           Iterator iter = items.iterator();
           while (iter.hasNext())
           {
               FileItem item = (FileItem) iter.next();

               if (item.isFormField())
               {
                   //processFormField(item);
                   String name = item.getFieldName();
                   String value = item.getString("utf-8");
               }
               else
               {
                   String fullFileName = item.getName();
                   long fileSize = item.getSize();

                   if (fullFileName != null && item.getSize() != 0)
                   {
                       String newfileName = tradeWaterNo + ".jpg";
                       
                       String filePath = apCfg.getFullPicFilePath() + "/" + terminalNo + "/" + uploadType;

                       File savedFilePath = new File(filePath);

                       if (!savedFilePath.isDirectory())
                           savedFilePath.mkdirs();

                       fileName = filePath + "/" +  newfileName;

                       File savedFile = new File(fileName);

                       item.write(savedFile);
                   }
               }
           }
       }
       catch (FileUploadException e)
       {
           System.err.println(e.getMessage());
           e.printStackTrace();

           if(e instanceof org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException)
               throw new EduException("SizeLimitExceededException");
       }
       catch (Exception e)
       {
           System.err.println(e.getMessage());
           e.printStackTrace();

           throw new EduException("File Upload Failed");
       }

       return fileName;
   }

   private ServletFileUpload getfileUpload()
   {
       // Create a factory for disk-based file items
       DiskFileItemFactory factory = new DiskFileItemFactory();

       // Set factory constraints
       factory.setSizeThreshold(4096 * 4);
       factory.setRepository(new File(apCfg.getFullTerminalLogTmpPath()));

       // Create a new file upload handler
       ServletFileUpload upload = new ServletFileUpload(factory);

       // Set overall request size constraint
       upload.setSizeMax(1024*1000*4);

       return upload;
   }

   private String returnResult(boolean success,String result){
	   String msg = "{\"success\":" + success + ",\"msg\":\"" + result + "\"}";

       return msg;
   }
}
