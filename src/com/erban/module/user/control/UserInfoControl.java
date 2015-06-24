package com.erban.module.user.control;

import android.text.TextUtils;

import com.erban.WifiApplication;
import com.erban.module.user.control.model.UserInfoModel;
import com.erban.util.UserUtil;
import com.yuekuapp.BaseControl;
import com.yuekuapp.annotations.AsynMethod;
import com.yuekuapp.proxy.MessageProxy;

public class UserInfoControl extends BaseControl {
    
    private UserInfoModel mModel;

    public UserInfoControl(MessageProxy mMethodCallBack) {
        super(mMethodCallBack);
    }

    @AsynMethod
    public void uploadIcon(String filePath){
        String uploadFileUrl = null;
        try {
            uploadFileUrl = WifiApplication.getInstance().getApi().uploadFile(UserUtil.getUser().getToken(),filePath);
        } catch (Exception e) {
            sendMessage("uploadIconExceptionCallBack");
            e.printStackTrace();
        }
        mModel.setUploadFileUrl(uploadFileUrl);
        if(!TextUtils.isEmpty(uploadFileUrl))
            sendMessage("uploadIconCallBack");
        else
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
}
