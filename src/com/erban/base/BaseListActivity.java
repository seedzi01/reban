package com.erban.base;

import android.os.Bundle;
import android.widget.BaseAdapter;

import com.erban.AbstractActivity;
import com.yuekuapp.BaseControl;

public class BaseListActivity<E extends BaseAdapter, T extends BaseControl> extends AbstractActivity<T>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }
    
    
    
}
