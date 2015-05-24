package com.erban.levelone;

import com.erban.R;
import com.erban.util.ViewUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WifiFragment extends Fragment{
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return ViewUtils.newInstance(inflater, container, R.layout.fragment_wifi);
    }

}
