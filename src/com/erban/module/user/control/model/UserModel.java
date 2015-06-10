package com.erban.module.user.control.model;

import com.erban.bean.User;


public class UserModel {
    
    private User mUser;
    /**更新用户头像后新生成的url*/
    private String uploadFileUrl;
    
    public void setUser(User user){
        mUser = user;
    }
    
    public User getUser(){
        return mUser;
    }
    
    // ===============================   注册信息  =====================================
    private String mRegisterFailureMsg;
    
    public String getRegisterFailureMsg(){
        return mRegisterFailureMsg;
    }
    
    public void setRegisterFailureMsg(String msg){
        mRegisterFailureMsg = msg;
    }
    
}
