package com.erban.module.user.control;

import android.text.TextUtils;

import com.erban.WifiApplication;
import com.erban.bean.NetResult;
import com.erban.module.user.control.model.UserInfoModel;
import com.erban.util.UserUtil;
import com.yuekuapp.BaseControl;
import com.yuekuapp.annotations.AsynMethod;
import com.yuekuapp.proxy.MessageProxy;

public class UserInfoControl extends BaseControl {
    
    private UserInfoModel mModel;

    public UserInfoControl(MessageProxy mMethodCallBack) {
        super(mMethodCallBack);
        mModel = new UserInfoModel();
    }
    
    public UserInfoModel getModel(){
    	return mModel;
    }

    @AsynMethod
    public void uploadIcon(String filePath){
        String uploadFileUrl = null;
        try {
            uploadFileUrl = WifiApplication.getInstance().getApi().uploadFile(UserUtil.getUser().getToken(),filePath);
            android.util.Log.d("111", "uploadFileUrl =" + uploadFileUrl);
        } catch (Exception e) {
            sendMessage("uploadIconExceptionCallBack");
            e.printStackTrace();
        }
        mModel.setUploadFileUrl(uploadFileUrl);
        if(!TextUtils.isEmpty(uploadFileUrl)){
        	 try {
				WifiApplication.getInstance().getApi().updateUserInfo(UserUtil.getUser().getToken(),
				         UserUtil.getUser().getUserInfo().getUserid(),"avatar", uploadFileUrl);
			} catch (Exception e) {
				sendMessage("uploadIconExceptionCallBack");
				e.printStackTrace();
				return;
			}
            sendMessage("uploadIconCallBack");
        }else
            sendMessage("uploadIconExceptionCallBack");
    }

    @AsynMethod
    public void updateUserInfo(String key,String value){
        try {
            WifiApplication.getInstance().getApi().updateUserInfo(UserUtil.getUser().getToken(),
                    UserUtil.getUser().getUserInfo().getUserid(),key, value);
            sendMessage("updateUserInfoCallBack");
        } catch (Exception e) {
            e.printStackTrace();
            sendMessage("updateUserInfoExceptionCallBack");
        }
    }
    
    @AsynMethod
	public void loginOutAsyn(){
		try {
			NetResult netResult = WifiApplication.getInstance().getApi().loginOut(UserUtil.getUser().getToken());
			if(netResult.getCode().trim().equals("0")){
				UserUtil.clearUser();
				sendMessage("loginOutAsynCallBack");
			}else{
				sendMessage("loginOutAsynExceptionCallBack");
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendMessage("loginOutAsynExceptionCallBack");
		} 				
	}
}
