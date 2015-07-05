package com.erban.module.user.center.model;

import java.util.List;

import com.erban.bean.MemberShip;
import com.erban.bean.Msg;
import com.erban.bean.NormalGoods;
import com.erban.bean.SaleCount;

public class UserCenterModel {
	
	private List<NormalGoods> mGoodsList;
	
	public void setGoodsList(List<NormalGoods> list){
		mGoodsList = list;
	}
	
	public List<NormalGoods> getGoodsList(){
		return mGoodsList;
	}

	private List<Msg> mMsgList;

	public void setMsgList(List<Msg> list){
		mMsgList = list;
	}
	
	public List<Msg> getMsgList(){
		return mMsgList;
	}

	private List<MemberShip> mMemberShips;

	public void setMemberShip(List<MemberShip> list) {
	    mMemberShips = list;
	}

	public List<MemberShip> getMemberShips() {
        return mMemberShips;
    }
	
	private SaleCount mSaleCount;
	
	public void setSaleCount(SaleCount saleCount){
		mSaleCount = saleCount;
	}
	
	public SaleCount getSaleCount(){
		return mSaleCount;
	}

}
