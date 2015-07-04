package com.erban.bean;

import java.io.Serializable;
import java.util.List;

import android.text.TextUtils;

import com.erban.wifi.PhoneWifiInfo;
import com.erban.wifi.SecurityType;

public class PrivateWifiListModel {

    public int code;
    public List<WifiModel> msg;

    public static class WifiModel implements Serializable, PhoneWifiInfo {
        private static final long serialVersionUID = -4511374368151578009L;
        public String ssid;
        public String pwd;
        public String type;
        public List<AdsModel> circules;

        @Override
        public String getWifiName() {
            return ssid;
        }

        @Override
        public String getBSSID() {
            // TODO unknown.
            return "";
        }

        @Override
        public SecurityType getSecurityType() {
            // TODO unknown.
            if (!TextUtils.isEmpty(type)) {
                return SecurityType.NONE;
            } else {
                try {
                    return SecurityType.valueOf(type);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return SecurityType.NONE;
        }

        @Override
        public int getSignalStrengthPercent() {
            return 88;
        }
    }

    public static class AdsModel implements Serializable {
        private static final long serialVersionUID = 3060365168747811706L;
        public String logo;
        public String notes;
    }
}
