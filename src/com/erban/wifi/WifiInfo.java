package com.erban.wifi;

public interface WifiInfo {

	/**
	 * @return wifi name.
	 */
	String getWifiName();

	/**
	 * BSS 是一组相互通信的工作站，基于的通信方式是 <Station> - <Ad> - <Station>,
	 * 所有无线网络中想要通信，都必须通过Ad。
	 *
	 * 这样一套工作站的Id，就是BSSID，一般情况下，是所处无线接入点的MAC地址
	 *
	 * @return bss id.
	 */
	String getBSSID();

	/**
	 * 获取WIFI的加密方式
	 *
	 * @return SecurityType
	 */
	SecurityType getSecurityType();

	/**
	 * 计算得出WiFi的信号强度
	 *
	 * @return signal strength percent.
	 */
	int getSignalStrengthPercent();

}