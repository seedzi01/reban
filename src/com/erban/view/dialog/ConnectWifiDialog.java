package com.erban.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

public class ConnectWifiDialog extends DialogFragment {

	private static final String ARGU_NAME = "ARGU_NAME";
	
	public static ConnectWifiDialog newInstance(String name) {
		ConnectWifiDialog dialogFragment = new ConnectWifiDialog();
		Bundle args = new Bundle();
		args.putString(ARGU_NAME, name);
		dialogFragment.setArguments(args);
		return dialogFragment;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
		LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		alertDialog.setCanceledOnTouchOutside(true);
		// alertDialog.setContentView(layoutResID);
		return alertDialog;
	}

}
