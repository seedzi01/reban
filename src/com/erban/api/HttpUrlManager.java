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
    /**优惠个数*/
    String mysaleCount(){
    	return HOST + "/sale/mysale_count";
    }
    /**优惠*/
    String showDiscount(){
        return HOST + "/user/sales";
    }
    /**消息列表*/
    String noticeUrl(){
    	return HOST + "/notice/lists";
    }
    /*会员卡列表*/
    String memberShip() {
        return HOST + "/vip/lists";
    }
}

