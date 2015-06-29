package com.erban.api.builder;

import java.util.Collections;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.erban.bean.MemberShip;
import com.erban.bean.MemberShipListModel;
import com.google.gson.Gson;

public class MemberShipBuilder extends AbstractJSONBuilder<List<MemberShip>> {

    @Override
    protected List<MemberShip> builder(JSONObject jsonObject)
            throws JSONException {
        if (jsonObject == null) {
            return Collections.emptyList();
        }
        Gson gson = new Gson();
        MemberShipListModel memberShips = gson.fromJson(jsonObject.toString(), 
                MemberShipListModel.class);
        if (memberShips != null && memberShips.code == 0) {
        	if (memberShips.msg == null) {
        		return Collections.emptyList();
        	}
        	return memberShips.msg;
        }
        return Collections.emptyList();
    }

}
