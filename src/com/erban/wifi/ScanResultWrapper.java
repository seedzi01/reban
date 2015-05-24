package com.erban.wifi;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

/**
 * 对ScanResult做了简单的封装，屏蔽了一些细节，可读性更好。
 *
 * @author qisen (woaitqs@gmail.com).
 */
public class ScanResultWrapper {

    private final ScanResult scanResult;

    public ScanResultWrapper(ScanResult scanResult) {
        this.scanResult = scanResult;
    }

    public String getWifiName() {
        return scanResult.SSID;
    }

    /**
     * BSS 是一组相互通信的工作站，基于的通信方式是 <Station> - <Ad> - <Station>,
     * 所有无线网络中想要通信，都必须通过Ad。
     *
     * 这样一套工作站的Id，就是BSSID，一般情况下，是所处无线接入点的MAC地址
     *
     * @return bss id.
     */
    public String getBSSID() {
        return scanResult.BSSID;
    }

    /**
     * 获取WIFI的加密方式
     *
     * @return SecurityType
     */
    public SecurityType getSecurityType() {
        String capablitiesStr = scanResult.capabilities;
        if (TextUtils.isEmpty(scanResult.capabilities)) {
            return SecurityType.NONE;
        }
        if (capablitiesStr.contains("WEP")) {
            return SecurityType.WEP;
        } else if (capablitiesStr.contains("EAP")) {
            return SecurityType.EAP;
        } else {
            // 处理PSK的加密方式
            boolean wpa = capablitiesStr.contains("WPA-PSK");
            boolean wpa2 = capablitiesStr.contains("WPA2-PSK");
            if (wpa && wpa2) {
                return SecurityType.WPA_WPA2;
            } else if (wpa) {
                return SecurityType.WPA;
            } else if (wpa2) {
                return SecurityType.WPA2;
            }
        }
        return SecurityType.NONE;
    }

    /**
     * 计算得出WiFi的信号强度
     *
     * @return signal strength percent.
     */
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
