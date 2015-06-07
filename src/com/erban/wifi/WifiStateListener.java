package com.erban.wifi;

/**
 * Listener 用于监听网络状态是否发生变化
 *
 * @author qisen (woaitqs@gmail.com).
 */
public interface WifiStateListener {

    /**
     * 启动Wifi Scan，并扫描周围wifi 工作已经完成，可以获取相应的数据
     */
    void onWifiScanSuccess();

    /**
     * Wifi设备本身的状态改变。
     *
     * @param state new wifi state.
     */
    void onDevicesStateChanged(DevicesState state);

    /**
     * 通知wifi状态发生改变
     */
    void onWifiStateChanged();
}
