package com.erban.levelone;

import com.erban.R;
import com.erban.util.ViewUtils;
import com.erban.view.WifiAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

public class WifiFragment extends Fragment{
    
	private ListView wifiListView;
	private ImageView wifiSwitcher;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return ViewUtils.newInstance(inflater, container, R.layout.fragment_wifi);
    }

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		wifiListView = (ListView) view.findViewById(R.id.wifi_listview);
		wifiSwitcher = (ImageView) view.findViewById(R.id.wifi_switcher);
		initListView();
	}

    private void initListView() {
    	View statusView = ViewUtils.newInstance(wifiListView, R.layout.view_wifi_function_area);
    	wifiListView.addHeaderView(statusView);
    	wifiListView.setAdapter(new WifiAdapter());
    }
}
