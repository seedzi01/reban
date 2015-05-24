package com.erban.wifi;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Provider Basic function to query and manager wifi state.
 *
 * @author qisen (woaitqs@gmail.com).
 */
public class PhoneWifiManager {

    private final String TAG = PhoneWifiManager.class.getSimpleName();

    private static volatile PhoneWifiManager instance;

    private WifiManager wifiManager;
    private List<WeakReference<WifiStateListener>> listeners
            = new ArrayList<WeakReference<WifiStateListener>>();

    private WifiStateListener innerListener =
            new WifiStateListener() {
                @Override
                public void onWifiScanSuccess() {
                    Iterator<WeakReference<WifiStateListener>> iterator = listeners.iterator();
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
                    Iterator<WeakReference<WifiStateListener>> iterator = listeners.iterator();
                    while (iterator.hasNext()) {
                        WeakReference<WifiStateListener> ref = iterator.next();
                        if (ref.get() != null) {
                            ref.get().onDevicesStateChanged(state);
                        } else {
                            iterator.remove();
                        }
                    }
                }
            };

    private PhoneWifiManager(Context context) {
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiReceiver.setChangeListener(innerListener);
        WifiReceiver.initReceiver(context);
    }

    /**
     * Double - Check and provide stable access function to get PhoneWifiManager instance.
     *
     * @param context application context.
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
    public List<ScanResultWrapper> getLastestWifis() {
        List<ScanResultWrapper> wrapperList = new ArrayList<ScanResultWrapper>();
        List<ScanResult> result = wifiManager.getScanResults();
        if (result != null) {
            for (ScanResult item : result) {
                Log.d(TAG, item.toString());
            }
        }
        return wrapperList;
    }
}
