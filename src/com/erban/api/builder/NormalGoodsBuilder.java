package com.erban.api.builder;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.erban.bean.NormalGoods;
import com.erban.util.Log;

public class NormalGoodsBuilder extends AbstractJSONBuilder<List<NormalGoods>> {

	/*
	{
	    "msg": [
	        {
	            "id": 11,
	            "logo": "http://pic10.nipic.com/20101014/1341920_092555096491_2.jpg",
	            "price": "500",
	            "groupbuy": "1",
	            "firm": "咖啡時刻(商户名)",
	            "gets": "200",
	            "goods": "商品名(商品名称)",
	            "dprice": "257",
	            "goodsdesc": "【8店通用】，非常棒的体验，就算什么都不能阻挡，就这样吧(商品描述)",
	            "coupon": "1",
	            "jump": "跳转h5地址"
	        },
	        {
	            "id": 12,
	            "logo": "http://pic10.nipic.com/20101014/1341920_092555096491_2.jpg",
	            "price": "500",
	            "groupbuy": "1",
	            "firm": "咖啡時刻(商户名)",
	            "gets": "200",
	            "goods": "商品名(商品名称)",
	            "dprice": "257",
	            "goodsdesc": "【8店通用】，非常棒的体验，就算什么都不能阻挡，就这样吧(商品描述)",
	            "coupon": "1",
	            "jump": "跳转h5地址"
	        }
	    ],
	    "code": 0
	}
	*/
	
	@Override
	protected List<NormalGoods> builder(JSONObject jsonObject)
			throws JSONException {
		Log.d("json", "list normalgoods = " + jsonObject);
		JSONArray jsonArray = null;
		if(jsonObject.has("msg"))
			jsonArray = jsonObject.getJSONArray("msg");
		List<NormalGoods> list = new ArrayList<NormalGoods>();
		NormalGoods normalGoods = null;
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			normalGoods = new NormalGoods();
			if(jsonObj.has("id"))
				normalGoods.id = jsonObj.getLong("id");
			if(jsonObj.has("logo"))
				normalGoods.logo = jsonObj.getString("logo");
			if(jsonObj.has("price"))
				normalGoods.price = jsonObj.getString("price");
			if(jsonObj.has("groupbuy"))
				normalGoods.groupby = jsonObj.getString("groupbuy");
			if(jsonObj.has("firm"))
				normalGoods.firm = jsonObj.getString("firm");
			if(jsonObj.has("gets"))
				normalGoods.gets = jsonObj.getString("gets");
			if(jsonObj.has("goods"))
				normalGoods.goods = jsonObj.getString("goods");
			if(jsonObj.has("dprice"))
				normalGoods.dprice = jsonObj.getString("dprice");
			if(jsonObj.has("goodsdesc"))
				normalGoods.goodsdesc = jsonObj.getString("goodsdesc");
			if(jsonObj.has("coupon"))
				normalGoods.coupon = jsonObj.getString("coupon");
			if(jsonObj.has("jump"))
				normalGoods.jump = jsonObj.getString("jump");
			list.add(normalGoods);
		}
		return list;
	}

}
