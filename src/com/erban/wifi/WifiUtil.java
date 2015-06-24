package com.erban.wifi;

import java.util.List;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import com.erban.WifiApplication;

public class WifiUtil {

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

	public static WifiInfo getWifiInfo() {
		WifiManager wifiManager = (WifiManager) WifiApplication.getInstance()
				.getSystemService(Context.WIFI_SERVICE);
		if (wifiManager != null) {
			return wifiManager.getConnectionInfo();
		}
		return null;
	}

	public static boolean isWifiEnable() {
		WifiManager wifiManager = (WifiManager) WifiApplication.getInstance()
				.getSystemService(Context.WIFI_SERVICE);
		if (wifiManager != null) {
			return wifiManager.isWifiEnabled();
		}
		return false;
	}

	public static boolean enableWifi() {
		WifiManager wifiManager = (WifiManager) WifiApplication.getInstance()
				.getSystemService(Context.WIFI_SERVICE);
		return wifiManager != null && wifiManager.setWifiEnabled(true);
	}

	public static boolean disableWifi() {
		WifiManager wifiManager = (WifiManager) WifiApplication.getInstance()
				.getSystemService(Context.WIFI_SERVICE);
		return wifiManager != null && wifiManager.setWifiEnabled(false);
	}

	public static boolean disconnect() {
		WifiManager wifiManager = (WifiManager) WifiApplication.getInstance()
				.getSystemService(Context.WIFI_SERVICE);
		if (wifiManager != null) {
			return wifiManager.disconnect();
		}
		return false;
	}

	public static boolean isUsingMobile() {
		ConnectivityManager connManager = (ConnectivityManager) WifiApplication
				.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo;
		try {
			activeNetworkInfo = connManager.getActiveNetworkInfo();
		} catch (NullPointerException ex) {
			return true;
		}
		return activeNetworkInfo != null
				&& activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
	}

	public static NetworkInfo getActiveNetworkInfo() {
		ConnectivityManager connManager = (ConnectivityManager) WifiApplication
				.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connManager != null) {
			return connManager.getActiveNetworkInfo();
		}
		return null;
	}

	public static int addNetwork(WifiConfiguration configuration) {
		WifiManager wifiManager = (WifiManager) WifiApplication.getInstance()
				.getSystemService(Context.WIFI_SERVICE);
		return wifiManager.addNetwork(configuration);
	}

	public static int updateNetwork(WifiConfiguration configuration) {
		WifiManager wifiManager = (WifiManager) WifiApplication.getInstance()
				.getSystemService(Context.WIFI_SERVICE);
		return wifiManager.updateNetwork(configuration);
	}

	public static boolean connect(int networkId) {
		WifiManager wifiManager = (WifiManager) WifiApplication.getInstance()
				.getSystemService(Context.WIFI_SERVICE);

		return wifiManager.enableNetwork(networkId, true)
				&& wifiManager.saveConfiguration() && wifiManager.reconnect();
	}

	public static boolean isWifiConnected() {
		NetworkInfo networkInfo = getWifiNetworkInfo();
		return networkInfo != null && networkInfo.isConnected();
	}

	public static boolean isSameSSID(String a, String b) {
		if (a == null || b == null) {
			return false;
		}
		a = removeDoubleQuotes(a);
		b = removeDoubleQuotes(b);

		return TextUtils.equals(a, b);
	}

	public static boolean isMobileAvailable(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mMobileNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mMobileNetworkInfo != null) {
				return mMobileNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	public static WifiInfo getConnectedWifiInfo() {
		NetworkInfo networkInfo = getWifiNetworkInfo();
		WifiInfo wifiInfo = getWifiInfo();

		if (networkInfo != null && wifiInfo != null
				&& networkInfo.isConnected()) {
			return wifiInfo;
		}
		return null;
	}

	public static String getConnectedSSID() {
		WifiInfo wifiInfo = getConnectedWifiInfo();

		if (wifiInfo != null && wifiInfo.getSSID() != null) {
			return removeDoubleQuotes(wifiInfo.getSSID());
		}
		return null;
	}

	public static NetworkInfo getWifiNetworkInfo() {
		ConnectivityManager connManager = (ConnectivityManager) WifiApplication
				.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connManager != null) {
			return connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		}
		return null;
	}

	public static void deleteWifiConfigurationByNetworkID(int networkId) {
		WifiManager wifiManager = (WifiManager) WifiApplication.getInstance()
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		if (wifiManager != null && wifiInfo != null) {
			wifiManager.removeNetwork(networkId);
			wifiManager.saveConfiguration();
		}
	}

	public static void deleteWifiConfigurationBySSID(String ssid) {
		WifiManager wifiManager = (WifiManager) WifiApplication.getInstance()
				.getSystemService(Context.WIFI_SERVICE);
		if (wifiManager != null) {
			List<WifiConfiguration> configuredNetworks = wifiManager
					.getConfiguredNetworks();
			if (configuredNetworks != null) {
				for (WifiConfiguration wifiConfiguration : configuredNetworks) {
					if (removeDoubleQuotes(ssid).equals(
							removeDoubleQuotes(wifiConfiguration.SSID))) {
						wifiManager.removeNetwork(wifiConfiguration.networkId);
						wifiManager.saveConfiguration();
						break;
					}
				}
			}
		}
	}

	public static void disableWifiConfiguration(String ssid) {
		WifiManager wifiManager = (WifiManager) WifiApplication.getInstance()
				.getSystemService(Context.WIFI_SERVICE);
		if (wifiManager != null) {
			List<WifiConfiguration> configuredNetworks = wifiManager
					.getConfiguredNetworks();
			if (configuredNetworks != null) {
				for (WifiConfiguration wifiConfiguration : configuredNetworks) {
					if (removeDoubleQuotes(ssid).equals(
							removeDoubleQuotes(wifiConfiguration.SSID))) {
						wifiManager.disableNetwork(wifiConfiguration.networkId);
					}
				}
			}
		}
	}

	public static String convertToQuotedString(String string) {
		return "\"" + string + "\"";
	}

	public static String wifiStateToString(int wifiState) {
		switch (wifiState) {
		case WifiManager.WIFI_STATE_DISABLED:
			return "WIFI_STATE_DISABLED";
		case WifiManager.WIFI_STATE_DISABLING:
			return "WIFI_STATE_DISABLING";
		case WifiManager.WIFI_STATE_ENABLED:
			return "WIFI_STATE_ENABLED";
		case WifiManager.WIFI_STATE_ENABLING:
			return "WIFI_STATE_ENABLING";
		case WifiManager.WIFI_STATE_UNKNOWN:
			return "WIFI_STATE_UNKNOWN";
		default:
			return "invalid_wifi_state";
		}
	}

	public static String removeDoubleQuotes(String string) {
		if (string != null) {
			int length = string.length();
			if ((length > 1) && (string.charAt(0) == '"')
					&& (string.charAt(length - 1) == '"')) {
				return string.substring(1, length - 1);
			}
		}
		return string;
	}
}
