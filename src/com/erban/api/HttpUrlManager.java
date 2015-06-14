package com.erban.api;

public class HttpUrlManager {
	
	private static String HOST = "http://101.200.176.151/client/signup"; 
	
	// ========================== 登录与注册 =================================
    /**注册*/
    String getUserRegisterUrl(){
        return HOST + "/register";
    }
    /**登录*/
    String getUserLoginUrl(){
        return HOST + "/login";
    }
    /**登出*/
    String getUserLoginOutUrl(){
        return HOST + "/logout";
    }
    /**短信验证*/
    String getVerificationCodeUrl(){
        return HOST + "/randcode";
    }
    /**找回密碼*/
    String getFindPwdUrl(){
        return HOST + "/resetpwd";
    }
}

