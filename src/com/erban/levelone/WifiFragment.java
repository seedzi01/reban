package com.erban.levelone;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.erban.R;
import com.erban.WifiApplication;
import com.erban.bean.Location;
import com.erban.bean.PrivateWifiListModel;
import com.erban.util.ViewUtils;
import com.erban.view.WifiAdapter;
import com.erban.view.WifiStatusArea;
import com.erban.view.WifiStatusArea.RefreshCallBack;
import com.erban.volley.WifiHandler;
import com.erban.wifi.DevicesState;
import com.erban.wifi.PhoneWifiInfo;
import com.erban.wifi.PhoneWifiManager;
import com.erban.wifi.SecurityType;
import com.erban.wifi.WifiStateListener;

public class WifiFragment extends Fragment {

	private static final String TAG = WifiFragment.class.getSimpleName();

	private ListView wifiListView;
	private WifiStatusArea statusView;
	private TextView tipsView;

	private WifiAdapter adapter;

	private WifiStateListener statusListener = new WifiStateListener() {

		@Override
		public void onWifiScanSuccess() {
			updateWifis();
		}

		@Override
		public void onDevicesStateChanged(DevicesState state) {
			updateWifiStatus();
		}

		@Override
		public void onWifiStateChanged() {
			updateWifiStatus();
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return ViewUtils.newInstance(inflater, container,
				R.layout.fragment_wifi);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		PhoneWifiManager.getInstance(getActivity()).addListener(statusListener);
		PhoneWifiManager.getInstance(getActivity()).startScan();

		wifiListView = (ListView) view.findViewById(R.id.wifi_listview);
		initListView();
		tipsView = (TextView) view.findViewById(R.id.tips);
		// load wifis scan.
		updateWifis();
		updateWifiStatus();
		Log.d("location", System.currentTimeMillis() + " begin fetch");
		wifiListView.postDelayed((new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Location location = WifiApplication.getInstance()
						.getLbsManager().getLocation();
				if (location != null
						&& !TextUtils.isEmpty(location.getmLatitude())) {
					WifiHandler.requestAreaWifis(location.getmLongitude(),
							location.getmLatitude(),
							new WifiHandler.FetchListener() {

								@Override
								public void onResponse(
										PrivateWifiListModel model) {
									Log.d(TAG, "fetch recent wifi success.");
									if (model != null && model.msg != null
											&& model.msg.size() != 0) {
										adapter.setSpecialWifis(model.msg);
									}
								}

								@Override
								public void onError() {
									Log.d(TAG, "fetch recent wifi failed");
								}
							});
				}
			}
		}), 1000);
	}

	private void initListView() {
		statusView = (WifiStatusArea) ViewUtils.newInstance(wifiListView,
				R.layout.view_wifi_function_area);
		wifiListView.addHeaderView(statusView);
		adapter = new WifiAdapter();
		statusView.setRefreshCallBack(new RefreshCallBack() {

			@Override
			public void onRefresh() {
				Toast.makeText(getActivity(), R.string.refreshing, Toast.LENGTH_SHORT).show();
				PhoneWifiManager.getInstance(WifiApplication.getInstance())
						.startScan();
			}
		});
		wifiListView.setAdapter(adapter);
	}

	private void updateWifis() {
		List<PhoneWifiInfo> wifiInfos = PhoneWifiManager.getInstance(
				getActivity()).getLastestWifis();
		if (adapter != null && wifiInfos != null) {
			List<PhoneWifiInfo> noPasswords = new ArrayList<PhoneWifiInfo>();
			List<PhoneWifiInfo> needPasswords = new ArrayList<PhoneWifiInfo>();

			List<PhoneWifiInfo> configuredWifiInfos = PhoneWifiManager
					.getInstance(WifiApplication.getInstance())
					.getConfiguredWifis();

			for (PhoneWifiInfo wifiInfo : wifiInfos) {
				// 处理已经缓存的wifi.
				
				boolean hasMatched = false;
				if (configuredWifiInfos != null) {
					for (PhoneWifiInfo configItem : configuredWifiInfos) {
						if (wifiInfo.getWifiName().equals(configItem.getWifiName())) {
							hasMatched = true;
							break;
						}
					}
				}
				
				
				if (SecurityType.NONE.equals(wifiInfo.getSecurityType()) || hasMatched) {
					noPasswords.add(wifiInfo);
				} else {
					needPasswords.add(wifiInfo);
				}
			}
			// mock data.
			adapter.setWifiInfos(noPasswords, needPasswords);
		}
	}

	private void updateWifiStatus() {
		if (getActivity() != null) {
			if (!PhoneWifiManager.getInstance(WifiApplication.getInstance())
					.isWifiEnabled()) {
				statusView.showDisConnectedStatus();
				tipsView.setVisibility(View.VISIBLE);
				return;
			}
			PhoneWifiInfo wifiInfo = PhoneWifiManager
					.getInstance(getActivity()).getConnectedWifi();
			if (wifiInfo != null) {
				statusView.showConnectedStatus(wifiInfo.getWifiName());
				tipsView.setVisibility(View.GONE);
			}
		}
	}
}