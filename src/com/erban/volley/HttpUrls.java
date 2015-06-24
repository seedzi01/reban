package com.erban.volley;

public class HttpUrls {

    private static final String ROOT_URL = "http://101.200.176.151/client/";

    public static String getHireListUrl() {
        return ROOT_URL + "hire/hires";
    }

    public static String getDiscountUrl() {
        return ROOT_URL + "sale/sales";
    }

}