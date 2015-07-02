package com.erban.volley;

public class HttpUrls {

    private static final String ROOT_URL = "http://101.200.176.151/client/";

    public static String getHireListUrl() {
        return ROOT_URL + "hire/hires";
    }

    public static String getDiscountUrl() {
        return ROOT_URL + "sale/sales";
    }

    public static String getAreaWifi() {
        return ROOT_URL + "wifi/areas";
    }

    public static String getExactWifi() {
        return ROOT_URL + "wifi/exact";
    }

    public static String getRecordWifi() {
        return ROOT_URL + "wifi/record";
    }
}