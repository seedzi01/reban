package com.erban.bean;

import java.io.Serializable;

public class NormalGoods implements Serializable {

    private static final long serialVersionUID = -8295178744539680074L;

    public long id;
    public String firm;
    public String logo;
    public String goods; // 商品名称
    public String dprice;
    public String price;
    public String groupby;
    public String coupon; // 优惠券
    public String gets;
    public String goodsdesc;
    public String jump;

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

    public String isGroupby() {
        return groupby;
    }

    public String isCoupon() {
        return coupon;
    }

    public String getGets() {
        return gets;
    }

    public String getDesc() {
        return goodsdesc;
    }

    public String getJump() {
        return jump;
    }

}
