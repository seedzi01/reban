package com.reban.container;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.reban.R;
import com.reban.levelone.MerchantFragment;
import com.reban.levelone.TreasureFragment;
import com.reban.levelone.UserFragment;
import com.reban.levelone.WifiFragment;



/**
 * Created by huzhi on 15-3-9.
 */
public class TabsFragmentManager {
	
	private Map<String ,Fragment> fragments = new HashMap<String ,Fragment>();

    public TabsFragmentManager(){};

    public Fragment getFragment(int position){
        Fragment fragment = null;
        switch (position) {
		case 0:
		    fragment = new WifiFragment();
			break;
		case 1:
		    fragment = new MerchantFragment();
			break;
		case 2:
		    fragment = new TreasureFragment();
			break;
		case 3:
		    fragment = new UserFragment();
			break;
		default:
			break;
		}
		fragments.put(position+"", fragment);
        return fragment;
    }

    @SuppressLint("NewApi")
	public void commitFragment(int position,FragmentActivity ac){
        FragmentManager fragmentManager = ac.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment;
        if(fragments.get(position+"")==null){
            fragment = getFragment(position);
        }else {
        	fragment = fragments.get(position + "");
        }
        fragmentTransaction.replace(R.id.container_body_layout,fragment,String.valueOf(position));
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }
	
	
}
