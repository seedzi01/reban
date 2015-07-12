package com.erban.volley;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.util.Log;

import com.erban.WifiApplication;
import com.erban.bean.Location;
import com.erban.bean.PrivateWifiListModel;
import com.erban.wifi.DevicesState;
import com.erban.wifi.PhoneWifiInfo;
import com.erban.wifi.PhoneWifiManager;
import com.erban.wifi.WifiStateListener;

public class WifiReporterProcesser {

    private static Map<PhoneWifiInfo, String> waitingWifis = new HashMap<PhoneWifiInfo, String>();

    private static WifiReporterProcesser instance = new WifiReporterProcesser();

    private WifiReporterProcesser() {
        PhoneWifiManager.getInstance(WifiApplication.getInstance()).addListener(statusListener);
    }

    public static WifiReporterProcesser getInstance() {
        return instance;
    }

    public void addWifi(PhoneWifiInfo wifiInfo, String pwd) {
        waitingWifis.put(wifiInfo, pwd);
        process();
    }

    private WifiStateListener statusListener = new WifiStateListener() {

        @Override
        public void onWifiScanSuccess() {
        }

        @Override
        public void onDevicesStateChanged(DevicesState state) {
        }

        @Override
        public void onWifiStateChanged() {
            if (PhoneWifiManager.getInstance(WifiApplication.getInstance()).isWifiEnabled()) {
                process();
            }
        }
    };

    @SuppressWarnings("unchecked")
    private void process() {
        Map<PhoneWifiInfo, String> snapShot = new HashMap<PhoneWifiInfo, String>();
        synchronized (waitingWifis) {
            snapShot.putAll(waitingWifis);
        }
        Iterator<?> iter = snapShot.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<PhoneWifiInfo, String> entry = (Map.Entry<PhoneWifiInfo, String>) iter.next();
            final PhoneWifiInfo wifi = entry.getKey();
            String pwd = entry.getValue();
            Location location = WifiApplication.getInstance().getLbsManager().getLocation();
            if (location != null) {
                WifiHandler.recordWifi(
                        wifi, pwd, 
                        location.getmLongitude(), location.getmLatitude(), 
                        new WifiHandler.FetchListener() {

                    @Override
                    public void onResponse(PrivateWifiListModel model) {
                        Log.d("wifi-handler", "send wifi detail success.");
                        if (model == null) {
                            return;
                        }
                        synchronized (waitingWifis) {
                            waitingWifis.remove(wifi);
                        }
                    }

                    @Override
                    public void onError() {
                        Log.d("wifi-handler", "send wifi detail failed.");
                    }

                });
            }
        }
    }
}
