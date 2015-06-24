package com.erban.bean;

import android.text.TextUtils;

import com.erban.SharePreferenceKey;
import com.erban.SharePreferenceWrap;



public class User {
	
	private String token;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private UserInfo userInfo;
	
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
/*
	{
	    "msg": {
	        "platform": null,
	        "birth": null,
	        "username": "",
	        "nickname": "",
	        "telno": "13436934495",
	        "userid": "1",
	        "gender": "0",
	        "addr": "",
	        "openid": null,
	        "avatar": "",
	        "qq": ""
	    },
	    "code": 0
	}
	*/
	/**
	 *  用戶中心的信息
	 */
	public static class UserInfo{
		
		private String userTypeDesc;
		
		private  String modifydate;
		
		private String username;
		
		private String userType;
		
		private String age;
		
		private String createdate;
		
		private String userid;
		
		private String mobile;
		
		private String avatar;
		
		private String cost;
		
		private String idcard;
		
		private String address;
		
		private String birth;
		
		private String nickname;
		
		private String telno;
		
		private String gender;
		
		private String openid;
		
		private String qq;
		
		public String getIdcard() {
			return idcard;
		}

		public void setIdcard(String idcard) {
			this.idcard = idcard;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getCost() {
			return cost;
		}

		public void setCost(String cost) {
			this.cost = cost;
		}

		public String getModifydate() {
			return modifydate;
		}

		public void setModifydate(String modifydate) {
			this.modifydate = modifydate;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getUserType() {
			return userType;
		}

		public void setUserType(String userType) {
			this.userType = userType;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

		public String getCreatedate() {
			return createdate;
		}

		public void setCreatedate(String createdate) {
			this.createdate = createdate;
		}

		public String getUserid() {
			return userid;
		}

		public void setUserid(String userid) {
			this.userid = userid;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getUserTypeDesc() {
			return userTypeDesc;
		}

		public void setUserTypeDesc(String userTypeDesc) {
			this.userTypeDesc = userTypeDesc;
		}

		public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getTelno() {
            return telno;
        }

        public void setTelno(String telno) {
            this.telno = telno;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getSex(){
            if(!TextUtils.isEmpty(gender) && "0".equals(gender)){
                return "男";
            }else if(!TextUtils.isEmpty(gender) && "1".equals(gender)){
                return "女";
            }
            return "未定义";
        }
        
        @Override
        public String toString() {
            return "UserInfo [userTypeDesc=" + userTypeDesc + ", modifydate="
                    + modifydate +  ", username=" + username
                    + ", userType=" + userType + ", age=" + age
                    + ", createdate=" + createdate + ", userid=" + userid
                    + ", mobile=" + mobile + ", avatar=" + avatar + ", cost="
                    + cost + ", idcard=" + idcard + ", address=" + address
                    + ", birth=" + birth + ", nickname=" + nickname
                    + ", telno=" + telno + ", gender=" + gender + ", openid="
                    + openid + ", qq=" + qq + "]";
        }
	}


	public static void save(User user){
		SharePreferenceWrap sharePreferenceWrap = new SharePreferenceWrap(SharePreferenceKey.USER);
		sharePreferenceWrap.putString(SharePreferenceKey.USER_TYPE_DESC, user.getUserInfo().userTypeDesc);
		sharePreferenceWrap.putString(SharePreferenceKey.USER_NAME, user.getUserInfo().username);
		sharePreferenceWrap.putString(SharePreferenceKey.USER_MODIFYDATE, user.getUserInfo().modifydate);
		sharePreferenceWrap.putString(SharePreferenceKey.USER_TYPE, user.getUserInfo().userType);
		sharePreferenceWrap.putString(SharePreferenceKey.USER_AGE, user.getUserInfo().age);
		sharePreferenceWrap.putString(SharePreferenceKey.USER_CREATEDATE, user.getUserInfo().createdate);
		sharePreferenceWrap.putString(SharePreferenceKey.USER_USERID, user.getUserInfo().userid);
		sharePreferenceWrap.putString(SharePreferenceKey.USER_MOBILE, user.getUserInfo().mobile);
		sharePreferenceWrap.putString(SharePreferenceKey.USER_AVATAR, user.getUserInfo().avatar);
		sharePreferenceWrap.putString(SharePreferenceKey.USER_COST, user.getUserInfo().cost);
		sharePreferenceWrap.putString(SharePreferenceKey.USER_IDCARD, user.getUserInfo().idcard);
		sharePreferenceWrap.putString(SharePreferenceKey.USER_ADDRESS, user.getUserInfo().address);
		sharePreferenceWrap.putString(SharePreferenceKey.USER_BIRTH, user.getUserInfo().birth);
		sharePreferenceWrap.putString(SharePreferenceKey.USER_NICKNAME, user.getUserInfo().nickname);
		sharePreferenceWrap.putString(SharePreferenceKey.USER_TELNO, user.getUserInfo().telno);
		sharePreferenceWrap.putString(SharePreferenceKey.USER_GENDER, user.getUserInfo().gender);
		sharePreferenceWrap.putString(SharePreferenceKey.USER_OPENID, user.getUserInfo().openid);
		sharePreferenceWrap.putString(SharePreferenceKey.USER_QQ, user.getUserInfo().qq);
	}
	
	public static User getFromShareP(){
		SharePreferenceWrap sharePreferenceWrap = new SharePreferenceWrap(SharePreferenceKey.USER);
		if(!sharePreferenceWrap.getBoolean(SharePreferenceKey.USER_VALID, false)){
			return null;
		}
		User user = new User();
		user.setToken(sharePreferenceWrap.getString(SharePreferenceKey.TOKEN, ""));
		User.UserInfo userInfo = new UserInfo();
		userInfo.setAge(sharePreferenceWrap.getString(SharePreferenceKey.USER_AGE, ""));
		userInfo.setCreatedate(sharePreferenceWrap.getString(SharePreferenceKey.USER_CREATEDATE, ""));
		userInfo.setMobile(sharePreferenceWrap.getString(SharePreferenceKey.USER_MOBILE, ""));
		userInfo.setModifydate(sharePreferenceWrap.getString(SharePreferenceKey.USER_MODIFYDATE, ""));
		userInfo.setUserid(sharePreferenceWrap.getString(SharePreferenceKey.USER_USERID, ""));
		userInfo.setUsername(sharePreferenceWrap.getString(SharePreferenceKey.USER_NAME, ""));
		userInfo.setUserType(sharePreferenceWrap.getString(SharePreferenceKey.USER_TYPE, ""));
		userInfo.setAvatar(sharePreferenceWrap.getString(SharePreferenceKey.USER_AVATAR, ""));
		userInfo.setUserTypeDesc(sharePreferenceWrap.getString(SharePreferenceKey.USER_TYPE_DESC, ""));
		userInfo.setCost(sharePreferenceWrap.getString(SharePreferenceKey.USER_COST, ""));
		userInfo.setIdcard(sharePreferenceWrap.getString(SharePreferenceKey.USER_IDCARD, ""));
		userInfo.setAddress(sharePreferenceWrap.getString(SharePreferenceKey.USER_ADDRESS, ""));
		userInfo.setBirth(sharePreferenceWrap.getString(SharePreferenceKey.USER_BIRTH, ""));
	    userInfo.setNickname(sharePreferenceWrap.getString(SharePreferenceKey.USER_NICKNAME, ""));
	    userInfo.setTelno(sharePreferenceWrap.getString(SharePreferenceKey.USER_TELNO ,"")); 
	    userInfo.setGender(sharePreferenceWrap.getString(SharePreferenceKey.USER_GENDER ,"")); 
	    userInfo.setOpenid(sharePreferenceWrap.getString(SharePreferenceKey.USER_OPENID ,"")); 
	    userInfo.setQq(sharePreferenceWrap.getString(SharePreferenceKey.USER_QQ ,"")); 
		
		user.setUserInfo(userInfo);
		return user;
	}

	@Override
	public String toString() {
		return "User [token=" + token + ", userInfo=" + userInfo + "]";
	}
	
}
