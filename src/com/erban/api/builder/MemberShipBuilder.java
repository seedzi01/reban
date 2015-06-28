package com.erban.api.builder;

import java.util.Collections;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.erban.bean.MemberShip;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MemberShipBuilder extends AbstractJSONBuilder<List<MemberShip>> {

    @Override
    protected List<MemberShip> builder(JSONObject jsonObject)
            throws JSONException {
        if (jsonObject == null) {
            return Collections.emptyList();
        }
        Gson gson = new Gson();
        List<MemberShip> memberShips = gson.fromJson(jsonObject.toString(), 
                new TypeToken<List<MemberShipBuilder>>(){}.getType());
        return memberShips;
    }

}
