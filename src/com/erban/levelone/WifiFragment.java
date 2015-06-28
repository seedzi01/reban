package com.erban.levelone;

import java.util.ArrayList;
import java.util.List;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.erban.R;
import com.erban.WifiApplication;
import com.erban.util.ViewUtils;
import com.erban.view.WifiAdapter;
import com.erban.view.WifiStatusArea;
import com.erban.wifi.DevicesState;
import com.erban.wifi.PhoneWifiManager;
import com.erban.wifi.SecurityType;
import com.erban.wifi.PhoneWifiInfo;
import com.erban.wifi.WifiStateListener;

public class WifiFragment extends Fragment {

    private ListView wifiListView;
    private ImageView wifiSwitcher;
    private WifiStatusArea statusView;

    private boolean enable;

    private WifiAdapter adapter;

    private WifiStateListener statusListener = new WifiStateListener() {

        @Override
        public void onWifiScanSuccess() {
            updateWifis();
        }

        @Override
        public void onDevicesStateChanged(DevicesState state) {
            updateWifiStatus();
        }

        @Override
        public void onWifiStateChanged() {
            updateWifiStatus();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return ViewUtils.newInstance(inflater, container,
                R.layout.fragment_wifi);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PhoneWifiManager.getInstance(getActivity()).addListener(statusListener);
        PhoneWifiManager.getInstance(getActivity()).startScan();

        wifiListView = (ListView) view.findViewById(R.id.wifi_listview);
        wifiSwitcher = (ImageView) view.findViewById(R.id.wifi_switcher);
        initListView();
        wifiSwitcher.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                toggle();
            }
        });
        // load wifis scan.
        updateWifis();
        updateWifiStatus();
    }

    private void initListView() {
        statusView = (WifiStatusArea) ViewUtils.newInstance(wifiListView,
                R.layout.view_wifi_function_area);
        wifiListView.addHeaderView(statusView);
        adapter = new WifiAdapter();
        wifiListView.setAdapter(adapter);
    }

    private void toggle() {
        enable = !enable;
        wifiSwitcher.setImageResource(enable ? R.drawable.view_switcher_on
                : R.drawable.view_switcher_off);
    }

    private void updateWifis() {
        List<PhoneWifiInfo> wifiInfos = PhoneWifiManager.getInstance(
                getActivity()).getLastestWifis();
        if (adapter != null && wifiInfos != null) {
            List<PhoneWifiInfo> noPasswords = new ArrayList<PhoneWifiInfo>();
            List<PhoneWifiInfo> needPasswords = new ArrayList<PhoneWifiInfo>();
            for (PhoneWifiInfo wifiInfo : wifiInfos) {
                if (SecurityType.NONE.equals(wifiInfo.getSecurityType())) {
                    noPasswords.add(wifiInfo);
                } else {
                    needPasswords.add(wifiInfo);
                }
            }
            // mock data.
            adapter.setWifiInfos(noPasswords, needPasswords);
        }
    }

    private void updateWifiStatus() {
        if (statusView != null) {

            if (!PhoneWifiManager.isConnected(WifiApplication.getInstance(),
                    ConnectivityManager.TYPE_WIFI)) {
                statusView.showDisConnectedStatus();
                return;
            }
            PhoneWifiInfo wifiInfo = PhoneWifiManager
                    .getInstance(getActivity()).getConnectedWifi();
            if (wifiInfo != null) {
                statusView.showConnectedStatus(wifiInfo.getWifiName());
            }
        }
    }
}