package com.erban.api.builder;


import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.erban.DebugRelease;
import com.erban.bean.SaleCount;

public class SaleCountBuilder extends AbstractJSONBuilder<SaleCount> {

	@Override
	protected SaleCount builder(JSONObject jsonObject)
			throws JSONException {
		if(DebugRelease.isDebug)
			Log.d("json", jsonObject.toString());
		SaleCount saleCount = new SaleCount();
		if(jsonObject.has("gets"))
			saleCount.setGets(jsonObject.getString("gets"));
		if(jsonObject.has("expire"))
			saleCount.setExpire(jsonObject.getString("expire"));
		if(jsonObject.has("used"))
			saleCount.setUsed(jsonObject.getString("used"));
		return saleCount;
	}

}
