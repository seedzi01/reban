package com.erban.module.user.control;


import com.erban.WifiApplication;
import com.erban.bean.NetResult;
import com.erban.bean.User;
import com.erban.module.user.control.model.UserModel;
import com.erban.util.Log;
import com.erban.util.UserUtil;
import com.yuekuapp.BaseControl;
import com.yuekuapp.annotations.AsynMethod;
import com.yuekuapp.proxy.MessageProxy;

public class UserControl extends BaseControl{

    private UserModel mModel;

    public UserControl(MessageProxy mMethodCallBack) {
        super(mMethodCallBack);
        mModel = new UserModel();
    }

    /**找回密码 异步*/
    @AsynMethod
    public void findPasswordAsyn(String username,String password,String verificationCode){
        try {
            NetResult result = WifiApplication.getInstance().getApi().findPassword(username, password, verificationCode);
            Log.d("111", "findPasswordAsyn()  msg = " + result.getMsg());
        } catch (Exception e) {
            sendMessage("findPasswordAsynExceptionCallBack");
            e.printStackTrace();
            return;
        } 
        sendMessage("findPasswordAsynCallBack");
    }

    /**登录 异步*/
    @AsynMethod
    public void loginAsyn(String username ,String password){
        User user = null;
        try {
            user = WifiApplication.getInstance().getApi().userLogin(username, password);
            mModel.setUser(user);
            Log.d("111", "loginAsyn()  user = " + user);
        } catch (Exception e) {
            sendMessage("loginAsynExceptionCallBack");
            e.printStackTrace();
            return;
        } 
        if(UserUtil.isUserValid(user)){
            UserUtil.userloginSuccess(user);
            sendMessage("loginAsynCallBack");
            Log.d("111", "loginAsyn()  登录成功" );
        }else{
            sendMessage("loginAsynExceptionCallBack");
             Log.d("111", "loginAsyn()  登录失败" );
        }
    }

    @AsynMethod
    public void registeAsyn(String username,String password,String verificationCode){
        try {
            NetResult result = WifiApplication.getInstance().getApi().userRegister(username, password, verificationCode);
            if("0".equals(result.getCode()))
                sendMessage("registeAsynCallBack");
            else{
                mModel.setRegisterFailureMsg(result.getMsg());
                sendMessage("registeAsynExceptionCallBack");
            }
            Log.d("111", "registeAsyn()  msg = " + result.getMsg());
        } catch (Exception e) {
            sendMessage("registeAsynExceptionCallBack");
            e.printStackTrace();
            return;
        } 
        
    }

    @AsynMethod
    public void getVerificationCodeAsyn(String telno){
        try {
            String respondCode = WifiApplication.getInstance().getApi(). getVerificationCode(telno);
            Log.d("111", "getVerificationCodeAsyn()  respondCode= " + respondCode);
        } catch (Exception e) {
                sendMessage("getVerificationCodeAsynExceptionCallBack");
                e.printStackTrace();
                return;
        } 
        sendMessage("getVerificationCodeAsynCallBack");
    }
    
    public User getUser(){
        return mModel.getUser();
    }

}
