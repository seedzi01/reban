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

public class WifiHandler {

    public static void requestAreaWifis(float lon, float alt) {

        BasicNameValuePair[] values = {
                new BasicNameValuePair("long", String.valueOf(lon)) ,
                new BasicNameValuePair("alti", String.valueOf(alt)) ,
        };

        Map<String, String> params = new HashMap<String, String>();
        params.put("long", String.valueOf(lon));
        params.put("alti", String.valueOf(alt));
        String figStr = Security.get32MD5Str(values);
        params.put("fig", figStr);

        GsonRequest<PrivateWifiListModel> request = 
                new GsonRequest<PrivateWifiListModel>(
                        Request.Method.POST, 
                        HttpUrls.getAreaWifi(),
                        PrivateWifiListModel.class,
                        params, 
                        new Response.Listener<PrivateWifiListModel>(){

                            @Override
                            public void onResponse(PrivateWifiListModel arg0) {
                                
                            }}, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError arg0) {
                                    
                                }
                            });
        WifiApplication.getRequestQueue().add(request);
    }

    public static void exactWifis(List<String> ssids) {
        
    }
}
