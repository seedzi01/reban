package com.erban.module.user.control;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.android.dx.util.FileUtils;
import com.erban.util.Security;
import com.yuekuapp.BaseControl;
import com.yuekuapp.proxy.MessageProxy;

public class UserInfoControl extends BaseControl {

    public UserInfoControl(MessageProxy mMethodCallBack) {
        super(mMethodCallBack);
    }


}
