package com.erban.wifi;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

/**
 * Provider Basic function to query and manager wifi state.
 *
 * @author qisen (woaitqs@gmail.com).
 */
public class PhoneWifiManager {

    private final String TAG = PhoneWifiManager.class.getSimpleName();

    private static volatile PhoneWifiManager instance;

    private WifiManager wifiManager;
    private List<WeakReference<WifiStateListener>> listeners = new ArrayList<WeakReference<WifiStateListener>>();

    private WifiStateListener innerListener = new WifiStateListener() {
        @Override
        public void onWifiScanSuccess() {
            Iterator<WeakReference<WifiStateListener>> iterator = listeners
                    .iterator();
            while (iterator.hasNext()) {
                WeakReference<WifiStateListener> ref = iterator.next();
                if (ref.get() != null) {
                    ref.get().onWifiScanSuccess();
                } else {
                    iterator.remove();
                }
            }
        }

        @Override
        public void onDevicesStateChanged(DevicesState state) {
            Iterator<WeakReference<WifiStateListener>> iterator = listeners
                    .iterator();
            while (iterator.hasNext()) {
                WeakReference<WifiStateListener> ref = iterator.next();
                if (ref.get() != null) {
                    ref.get().onDevicesStateChanged(state);
                } else {
                    iterator.remove();
                }
            }
        }

        @Override
        public void onWifiStateChanged() {
            Iterator<WeakReference<WifiStateListener>> iterator = listeners
                    .iterator();
            while (iterator.hasNext()) {
                WeakReference<WifiStateListener> ref = iterator.next();
                if (ref.get() != null) {
                    ref.get().onWifiStateChanged();
                } else {
                    iterator.remove();
                }
            }
        }
    };

    private PhoneWifiManager(Context context) {
        wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiReceiver.setChangeListener(innerListener);
        WifiReceiver.initReceiver(context);
    }

    /**
     * Double - Check and provide stable access function to get PhoneWifiManager
     * instance.
     *
     * @param context
     *            application context.
     * @return phoneWifiManager instance.
     */
    public static PhoneWifiManager getInstance(Context context) {
        if (instance == null) {
            synchronized (PhoneWifiManager.class) {
                if (instance == null) {
                    instance = new PhoneWifiManager(context);
                }
            }
        }
        return instance;
    }

    public void startScan() {
        // TODO 这里可能需要做一些限制
        wifiManager.startScan();
    }

    /**
     * Get lastest wifi scaned.
     *
     * @return lastest wifis.
     */
    public List<PhoneWifiInfo> getLastestWifis() {
        List<PhoneWifiInfo> wrapperList = new ArrayList<PhoneWifiInfo>();
        List<ScanResult> result = wifiManager.getScanResults();
        if (result != null) {
            for (ScanResult item : result) {
                Log.d(TAG, item.toString());
                wrapperList.add(new ScanResultWrapper(item));
            }
        }
        return wrapperList;
    }

    /**
     * watch wifi status change.
     * 
     * @param listener
     */
    public void addListener(WifiStateListener listener) {
        if (listener == null) {
            return;
        }
        listeners.add(new WeakReference<WifiStateListener>(listener));
    }

    /**
     * check current network type is connected.
     * 
     * @param context
     *            app context.
     * @param netWorkType
     *            type of network. ConnectivityManager.TYPE_WIFI or
     *            ConnectivityManager.TYPE_MOBILE
     * @return connected or not.
     */
    public static boolean isConnected(Context context, int netWorkType) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connManager.getNetworkInfo(netWorkType);
        return info != null && info.isConnected();
    }

    /**
     * If wifi is connected currently, get connected wifi infos.
     * 
     * @return connected wifi info.
     */
    public PhoneWifiInfo getConnectedWifi() {
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        List<WifiConfiguration> configurations = wifiManager
                .getConfiguredNetworks();
        if (wifiInfo == null || configurations == null) {
            return null;
        }
        WifiConfiguration matched = null;
        for (WifiConfiguration item : configurations) {
            if (item.SSID.equals(wifiInfo.getSSID())) {
                matched = item;
                break;
            }
        }
        if (matched == null) {
            return null;
        }
        return new WifiInfoWrapper(wifiInfo, matched);
    }

    private static String wrapQuote(String origin) {
        return "\"" + origin + "\"";
    }

    public void connect(String ssid, String password, SecurityType type) {
        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = wrapQuote(ssid);
        switch (type) {
        case WPA:
        case WPA2:
        case WPA_WPA2:
            conf.preSharedKey = "\""+ password +"\"";
        case WEP:
        case PSK:
        case EAP:
            conf.wepKeys[0] = wrapQuote(password);
            conf.wepTxKeyIndex = 0;
            conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
            break;
        case NONE:
        default:
            conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            break;
        }
        wifiManager.addNetwork(conf);
        
        // remember wifi.
    }

}
