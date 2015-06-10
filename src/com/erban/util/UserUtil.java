package com.erban.util;

import com.erban.SharePreferenceKey;
import com.erban.SharePreferenceWrap;
import com.erban.bean.User;

import android.text.TextUtils;


public class UserUtil {
	
	/**
	 * 用户是否登录
	 * @return
	 */
	public static boolean isUserValid(){
		SharePreferenceWrap sharePreferenceWrap = new SharePreferenceWrap(SharePreferenceKey.USER);
		return sharePreferenceWrap.getBoolean(SharePreferenceKey.USER_VALID, false);
	}
	
	
	/**
	 * 用户是否登录
	 * @return
	 */
	public static void userloginSuccess(User user){
		User.save(user);
	}
	


    /**
     *  获取当前用户
     */
	public static User getUser(){
		return User.getFromShareP();
	}
	
	/**
	 * 清除用户
	 */
	public static void clearUser(){
		SharePreferenceWrap sharePreferenceWrap = new SharePreferenceWrap(SharePreferenceKey.USER);
		sharePreferenceWrap.clear();
	}
	
	public static boolean isUserValid(User user){
		if(user!=null && !TextUtils.isEmpty(user.getToken())){
			SharePreferenceWrap sharePreferenceWrap = new SharePreferenceWrap(SharePreferenceKey.USER);
			sharePreferenceWrap.putBoolean(SharePreferenceKey.USER_VALID, true);
			sharePreferenceWrap.putString(SharePreferenceKey.TOKEN, user.getToken());
			return true;
		}
		return false;
	}
	
}
