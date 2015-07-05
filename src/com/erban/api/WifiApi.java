package com.erban.api;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.provider.MediaStore.Audio.Genres.Members;
import android.text.TextUtils;
import android.util.Log;

import com.erban.api.builder.MemberShipBuilder;
import com.erban.api.builder.MsgBuilder;
import com.erban.api.builder.NetResultBuilder;
import com.erban.api.builder.NormalGoodsBuilder;
import com.erban.api.builder.SaleCountBuilder;
import com.erban.api.builder.UploadFIleBuilder;
import com.erban.api.builder.UserLoginBuilder;
import com.erban.api.builder.UserRegisterBuilder;
import com.erban.api.exception.XiaoMeiCredentialsException;
import com.erban.api.exception.XiaoMeiIOException;
import com.erban.api.exception.XiaoMeiJSONException;
import com.erban.api.exception.XiaoMeiOtherException;
import com.erban.api.http.AbstractHttpApi;
import com.erban.api.http.HttpApi;
import com.erban.api.http.HttpApiWithSession;
import com.erban.bean.MemberShip;
import com.erban.bean.Msg;
import com.erban.bean.NetResult;
import com.erban.bean.NormalGoods;
import com.erban.bean.SaleCount;
import com.erban.bean.User;
import com.erban.module.user.LoginAndRegisterActivity;
import com.erban.util.FileUtils;
import com.erban.util.Security;

/**
 * Created by huzhi on 15-2-17.
 */
public class WifiApi {

    private HttpApi mHttpApi;
    private HttpUrlManager urlManager;

    public WifiApi(String clientVersion, Context mContext) {
        mHttpApi = new HttpApiWithSession(AbstractHttpApi.createHttpClient(),
                clientVersion);
        urlManager = new HttpUrlManager();
    }
    // ========================================================================================
    // 用户注册与登录(NET)
    // ========================================================================================
    
    /**
     * 注册
     */
    public NetResult userRegister(String telno,String passwd,String rdcode) 
            throws XiaoMeiCredentialsException,XiaoMeiIOException,XiaoMeiJSONException ,XiaoMeiOtherException {
        BasicNameValuePair[] values = {new BasicNameValuePair("telno", telno) ,
                new BasicNameValuePair("pwd", passwd),
                new BasicNameValuePair("randcode", rdcode),
                new BasicNameValuePair("uptime", String.valueOf(System.currentTimeMillis()/1000))} ; 
        HttpPost httpPost = mHttpApi.createHttpPost(urlManager.getUserRegisterUrl(),
                values[0],
                values[1],
                values[2],
                values[3],
                new BasicNameValuePair("fig", Security.get32MD5Str(values)));
        return  mHttpApi.doHttpRequestObject(httpPost, new UserRegisterBuilder());
    }
    
    /**
     * 登录
     */
    public User userLogin(String userid,String passwd) 
            throws XiaoMeiCredentialsException,XiaoMeiIOException,XiaoMeiJSONException ,XiaoMeiOtherException {
        BasicNameValuePair[] values = {new BasicNameValuePair("telno", userid) ,
                new BasicNameValuePair("pwd", passwd),
                new BasicNameValuePair("uptime", String.valueOf(System.currentTimeMillis()/1000))} ; 
        HttpPost httpPost = mHttpApi.createHttpPost(urlManager.getUserLoginUrl(),
                values[0],
                values[1],
                values[2],
                new BasicNameValuePair("fig", Security.get32MD5Str(values)));
        return  mHttpApi.doHttpRequestObject(httpPost, new UserLoginBuilder());
    }
    
    /**
     * 登出
     */
    public NetResult loginOut(String token)
            throws XiaoMeiCredentialsException,XiaoMeiIOException,XiaoMeiJSONException ,XiaoMeiOtherException {
        BasicNameValuePair[] values = {
                new BasicNameValuePair("token", token),
                new BasicNameValuePair("uptime", String.valueOf(System.currentTimeMillis()/1000))} ; 
        HttpPost httpPost = mHttpApi.createHttpPost(urlManager.getUserLoginOutUrl(),
                values[0],
                values[1],
                new BasicNameValuePair("fig", Security.get32MD5Str(values)));
        return mHttpApi.doHttpRequestObject(httpPost, new NetResultBuilder());
    }
    
    /**
     * 获取验证码
     */
    public String getVerificationCode(String telno)
            throws XiaoMeiCredentialsException,XiaoMeiIOException,XiaoMeiJSONException ,XiaoMeiOtherException {
                BasicNameValuePair[] values = {new BasicNameValuePair("telno", telno) ,
                new BasicNameValuePair("uptime", String.valueOf(System.currentTimeMillis()/1000))} ; 
                HttpPost httpPost = mHttpApi.createHttpPost(urlManager.getVerificationCodeUrl(),
                        values[0],
                        values[1],
                        new BasicNameValuePair("fig", Security.get32MD5Str(values)));
                String responseCode =  mHttpApi.doHttpRequestString(httpPost);
                return responseCode;
    }
    
    /**
     *  找回密码
     */
    public NetResult findPassword(String userid,String passwd,String rdcode)
        throws XiaoMeiCredentialsException,XiaoMeiIOException,XiaoMeiJSONException ,XiaoMeiOtherException {
        BasicNameValuePair[] values = {new BasicNameValuePair("telno", userid) ,
                new BasicNameValuePair("pwd", passwd),
                new BasicNameValuePair("randcode", rdcode),
                new BasicNameValuePair("uptime", String.valueOf(System.currentTimeMillis()/1000))} ; 
        HttpPost httpPost = mHttpApi.createHttpPost(urlManager.getFindPwdUrl(),
                values[0],
                values[1],
                values[2],
                values[3],
                new BasicNameValuePair("fig", Security.get32MD5Str(values)));
        return  mHttpApi.doHttpRequestObject(httpPost, new NetResultBuilder());
    }
    // ========================================================================================
    // 用户资料(NET)
    // ========================================================================================
    /**
     * 获取用户信息
     * @throws Exception 
     */
    public User fetchUserInfo(String token,String userId) throws Exception{
        BasicNameValuePair[] values = {
                new BasicNameValuePair("userid", userId),
                new BasicNameValuePair("token", token),
                new BasicNameValuePair("uptime", String.valueOf(System.currentTimeMillis()/1000))};
        HttpGet httpGet = mHttpApi.createHttpGet(urlManager.userInfosUrl(),
                values[0],
                values[1],
                values[2],
                new BasicNameValuePair("fig", Security.get32MD5Str(values)));
        return  mHttpApi.doHttpRequestObject(httpGet, new UserLoginBuilder());
    }
    /**
     * 更新用户头像
     * @throws Exception 
     */
    public String uploadFile(String token,String filePath) throws Exception{
        BasicNameValuePair[] values = {
                new BasicNameValuePair("token", token),
                new BasicNameValuePair("uptime", String.valueOf(System.currentTimeMillis()/1000))};
        Map<String,String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("uptime", String.valueOf(System.currentTimeMillis()/1000));
        params.put("fig",  Security.get32MD5Str(values));
        String fileUrl = FileUtils.uploadSubmit(urlManager.upoadAvatarUrl(),params,new File(filePath));
        return fileUrl;
    }
    /**
     * 更新用户信息
     * @throws Exception 
     */
    public NetResult updateUserInfo(String token,String userid,String key,String value) throws Exception{
        BasicNameValuePair[] values = {
                new BasicNameValuePair("token", token),
                new BasicNameValuePair("userid", userid),
                new BasicNameValuePair(key, value),
                new BasicNameValuePair("uptime", String.valueOf(System.currentTimeMillis()/1000))};
        HttpGet httpGet = mHttpApi.createHttpGet(urlManager.userUpdateUrl(),
                values[0],
                values[1],
                values[2],
                new BasicNameValuePair("fig", Security.get32MD5Str(values)));
        return  mHttpApi.doHttpRequestObject(httpGet, new NetResultBuilder());
    }
    
    // ========================================================================================
    // 个人中心(NET)
    // ========================================================================================   
    /**
     * 收藏列表
     */
	public List<NormalGoods> showUserFav(String userid,String token)
			throws XiaoMeiCredentialsException,XiaoMeiIOException,XiaoMeiJSONException ,XiaoMeiOtherException {
				BasicNameValuePair[] values = {
				        new BasicNameValuePair("userid", userid) ,
						new BasicNameValuePair("token", token) ,
						new BasicNameValuePair("uptime", String.valueOf(System.currentTimeMillis()/1000)),
				} ; 
				HttpGet httpGet = mHttpApi.createHttpGet(urlManager.favListsUrl(),
						values[0],
						values[1],
						values[2],
						new BasicNameValuePair("fig", Security.get32MD5Str(values)));
				 return mHttpApi.doHttpRequestObject(httpGet, new NormalGoodsBuilder());
	}
	/**
     * 添加收藏
     */
	public User addUserFav(String userid,String token)
			throws XiaoMeiCredentialsException,XiaoMeiIOException,XiaoMeiJSONException ,XiaoMeiOtherException {
				BasicNameValuePair[] values = {
				        new BasicNameValuePair("userid", userid) ,
						new BasicNameValuePair("token", token) ,
						new BasicNameValuePair("uptime", String.valueOf(System.currentTimeMillis()/1000)),
				} ; 
				HttpGet httpGet = mHttpApi.createHttpGet(urlManager.favListsUrl(),
						values[0],
						values[1],
						values[2],
						new BasicNameValuePair("fig", Security.get32MD5Str(values)));
				 return mHttpApi.doHttpRequestObject(httpGet, new UserLoginBuilder());
	}
	/**
     * 消息列表
     */
	public List<Msg> showUserNotice(String userid,String token)
			throws XiaoMeiCredentialsException,XiaoMeiIOException,XiaoMeiJSONException ,XiaoMeiOtherException {
				BasicNameValuePair[] values = {
				        new BasicNameValuePair("userid", userid) ,
						new BasicNameValuePair("token", token) ,
						new BasicNameValuePair("uptime", String.valueOf(System.currentTimeMillis()/1000)),
				}; 
				HttpGet httpGet = mHttpApi.createHttpGet(urlManager.noticeUrl(),
						values[0],
						values[1],
						values[2],
						new BasicNameValuePair("fig", Security.get32MD5Str(values)));
				 return mHttpApi.doHttpRequestObject(httpGet, new MsgBuilder());
	}
	/**
     * 优惠列表
     */
	/*
	public User showDiscount(String cp,String)
			throws XiaoMeiCredentialsException,XiaoMeiIOException,XiaoMeiJSONException ,XiaoMeiOtherException {
				BasicNameValuePair[] values = {
				        new BasicNameValuePair("userid", userid) ,
						new BasicNameValuePair("token", token) ,
						new BasicNameValuePair("uptime", String.valueOf(System.currentTimeMillis()/1000)),
				} ; 
				HttpGet httpGet = mHttpApi.createHttpGet(urlManager.favListsUrl(),
						values[0],
						values[1],
						values[2],
						new BasicNameValuePair("fig", Security.get32MD5Str(values)));
				 return mHttpApi.doHttpRequestObject(httpGet, new UserLoginBuilder());
	}
	*/

	/**
	 * 会员卡列表
	 * @throws XiaoMeiOtherException 
	 * @throws XiaoMeiJSONException 
	 * @throws XiaoMeiIOException 
	 * @throws XiaoMeiCredentialsException 
	 */
	public List<MemberShip> showMemberShip(String userid, String token) 
	        throws XiaoMeiCredentialsException, XiaoMeiIOException, XiaoMeiJSONException, XiaoMeiOtherException {
	    BasicNameValuePair[] values = {
                new BasicNameValuePair("userid", userid) ,
                new BasicNameValuePair("token", token) ,
                new BasicNameValuePair("uptime", String.valueOf(System.currentTimeMillis()/1000)),
        };
	    HttpGet httpGet = mHttpApi.createHttpGet(urlManager.memberShip(),
                values[0],
                values[1],
                values[2],
                new BasicNameValuePair("fig", Security.get32MD5Str(values)));
         return mHttpApi.doHttpRequestObject(httpGet, new MemberShipBuilder());
	}
	
	/**
	 * 优惠个数
	 */
	public SaleCount mysaleCount() 
	        throws XiaoMeiCredentialsException, XiaoMeiIOException, XiaoMeiJSONException, XiaoMeiOtherException {
	    BasicNameValuePair[] values = {
                new BasicNameValuePair("uptime", String.valueOf(System.currentTimeMillis()/1000)),
        };
	    HttpGet httpGet = mHttpApi.createHttpGet(urlManager.memberShip(),
                values[0],
                new BasicNameValuePair("fig", Security.get32MD5Str(values)));
         return mHttpApi.doHttpRequestObject(httpGet, new SaleCountBuilder());
	}
}
