package com.reban.container;

import com.reban.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;


/**
 * Created by huzhi on 15-2-17.
 */
public class TabsActivity extends  com.reban.AbstractActivity{


    public static void startActivity(Activity ac){
        Intent intent = new Intent(ac,TabsActivity.class);
        ac.startActivity(intent);
        ac.overridePendingTransition(R.anim.activity_slid_in_from_right, R.anim.activity_slid_out_no_change);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	useAnimation = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_layout);
        
    }
    
    @Override
    public void onBackPressed() {
    	android.util.Log.d("111", "onBackPressed");
    	super.onBackPressed();
    }
    
}
