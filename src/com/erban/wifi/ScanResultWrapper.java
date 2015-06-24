package com.erban.wifi;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

/**
 * 对ScanResult做了简单的封装，屏蔽了一些细节，可读性更好。
 *
 * @author qisen (woaitqs@gmail.com).
 */
public class ScanResultWrapper implements PhoneWifiInfo {

    private static final long serialVersionUID = 247088067920540566L;

    private final ScanResult scanResult;

    public ScanResultWrapper(ScanResult scanResult) {
        this.scanResult = scanResult;
    }

    @Override
    public String getWifiName() {
        return scanResult.SSID;
    }

    @Override
    public String getBSSID() {
        return scanResult.BSSID;
    }

    @Override
    public SecurityType getSecurityType() {
        return WifiUtil.formatSecurityType(scanResult.capabilities);
    }

    @Override
    public int getSignalStrengthPercent() {
        int percent = 0;
        try {
            percent = WifiManager.calculateSignalLevel(scanResult.level, 100);
        } catch (Exception e) {
            // 在某些极端情况下，这里可能发生除0的情况
            e.printStackTrace();
        }
        return percent;
    }
}
