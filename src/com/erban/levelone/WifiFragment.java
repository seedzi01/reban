package com.erban.levelone;

import java.util.ArrayList;
import java.util.List;

import com.erban.R;
import com.erban.levelone.control.WifiControl;
import com.erban.util.ViewUtils;
import com.erban.view.WifiAdapter;
import com.erban.view.WifiStatusArea;
import com.erban.wifi.SecurityType;
import com.erban.wifi.WifiInfo;
import com.yuekuapp.BaseFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class WifiFragment extends Fragment {

	private ListView wifiListView;
	private ImageView wifiSwitcher;
	private WifiStatusArea statusView;

	private boolean enable;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return ViewUtils.newInstance(inflater, container,
				R.layout.fragment_wifi);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		wifiListView = (ListView) view.findViewById(R.id.wifi_listview);
		wifiSwitcher = (ImageView) view.findViewById(R.id.wifi_switcher);
		initListView();
		wifiSwitcher.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				toggle();
			}
		});
	}

	private void initListView() {
		statusView = (WifiStatusArea) ViewUtils.newInstance(wifiListView,
				R.layout.view_wifi_function_area);
		wifiListView.addHeaderView(statusView);
		WifiAdapter adapter = new WifiAdapter();
		wifiListView.setAdapter(adapter);
		// mock data.
		List<WifiInfo> noList = new ArrayList<WifiInfo>();
		noList.add(new WifiInfo() {

			@Override
			public String getWifiName() {
				return "sdgaga";
			}

			@Override
			public int getSignalStrengthPercent() {
				return 0;
			}

			@Override
			public SecurityType getSecurityType() {
				return null;
			}

			@Override
			public String getBSSID() {
				return null;
			}
		});
		noList.add(new WifiInfo() {

			@Override
			public String getWifiName() {
				return "sagageags";
			}

			@Override
			public int getSignalStrengthPercent() {
				return 0;
			}

			@Override
			public SecurityType getSecurityType() {
				return null;
			}

			@Override
			public String getBSSID() {
				return null;
			}
		});
		List<WifiInfo> needList = new ArrayList<WifiInfo>();
		needList.add(new WifiInfo() {

			@Override
			public String getWifiName() {
				return "jglgulgh";
			}

			@Override
			public int getSignalStrengthPercent() {
				return 0;
			}

			@Override
			public SecurityType getSecurityType() {
				return null;
			}

			@Override
			public String getBSSID() {
				return null;
			}
		});
		needList.add(new WifiInfo() {

			@Override
			public String getWifiName() {
				return "dtjdfjfdt";
			}

			@Override
			public int getSignalStrengthPercent() {
				return 0;
			}

			@Override
			public SecurityType getSecurityType() {
				return null;
			}

			@Override
			public String getBSSID() {
				return null;
			}
		});
		adapter.setWifiInfos(noList, needList);
	}

	private void toggle() {
		enable = !enable;
		wifiSwitcher.setImageResource(enable ? R.drawable.view_switcher_on
				: R.drawable.view_switcher_off);
	}
}