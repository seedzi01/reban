package com.erban.container;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.erban.R;
import com.erban.levelone.ShopContainerFragment;
import com.erban.levelone.TreasureFragment;
import com.erban.levelone.UserFragment;
import com.erban.levelone.WifiFragment;

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
		    fragment = new ShopContainerFragment();
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
