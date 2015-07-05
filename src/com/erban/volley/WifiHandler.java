package com.erban.volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.message.BasicNameValuePair;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.erban.WifiApplication;
import com.erban.bean.PrivateWifiListModel;
import com.erban.util.Security;
import com.erban.wifi.PhoneWifiInfo;
import com.google.gson.Gson;

public class WifiHandler {

    public static void requestAreaWifis(String lon, String alt, final FetchListener lisFetchResult) {

        BasicNameValuePair[] values = {
                new BasicNameValuePair("long", lon),
                new BasicNameValuePair("alti", alt), };

        Map<String, String> params = new HashMap<String, String>();
        params.put("long", String.valueOf(lon));
        params.put("alti", String.valueOf(alt));
        String figStr = Security.get32MD5Str(values);
        params.put("fig", figStr);

        sendWifiRequest(params, HttpUrls.getAreaWifi(), lisFetchResult);
    }

    public static void exactWifis(List<String> ssids, final FetchListener lisFetchResult) {
        String ssidList = new Gson().toJson(ssids);

        BasicNameValuePair[] values = { new BasicNameValuePair("ssid", ssidList), };

        Map<String, String> params = new HashMap<String, String>();
        params.put("ssid", ssidList);
        String figStr = Security.get32MD5Str(values);
        params.put("fig", figStr);

        sendWifiRequest(params, HttpUrls.getExactWifi(), lisFetchResult);
    }

    public static void recordWifi(
            PhoneWifiInfo wifiInfo, String pwd, float lon, float alt, final FetchListener lisFetchResult) {
        BasicNameValuePair[] values = {
                new BasicNameValuePair("ssid", wifiInfo.getWifiName()),
                new BasicNameValuePair("pwd", pwd),
                new BasicNameValuePair("type", String.valueOf(wifiInfo.getSecurityType())),
                new BasicNameValuePair("long", String.valueOf(lon)),
                new BasicNameValuePair("alti", String.valueOf(alt))};
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("ssid", wifiInfo.getWifiName());
        params.put("pwd", pwd);
        if (wifiInfo.getSecurityType() != null) {
            params.put("type", String.valueOf(wifiInfo.getSecurityType()));
        }
        params.put("long", String.valueOf(lon));
        params.put("alti", String.valueOf(alt));
        String figStr = Security.get32MD5Str(values);
        params.put("fig", figStr);
        
        sendWifiRequest(params, HttpUrls.getRecordWifi(), lisFetchResult);
    }
    
    private static void sendWifiRequest(
            Map<String, String> params, String url, final FetchListener lisFetchResult) {
        GsonRequest<PrivateWifiListModel> request = new GsonRequest<PrivateWifiListModel>(
                Request.Method.POST, url, PrivateWifiListModel.class, params,
                new Response.Listener<PrivateWifiListModel>() {

                    @Override
                    public void onResponse(PrivateWifiListModel arg0) {
                        lisFetchResult.onResponse(arg0);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError arg0) {
                        lisFetchResult.onError();
                    }
                });
        WifiApplication.getRequestQueue().add(request);
    }

    public interface FetchListener {
        void onResponse(PrivateWifiListModel model);
        void onError();
    }

}
