package com.erban.module.user.center.control;

import java.util.List;

import com.erban.WifiApplication;
import com.erban.api.exception.XiaoMeiCredentialsException;
import com.erban.api.exception.XiaoMeiIOException;
import com.erban.api.exception.XiaoMeiJSONException;
import com.erban.api.exception.XiaoMeiOtherException;
import com.erban.bean.MemberShip;
import com.erban.bean.Msg;
import com.erban.bean.NormalGoods;
import com.erban.bean.SaleCount;
import com.erban.module.user.center.model.UserCenterModel;
import com.erban.util.UserUtil;
import com.yuekuapp.BaseControl;
import com.yuekuapp.annotations.AsynMethod;
import com.yuekuapp.proxy.MessageProxy;

public class UserCenterControl extends BaseControl {
	
	private final String PER_PAGE_SIZE = "200";
	
	private UserCenterModel mModel;

	public UserCenterControl(MessageProxy mMethodCallBack) {
		super(mMethodCallBack);
		mModel = new UserCenterModel();
	}

	public UserCenterModel getModel(){
		return mModel;
	}
	
	@AsynMethod
	public void showFavList(){
		try {
			List<NormalGoods> data = WifiApplication.getInstance().getApi().showUserFav(UserUtil.getUser().getUserInfo().getUserid(),
					UserUtil.getUser().getToken());
			mModel.setGoodsList(data);
			sendMessage("showFavListCallback");
		} catch (Exception e) {
			e.printStackTrace();
			sendMessage("showFavListExceptionCallback");
		}
	}
	
	@AsynMethod
	public void showUserNotice(){
		try {
			List<Msg> data = WifiApplication.getInstance().getApi().showUserNotice(UserUtil.getUser().getUserInfo().getUserid(),
					UserUtil.getUser().getToken());
			mModel.setMsgList(data);
			sendMessage("showUserNoticeCallback");
		} catch (Exception e) {
			e.printStackTrace();
			sendMessage("showUserNoticeExceptionCallback");
		} 
	}
	
	@AsynMethod
	public void showMemeberShipAsyn() {
	    try {
	        // TODO replace 1 with UserUtil.getUser().getUserInfo().getUserid()
	        List<MemberShip> data = WifiApplication.getInstance().getApi().showMemberShip(
	                UserUtil.getUser().getUserInfo().getUserid(),UserUtil.getUser().getToken());
	        mModel.setMemberShip(data);
	        sendMessage("showMemberShipCallback");
	    } catch (Exception e) {
	        e.printStackTrace();
	        sendMessage("showMemberShipExceptionCallback");
	    }
	}
	
	@AsynMethod
	public void getSaleCountAsyn(){
		try {
			SaleCount saleCount = WifiApplication.getInstance().getApi().getSaleCount(UserUtil.getUser().getUserInfo().getUserid()
					,UserUtil.getUser().getToken());
			mModel.setSaleCount(saleCount);
			sendMessage("getSaleCountAsynCallback");
		} catch (Exception e) {
			e.printStackTrace();
			sendMessage("getSaleCountAsynExceptionCallback");
		}
	}
	
	/**
	 * 优惠列表
	 */
	@AsynMethod
	public void getMySaleAsyn(String classify,String curpage){
		try {
			List<NormalGoods> list = WifiApplication.getInstance().getApi().getMySale(UserUtil.getUser().getUserInfo().getUserid(), 
					UserUtil.getUser().getToken(), 
					classify, 
					"1", 
					PER_PAGE_SIZE);
			mModel.setGoodsList(list);
			sendMessage("showFavListCallback");
		} catch (Exception e) {
			e.printStackTrace();
			sendMessage("showFavListExceptionCallback");
		}
	}
	/**
	 * 使用优惠卡
	 */
	@AsynMethod
	public void useYouHuiCard(String id){
		try {
			WifiApplication
					.getInstance()
					.getApi()
					.useYouHuiCard(
							UserUtil.getUser().getUserInfo().getUserid(),
							UserUtil.getUser().getToken(), id);
			sendMessage("useYouHuiCardCallback");
		} catch (Exception e) {
			e.printStackTrace();
			sendMessage("useYouHuiCardExceptionCallback");
		} 
	}
}
