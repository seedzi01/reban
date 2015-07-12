package com.erban.view.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.erban.R;

public class ConnectingWifiDialog extends DialogFragment {

    private TextView statusView;
    private Dialog dialog;
    private Button completeButton;
    
    public static ConnectingWifiDialog newInstance() {
        ConnectingWifiDialog dialogFragment = new ConnectingWifiDialog();
        return dialogFragment;
    }
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View dialogView = inflater.inflate(R.layout.dialog_connecting_wifi, null,
                false);

        ImageView bannerView = (ImageView) dialogView.findViewById(R.id.banner);
        TextView titleView = (TextView) dialogView.findViewById(R.id.title);
        statusView = (TextView) dialogView.findViewById(R.id.status);
        statusView.setText(R.string.state_connecting);
        completeButton = (Button) dialogView.findViewById(R.id.complete);
        completeButton.setVisibility(View.GONE);
        completeButton.setVisibility(View.VISIBLE);
        statusView.setVisibility(View.GONE);
        completeButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                if (dialog != null) {
                    dialog.cancel();
                }
            }
        });

        dialog = new Dialog(getActivity(), R.style.Dialog_No_Border);
        dialog.setContentView(dialogView);

        return dialog;
    }

}
