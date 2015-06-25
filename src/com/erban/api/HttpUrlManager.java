package com.erban.api;

public class HttpUrlManager {
	
	private static String HOST = "http://101.200.176.151/client"; 
	
	// ========================== 登录与注册 =================================
    /**注册*/
    String getUserRegisterUrl(){
        return HOST + "/signup/register";
    }
    /**登录*/
    String getUserLoginUrl(){
        return HOST + "/signup/login";
    }
    /**登出*/
    String getUserLoginOutUrl(){
        return HOST + "/signup/logout";
    }
    /**短信验证*/
    String getVerificationCodeUrl(){
        return HOST + "/signup/randcode";
    }
    /**找回密碼*/
    String getFindPwdUrl(){
        return HOST + "/signup/resetpwd";
    }
    
 // ========================== 用户信息 =================================
    /**获取用户信息*/
    String userInfosUrl(){
        return HOST + "/user/infos";
    }
    
    /**更新用户信息*/
    String userUpdateUrl(){
        return HOST + "/user/update";
    }
    
    /**更新用户头像*/
    String upoadAvatarUrl(){
        return HOST + "/user/uploadAvatar";
    }
 // ========================== 个人中心 =================================
    /**收藏列表*/
    String favListsUrl(){
    	return HOST + "/fav/lists";
    }
    /**收藏添加*/
    String addFavUrl(){
    	return HOST + "/fav/add";
    }
    /**收藏删除*/
    String delFavUrl(){
    	return HOST + "/fav/del";
    }
    /*
    请求Host:	http://101.200.176.151/client/notice/

    	1.	接口地址:	lists
    		接口功能:	获取个人消息列表
    		接口方式:	Get
    		请求参数:	userid
    					token
    		认证方式:	Token
    		接口返回:	对应用户消息列表
    		接口返回示例:
    	[
    		{
    			"from" : "系统",
    			"content" : "今日系统维护,造成的不变敬请谅解",
    			"uptime" : "2015年3月11日"
    		},
    		{
    			"from" : "系统",
    			"content" : "今日系统维护,造成的不变敬请谅解",
    			"uptime" : "2015年3月11日"
    		}
    	]*/
    /**消息列表*/
    String noticeUrl(){
    	return HOST + "/notice/lists";
    }
}

