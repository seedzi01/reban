package com.erban.view.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.erban.R;
import com.erban.wifi.PhoneWifiInfo;

public class ConnectWifiDialog extends DialogFragment {

	private static final String ARGU_WIFI = "ARGU_NAME";
	
	private PhoneWifiInfo wifiInfo;
	
	public static ConnectWifiDialog newInstance(PhoneWifiInfo info) {
		ConnectWifiDialog dialogFragment = new ConnectWifiDialog();
		Bundle args = new Bundle();
		args.putSerializable(ARGU_WIFI, info);
		dialogFragment.setArguments(args);
		return dialogFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle args = getArguments();
		if (args != null) {
			wifiInfo = (PhoneWifiInfo) args.getSerializable(ARGU_WIFI);
		}
	}

	@SuppressLint("InflateParams")
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		View dialogView = inflater.inflate(R.layout.dialog_connect_wifi, null, false);
		
		TextView titleView = (TextView) dialogView.findViewById(R.id.dialog_title);
		titleView.setText(wifiInfo.getWifiName());
		
		Dialog dialog = new Dialog(getActivity(), R.style.Dialog_No_Border);
		dialog.setContentView(dialogView);
		dialog.setCanceledOnTouchOutside(true);
		return dialog;
	}

}
