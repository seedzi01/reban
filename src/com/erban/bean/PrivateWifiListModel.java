package com.erban.bean;

import java.io.Serializable;
import java.util.List;

public class PrivateWifiListModel {

    public int code;
    public List<WifiModel> msg;

    public static class WifiModel implements Serializable {
        private static final long serialVersionUID = -4511374368151578009L;
        public String ssid;
        public String pwd;
        public String type;
        public List<AdsModel> circules;
    }
    
    public static class AdsModel implements Serializable {
        private static final long serialVersionUID = 3060365168747811706L;
        public String logo;
        public String notes;
    }
}
