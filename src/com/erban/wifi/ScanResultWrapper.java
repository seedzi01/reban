package com.erban.wifi;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

/**
 * 对ScanResult做了简单的封装，屏蔽了一些细节，可读性更好。
 *
 * @author qisen (woaitqs@gmail.com).
 */
public class ScanResultWrapper implements WifiInfo {

    private final ScanResult scanResult;

    public ScanResultWrapper(ScanResult scanResult) {
        this.scanResult = scanResult;
    }

    /* (non-Javadoc)
	 * @see com.erban.wifi.WifiInfo#getWifiName()
	 */
    @Override
	public String getWifiName() {
        return scanResult.SSID;
    }

    /* (non-Javadoc)
	 * @see com.erban.wifi.WifiInfo#getBSSID()
	 */
    @Override
	public String getBSSID() {
        return scanResult.BSSID;
    }

    /* (non-Javadoc)
	 * @see com.erban.wifi.WifiInfo#getSecurityType()
	 */
    @Override
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

    /* (non-Javadoc)
	 * @see com.erban.wifi.WifiInfo#getSignalStrengthPercent()
	 */
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
