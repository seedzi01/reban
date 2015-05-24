package com.erban.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;

/**
 * 监听相应的广播，并将事件发放出去。
 *
 * 这个类声明为 Package 权限，调用者不需要知道这个东西。
 *
 * @author qisen (woaitqs@gmail.com).
 */
class WifiReceiver extends BroadcastReceiver {

    private static WifiStateListener changeListener;
    private static boolean receiverRegistered;

    public static void setChangeListener(WifiStateListener listener) {
        changeListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            // 处理 wifi 网络设备的情况
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
            DevicesState devicesState = DevicesState.UNKNOWN;
            switch (wifiState) {
                case WifiManager.WIFI_STATE_DISABLED:
                    devicesState = DevicesState.DISABLED;
                    break;
                case WifiManager.WIFI_STATE_DISABLING:
                    devicesState = DevicesState.DISABLING;
                    break;
                case WifiManager.WIFI_STATE_ENABLED:
                    devicesState = DevicesState.ENABLED;
                    break;
                case WifiManager.WIFI_STATE_ENABLING:
                    devicesState = DevicesState.ENABLING;
                    break;
            }
            if (changeListener != null) {
                changeListener.onDevicesStateChanged(devicesState);
            }
        } else if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equalsIgnoreCase(intent.getAction())) {
            // 通知网络扫描已经完成
            if (changeListener != null) {
                changeListener.onWifiScanSuccess();
            }
        }
    }

    public static void initReceiver(Context appContext) {
        // 避免多次调用，否则会引发 already registered 的 exception
        if (!receiverRegistered) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
            filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
            filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
            filter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);
            appContext.registerReceiver(new WifiReceiver(), filter);
            receiverRegistered = true;
        }
    }

}
