package com.erban.api.builder;

import org.json.JSONException;
import org.json.JSONObject;

import com.erban.bean.User;
import com.erban.util.Log;



public class UserLoginBuilder extends AbstractJSONBuilder<User> {
	/*
{
    "msg": {
        "token": "4674b2343b644bdc6b4106581d23863d2934211c5c1ffd8cca854604903c2a57",
        "userinfo": {
            "birth": null,
            "username": "",
            "nickname": "",
            "telno": "13436934495",
            "userid": "1",
            "gender": "0",
            "addr": "",
            "avatar": "",
            "qq": ""
        }
    },
    "code": 0
}
		*/
	@Override
	protected User builder(JSONObject jsonObject) throws JSONException {
	    Log.d("json", jsonObject.toString());
		if(jsonObject.has("msg"))
			jsonObject = jsonObject.getJSONObject("msg");
		else 
			return null;
		User user = new User();
		if(jsonObject.has("token"))
			user.setToken(jsonObject.getString("token"));
		if(jsonObject.has("userinfo")){
			JSONObject js = jsonObject.getJSONObject("userinfo");
			User.UserInfo userInfo = new User.UserInfo();
			if(js.has("user_type_desc"))
				userInfo.setUserTypeDesc(js.getString("user_type_desc"));
			if(js.has("modifydate"))
				userInfo.setModifydate(js.getString("modifydate"));
			if(js.has("username"))
				userInfo.setUsername(js.getString("username"));
			if(js.has("user_type"))
				userInfo.setUserType(js.getString("user_type"));
			if(js.has("age"))
				userInfo.setAge(js.getString("age"));
			if(js.has("createdate"))
				userInfo.setCreatedate(js.getString("createdate"));
			if(js.has("userid"))
				userInfo.setUserid(js.getString("userid"));
			if(js.has("mobile"))
				userInfo.setMobile(js.getString("mobile"));
	         if(js.has("avatar"))
	                userInfo.setAvatar(js.getString("avatar"));
   	         if(js.has("cost"))
	                userInfo.setCost(js.getString("cost"));
   	         if(js.has("addr"))
                    userInfo.setAddress(js.getString("addr"));
             if(js.has("idcard"))
                    userInfo.setIdcard(js.getString("idcard"));
             if(js.has("brith"))
                    userInfo.setBirth(js.getString("brith"));
             if(js.has("nickname"))
                    userInfo.setNickname(js.getString("nickname"));
             if(js.has("telno"))
                    userInfo.setTelno(js.getString("telno"));
             if(js.has("gender"))
                    userInfo.setGender(js.getString("gender"));
             if(js.has("openid"))
                    userInfo.setOpenid(js.getString("openid"));
             if(js.has("qq"))
                    userInfo.setQq(js.getString("qq")); 
			user.setUserInfo(userInfo);
			android.util.Log.d("user", "user = " + user);
		}
		return user;
	}
//	{"msg":{"token":"75ced4559baf29cc7ba5c80add96c39afa3bc39357685881bc1d12242e315ad1",
//	"userinfo":{"modifydate":"1428493921","sex":"0","username":"未定义","user_type":"1","age":"0","createdate":"1428493921","userid":"19062270","mobile":"13716417246"}},"code":0}
}
