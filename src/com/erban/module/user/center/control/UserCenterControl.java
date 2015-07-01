package com.erban.module.user.center.control;

import java.util.List;

import com.erban.WifiApplication;
import com.erban.bean.MemberShip;
import com.erban.bean.Msg;
import com.erban.bean.NormalGoods;
import com.erban.module.user.center.model.UserCenterModel;
import com.erban.util.UserUtil;
import com.yuekuapp.BaseControl;
import com.yuekuapp.annotations.AsynMethod;
import com.yuekuapp.proxy.MessageProxy;

public class UserCenterControl extends BaseControl {
	
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
	                "1",UserUtil.getUser().getToken());
	        mModel.setMemberShip(data);
	        sendMessage("showMemberShipCallback");
	    } catch (Exception e) {
	        e.printStackTrace();
	        sendMessage("showMemberShipExceptionCallback");
	    }
	}
}
