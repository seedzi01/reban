package com.erban.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.erban.R;
import com.erban.WifiApplication;
import com.erban.view.dialog.ConnectWifiDialog;
import com.erban.view.dialog.ConnectingWifiDialog;
import com.erban.wifi.DevicesState;
import com.erban.wifi.PhoneWifiInfo;
import com.erban.wifi.PhoneWifiManager;
import com.erban.wifi.WifiStateListener;

public class DialogManager {

    private boolean hasClickedWifi = false;

    private static DialogManager instance;
    private static Context context;

    private DialogManager() {
        PhoneWifiManager.getInstance(WifiApplication.getInstance())
                .addListener(wifiListener);
    }

    public static DialogManager getInstance(Context activity) {
        if (instance == null) {
            instance = new DialogManager();
        }
        context = activity;
        return instance;
    }

    private WifiStateListener wifiListener = new WifiStateListener() {

        @Override
        public void onWifiStateChanged() {
            boolean connected = PhoneWifiManager.isConnected(WifiApplication.getInstance(),
                    ConnectivityManager.TYPE_WIFI);
            if (hasClickedWifi) {
                if (connected) {
                    showAdsDialog(context);
                } else {
                    Toast.makeText(
                            context,
                            WifiApplication.getInstance().getString(
                                    R.string.connect_wifi_failed),
                            Toast.LENGTH_SHORT).show();
                }
            }
            if (connected) {
                hasClickedWifi = false;
            }
        }

        @Override
        public void onWifiScanSuccess() {
            // do nothings.
        }

        @Override
        public void onDevicesStateChanged(DevicesState state) {
            hasClickedWifi = false;
        }
    };

    private void showAdsDialog(Context context) {
        if (context == null || !(context instanceof Activity)) {
            return;
        }
        FragmentManager transaction = ((Activity) context).getFragmentManager();
        ConnectingWifiDialog connectWifiDialog = ConnectingWifiDialog
                .newInstance();
        connectWifiDialog.setStyle(R.style.Dialog_No_Border, 0);
        connectWifiDialog.show(transaction, "ConnectingDialog");
    }

    public void showConnectDialog(Context context, PhoneWifiInfo wifiInfo) {
        FragmentManager transaction = ((Activity) context).getFragmentManager();
        ConnectWifiDialog dialog = ConnectWifiDialog.newInstance(wifiInfo);
        dialog.setStyle(R.style.Dialog_No_Border, 0);
        dialog.show(transaction, "ConnectDialog");
        hasClickedWifi = true;
    }
}
