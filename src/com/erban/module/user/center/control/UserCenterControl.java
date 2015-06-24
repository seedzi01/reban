package com.erban.module.user.center.control;

import java.util.List;

import com.erban.WifiApplication;
import com.erban.bean.NormalGoods;
import com.erban.util.UserUtil;
import com.yuekuapp.BaseControl;
import com.yuekuapp.annotations.AsynMethod;
import com.yuekuapp.proxy.MessageProxy;

public class UserCenterControl extends BaseControl {

	public UserCenterControl(MessageProxy mMethodCallBack) {
		super(mMethodCallBack);
	}

	@AsynMethod
	public void showFavList(){
		try {
			List<NormalGoods> data = WifiApplication.getInstance().getApi().showUserFav(UserUtil.getUser().getUserInfo().getUserid(),
					UserUtil.getUser().getToken());
			android.util.Log.d("111", "data = " + data.size());
			sendMessage("showFavListCallback");
		} catch (Exception e) {
			e.printStackTrace();
			sendMessage("showFavListExceptionCallback");
		}
	}
	
}
