package com.erban.bean;

import java.io.Serializable;

public class NormalGoods implements Serializable {

    private static final long serialVersionUID = -2635270951888214909L;

    public long id;
    public String goods;
    public String logo;
    public String dprice;
    public String price;
    public boolean groupby;
    public boolean coupon;
    public String gets;
    public String desc;

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

    public String getDesc() {
        return desc;
    }

}
