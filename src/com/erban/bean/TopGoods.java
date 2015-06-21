package com.erban.bean;

import java.io.Serializable;

public class TopGoods implements Serializable {

	private static final long serialVersionUID = -348605674511798822L;

	private long id;
	private String logo;
	private String mct;
	private String mctdesc;
	private String goods;
	private String goodsdesc;
	private String dprice;
	private String price;

	public long getId() {
		return id;
	}

	public String getLogo() {
		return logo;
	}

	public String getMct() {
		return mct;
	}

	public String getMctdesc() {
		return mctdesc;
	}

	public String getGoods() {
		return goods;
	}

	public String getGoodsdesc() {
		return goodsdesc;
	}

	public String getDprice() {
		return dprice;
	}

	public String getPrice() {
		return price;
	}

}
