package com.erban.api.builder;

import org.json.JSONException;
import org.json.JSONObject;

import com.erban.bean.NetResult;



public class NetResultBuilder extends AbstractJSONBuilder<NetResult> {

	@Override
	protected NetResult builder(JSONObject jsonObject) throws JSONException {
		NetResult netResult = new NetResult();
		if(jsonObject.has("code"))
			netResult.setCode(jsonObject.getString("code"));
		if(jsonObject.has("msg"))
			netResult.setMsg(jsonObject.getString("msg"));
		return netResult;
	}
	
	


}
