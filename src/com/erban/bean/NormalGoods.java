package com.erban.bean;

import java.io.Serializable;

public class NormalGoods implements Serializable {

	private static final long serialVersionUID = -2635270951888214909L;
	private long id;
	private String goods;
	private String logo;
	private String dprice;
	private String price;
	private boolean groupby;
	private boolean coupon;
	private String gets;

	public long getId() {
		return id;
	}

	public String getGoods() {
		return goods;
	}

	public String getLogo() {
		return logo;
	}

	public String getDprice() {
		return dprice;
	}

	public String getPrice() {
		return price;
	}

	public boolean isGroupby() {
		return groupby;
	}

	public boolean isCoupon() {
		return coupon;
	}

	public String getGets() {
		return gets;
	}

}
