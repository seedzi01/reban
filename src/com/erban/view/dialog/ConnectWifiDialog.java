package com.erban.view.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.erban.R;
import com.erban.WifiApplication;
import com.erban.util.ViewUtils;
import com.erban.wifi.PhoneWifiInfo;
import com.erban.wifi.PhoneWifiManager;

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
        View dialogView = inflater.inflate(R.layout.dialog_connect_wifi, null,
                false);

        final Dialog dialog = new Dialog(getActivity(), R.style.Dialog_No_Border);
        
        TextView titleView = (TextView) dialogView.findViewById(R.id.dialog_title);
        titleView.setText(wifiInfo.getWifiName());

        final EditText input = (EditText) dialogView.findViewById(R.id.dialog_input);
        
        Button confirm = (Button) dialogView.findViewById(R.id.dialog_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(input.getText().toString())) {
                    ViewUtils.showShortToast(R.string.no_password);
                    return;
                }
                PhoneWifiManager.getInstance(
                        WifiApplication.getInstance()).connect(
                                wifiInfo.getWifiName(), input.getText().toString(), wifiInfo.getSecurityType());
                dialog.cancel();
                FragmentManager transaction = ((Activity) view.getContext())
                        .getFragmentManager();
                ConnectingWifiDialog connectWifiDialog = ConnectingWifiDialog.newInstance();
                connectWifiDialog.setStyle(R.style.Dialog_No_Border, 0);
                connectWifiDialog.show(transaction, "ConnectingDialog");
            }
        });
        
        dialog.setContentView(dialogView);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

}
