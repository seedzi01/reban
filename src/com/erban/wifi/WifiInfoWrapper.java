package com.erban.wifi;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiConfiguration.KeyMgmt;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class WifiInfoWrapper implements PhoneWifiInfo {

	private static final long serialVersionUID = 3649369893877249580L;

	private final WifiInfo wifiInfo;
	private final WifiConfiguration config;
	
	public WifiInfoWrapper(WifiInfo wifiInfo, WifiConfiguration configuration) {
		this.wifiInfo = wifiInfo;
		this.config = configuration;
	}
	
	@Override
	public String getWifiName() {
		return wifiInfo.getSSID();
	}

	@Override
	public String getBSSID() {
		return wifiInfo.getBSSID();
	}

	@Override
	public SecurityType getSecurityType() {
		if (config.allowedKeyManagement.get(KeyMgmt.WPA_PSK)) {
	        return SecurityType.PSK;
	    }
	    if (config.allowedKeyManagement.get(KeyMgmt.WPA_EAP) ||
	            config.allowedKeyManagement.get(KeyMgmt.IEEE8021X)) {
	        return SecurityType.EAP;
	    }
	    return (config.wepKeys[0] != null) ? SecurityType.WEP : SecurityType.NONE;
	}

	@Override
	public int getSignalStrengthPercent() {
		return WifiManager.calculateSignalLevel(wifiInfo.getRssi(), 100);
	}

}
