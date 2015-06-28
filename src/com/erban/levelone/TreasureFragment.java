package com.erban.levelone;

import cn.waps.AppConnect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TreasureFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	AppConnect.getInstance(getActivity()).showOffers(getActivity());
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    
}
