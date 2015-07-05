package com.erban.view;

import com.erban.R;
import com.erban.wifi.WifiUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WifiStatusArea extends RelativeLayout {

    private View connectedView;
    private View disConnectedView;

    private TextView nameView;
    private Button refreshButton;

    public WifiStatusArea(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public WifiStatusArea(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WifiStatusArea(Context context) {
        super(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        connectedView = findViewById(R.id.wifi_connected_info);
        disConnectedView = findViewById(R.id.wifi_disconnected_info);
        nameView = (TextView) findViewById(R.id.wifi_name);
        refreshButton = (Button) findViewById(R.id.wifi_refresh_button);
    }

    public void showConnectedStatus(String wifiName) {
        connectedView.setVisibility(View.VISIBLE);
        disConnectedView.setVisibility(View.GONE);
        nameView.setText(WifiUtil.removeDoubleQuotes(wifiName));
    }

    public void showDisConnectedStatus() {
        connectedView.setVisibility(View.GONE);
        disConnectedView.setVisibility(View.VISIBLE);
    }

    public void setRefreshCallBack(final RefreshCallBack callback) {
        refreshButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                callback.onRefresh();
            }
        });
    }

    public interface RefreshCallBack {
        void onRefresh();
    }
}
