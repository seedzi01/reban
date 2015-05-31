package com.erban.view;

import java.util.List;

import com.erban.R;
import com.erban.util.ViewUtils;
import com.erban.wifi.WifiInfo;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class WifiAdapter extends BaseAdapter {

	private List<WifiInfo> wifiInfos;

	@Override
	public int getCount() {
		return 1;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return ViewUtils.newInstance(parent, R.layout.view_wifi_count);
	}

}
