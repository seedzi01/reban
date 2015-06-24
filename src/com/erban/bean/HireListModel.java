package com.erban.bean;

import java.io.Serializable;
import java.util.List;

public class HireListModel  implements Serializable {

    private static final long serialVersionUID = -3953341316620607871L;

    private int code;
    private HireContentModel msg;

    public static class HireContentModel implements Serializable {

        private static final long serialVersionUID = -767268409276420748L;

        private TopCompany top;
        
        private List<NormalCompany> list;

        public TopCompany getTop() {
            return top;
        }

        public List<NormalCompany> getList() {
            return list;
        }
    }

    public int getCode() {
        return code;
    }

    public HireContentModel getMsg() {
        return msg;
    }

}
