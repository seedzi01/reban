package com.erban.wifi;

import android.text.TextUtils;

public class WifiUtils {

	public static SecurityType formatSecurityType(String capabilities) {
		if (TextUtils.isEmpty(capabilities)) {
            return SecurityType.NONE;
        }
        if (capabilities.contains("WEP")) {
            return SecurityType.WEP;
        } else if (capabilities.contains("EAP")) {
            return SecurityType.EAP;
        } else {
            // 处理PSK的加密方式
            boolean wpa = capabilities.contains("WPA-PSK");
            boolean wpa2 = capabilities.contains("WPA2-PSK");
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
}
