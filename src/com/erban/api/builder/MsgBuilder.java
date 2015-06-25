package com.erban.api.builder;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.erban.bean.Msg;
import com.erban.util.Log;

public class MsgBuilder extends AbstractJSONBuilder<List<Msg>> {

	@Override
	protected List<Msg> builder(JSONObject jsonObject) throws JSONException {
		Log.d("json", "jsonObject = " + jsonObject);
		List<Msg> list = new ArrayList<Msg>(); 
		JSONArray jsonArray = null;
		Msg msg = null;
		if(jsonObject.has("msg"))
			jsonArray = jsonObject.getJSONArray("msg");
		else
			return list;
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jObj = jsonArray.getJSONObject(i);
			msg = new Msg();
			if(jObj.has("title"))
				msg.setTitle(jObj.getString("title"));
			if(jObj.has("uptime"))
				msg.setUptime(jObj.getString("uptime"));
			if(jObj.has("content"))
				msg.setContent(jObj.getString("content"));
			if(jObj.has("from"))
				msg.setFrom(jObj.getString("from"));
			list.add(msg);
		}
		return list;
	}

}
