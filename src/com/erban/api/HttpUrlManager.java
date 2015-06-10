package com.erban.api;

public class HttpUrlManager {
	
	private static String HOST = "http://101.200.176.151/client"; 
	
	// ========================== 登录与注册 =================================
    /**注册*/
    String getUserRegisterUrl(){
        return HOST + "/signup.php";
    }
    /**登录*/
    String getUserLoginUrl(){
        return HOST + "/login.php";
    }
    /**登出*/
    String getUserLoginOutUrl(){
        return HOST + "/logout.php";
    }
    /**短信验证*/
    String getVerificationCodeUrl(){
        return HOST + "/randcode.php";
    }
    /**找回密碼*/
    String getFindPwdUrl(){
        return HOST + "/resetpwd.php";
    }
}

