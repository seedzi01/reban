package com.erban.module.user.control.model;

public class UserInfoModel {
    
    /**更新用户头像后新生成的url*/
    private String uploadFileUrl;
    
    public String getUploadFileUrl() {
        return uploadFileUrl;
    }

    public void setUploadFileUrl(String uploadFileUrl) {
        this.uploadFileUrl = uploadFileUrl;
    }

}
