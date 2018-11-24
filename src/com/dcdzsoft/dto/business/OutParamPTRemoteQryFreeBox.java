package com.dcdzsoft.dto.business;

/**
* 远程查询可用箱门
*/

public class OutParamPTRemoteQryFreeBox implements java.io.Serializable
{
	public int SuperSmallBoxNum; //超小箱数量
	public int SmallBoxNum; //小箱数量
	public int MedialNum; //中箱数量
	public int LargeNum; //大箱数量
	public int MedialBNum; //中箱B数量
	public int SuperLargeNum; //超大箱数量
	public int FreshNum; //生鲜箱数量
	public int FastFoodNum; //快餐柜数量
	public int ShowingNum; //展示箱数量
	public String Remark = ""; //备注

	public int getSmallBoxNum()
	{
		return SmallBoxNum;
	}
	public void setSmallBoxNum(int SmallBoxNum)
	{
		this.SmallBoxNum = SmallBoxNum;
	}

	public int getMedialNum()
	{
		return MedialNum;
	}
	public void setMedialNum(int MedialNum)
	{
		this.MedialNum = MedialNum;
	}

	public int getLargeNum()
	{
		return LargeNum;
	}
	public void setLargeNum(int LargeNum)
	{
		this.LargeNum = LargeNum;
	}

	public int getMedialBNum()
	{
		return MedialBNum;
	}
	public void setMedialBNum(int MedialBNum)
	{
		this.MedialBNum = MedialBNum;
	}

	public int getSuperLargeNum()
	{
		return SuperLargeNum;
	}
	public void setSuperLargeNum(int SuperLargeNum)
	{
		this.SuperLargeNum = SuperLargeNum;
	}

	public int getFreshNum()
	{
		return FreshNum;
	}
	public void setFreshNum(int FreshNum)
	{
		this.FreshNum = FreshNum;
	}

	public int getFastFoodNum()
	{
		return FastFoodNum;
	}
	public void setFastFoodNum(int FastFoodNum)
	{
		this.FastFoodNum = FastFoodNum;
	}

	public int getShowingNum()
	{
		return ShowingNum;
	}
	public void setShowingNum(int ShowingNum)
	{
		this.ShowingNum = ShowingNum;
	}

	public int getSuperSmallBoxNum()
	{
		return SuperSmallBoxNum;
	}
	public void setSuperSmallBoxNum(int SuperSmallBoxNum)
	{
		this.SuperSmallBoxNum = SuperSmallBoxNum;
	}

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

}