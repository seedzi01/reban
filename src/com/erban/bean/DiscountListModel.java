package com.erban.bean;

import java.io.Serializable;
import java.util.List;

public class DiscountListModel implements Serializable {

    private static final long serialVersionUID = 5160463458051855752L;

    private int code;
    private DiscountContentModel msg;

    public static class DiscountContentModel implements Serializable {

        private static final long serialVersionUID = -8539846301541095957L;

        private NormalGoods top;

        private List<NormalGoods> list;

        public NormalGoods getTop() {
            return top;
        }

        public List<NormalGoods> getList() {
            return list;
        }
    }

    public int getCode() {
        return code;
    }

    public DiscountContentModel getMsg() {
        return msg;
    }

}