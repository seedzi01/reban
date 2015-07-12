package com.erban.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.app.Activity;
import android.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.erban.R;
import com.erban.WifiApplication;
import com.erban.util.ViewUtils;
import com.erban.view.dialog.ConnectWifiDialog;
import com.erban.wifi.PhoneWifiInfo;
import com.erban.wifi.PhoneWifiManager;
import com.erban.wifi.SecurityType;

public class WifiAdapter extends BaseAdapter {

    private List<PhoneWifiInfo> noPasswords;
    private List<PhoneWifiInfo> normalWifis;
    private List<? extends PhoneWifiInfo> specialWifis;

    private List<ViewType> types = new ArrayList<ViewType>();

    public void setWifiInfos(List<PhoneWifiInfo> noPasswords,
            List<PhoneWifiInfo> normalWifis) {
        this.noPasswords = noPasswords;
        this.normalWifis = normalWifis;
        rebuildTypeList();
        notifyDataSetChanged();
    }

    public void setSpecialWifis(List<? extends PhoneWifiInfo> specialWifis) {
        this.specialWifis = specialWifis;
        rebuildTypeList();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        int noPasswordSize = !isEmpty(noPasswords) ? noPasswords.size() : 0;
        int needPasswordSize = !isEmpty(normalWifis) ? normalWifis.size() : 0;
        int specialWifiSize = !isEmpty(specialWifis) ? specialWifis.size() : 0;

        int typeSize = 0;
        if (noPasswordSize != 0) {
            typeSize += 1;
        }
        if (needPasswordSize != 0) {
            typeSize += 1;
        }
        if (specialWifiSize != 0) {
            typeSize += 1;
        }

        return specialWifiSize + noPasswordSize + needPasswordSize + typeSize;
    }

    @Override
    public Object getItem(int position) {
        switch (types.get(position)) {
        case SPECAIL_WIFI_TITLE:
            return specialWifis.size();
        case SPECAIL_WIFI_ITEM:
            return specialWifis.get(position - 1);
        case NO_PASSWORD_ITEM:
            return noPasswords.get(position - 1
                    - (!isEmpty(specialWifis) ? specialWifis.size() + 1 : 0));
        case NO_PASSWORD_TITLE:
            return noPasswords.size();
        case NEED_PASSWORD_ITEM:
            return normalWifis.get(position - 1
                    - (!isEmpty(noPasswords) ? noPasswords.size() + 1 : 0)
                    - (!isEmpty(specialWifis) ? specialWifis.size() + 1 : 0));
        case NEED_PASSWORD_TITLE:
            return normalWifis.size();
        default:
            break;
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Object model = getItem(position);
        Binder binder = null;
        ViewType viewType = ViewType.values()[getItemViewType(position)];
        if (convertView != null) {
            binder = (Binder) convertView.getTag();
        } else {
            binder = viewType.binder;
            switch (viewType) {
            case NEED_PASSWORD_TITLE:
            case NO_PASSWORD_TITLE:
            case SPECAIL_WIFI_TITLE:
                convertView = ViewUtils.newInstance(parent,
                        R.layout.view_wifi_count);
                break;
            case NO_PASSWORD_ITEM:
            case NEED_PASSWORD_ITEM:
            case SPECAIL_WIFI_ITEM:
                convertView = ViewUtils.newInstance(parent,
                        R.layout.view_wifi_info);
                break;
            }
            convertView.setTag(binder);
        }
        // bind data.
        if (ViewType.NO_PASSWORD_ITEM.equals(viewType)) {
            if (binder instanceof WifiItemBinder) {
                ((WifiItemBinder) binder)
                        .setShowBottomLine(position != noPasswords.size());
            }
        }
        binder.bind(convertView, model);
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return types.get(position).ordinal();
    }

    @Override
    public int getViewTypeCount() {
        return ViewType.values().length;
    }

    private void rebuildTypeList() {
        types.clear();
        if (!isEmpty(specialWifis)) {
            types.add(ViewType.SPECAIL_WIFI_TITLE);
            for (int i = 0; i < specialWifis.size(); ++i) {
                types.add(ViewType.SPECAIL_WIFI_ITEM);
            }
        }
        if (!isEmpty(noPasswords)) {
            types.add(ViewType.NO_PASSWORD_TITLE);
            for (int i = 0; i < noPasswords.size(); i++) {
                types.add(ViewType.NO_PASSWORD_ITEM);
            }
        }
        if (!isEmpty(normalWifis)) {
            types.add(ViewType.NEED_PASSWORD_TITLE);
            for (int i = 0; i < normalWifis.size(); i++) {
                types.add(ViewType.NEED_PASSWORD_ITEM);
            }
        }
    }

    private enum ViewType {
        NO_PASSWORD_TITLE(new Binder() {
            @Override
            public void bind(View view, Object model) {
                TextView count = (TextView) view.findViewById(R.id.wifi_count);
                ImageView typeIcon = (ImageView) view
                        .findViewById(R.id.wifi_type);
                count.setText(String.format(
                        view.getResources().getString(
                                R.string.wifi_without_password),
                        ((Integer) model)));
                typeIcon.setImageResource(R.drawable.wifi_without_password);
            }
        }), NO_PASSWORD_ITEM(new WifiItemBinder()), NEED_PASSWORD_TITLE(
                new Binder() {
                    @Override
                    public void bind(View view, Object model) {
                        TextView count = (TextView) view
                                .findViewById(R.id.wifi_count);
                        ImageView typeIcon = (ImageView) view
                                .findViewById(R.id.wifi_type);
                        count.setText(String.format(view.getResources()
                                .getString(R.string.wifi_need_password),
                                ((Integer) model)));
                        typeIcon.setImageResource(R.drawable.wifi_with_password);
                    }
                }), NEED_PASSWORD_ITEM(new WifiItemBinder()), SPECAIL_WIFI_TITLE(
                new Binder() {
                    @Override
                    public void bind(View view, Object model) {
                        TextView count = (TextView) view
                                .findViewById(R.id.wifi_count);
                        ImageView typeIcon = (ImageView) view
                                .findViewById(R.id.wifi_type);
                        count.setText(String.format(view.getResources()
                                .getString(R.string.wifi_special),
                                ((Integer) model)));
                        typeIcon.setImageResource(R.drawable.special_wifi_icon);
                    }
                }), SPECAIL_WIFI_ITEM(new WifiItemBinder());

        private final Binder binder;

        private ViewType(Binder binder) {
            this.binder = binder;
        }

    }

    public static class WifiItemBinder implements Binder {

        private boolean showBottomLine = true;

        public void setShowBottomLine(boolean showBottomLine) {
            this.showBottomLine = showBottomLine;
        }

        @Override
        public void bind(final View view, Object model) {
            final PhoneWifiInfo wifiInfo = (PhoneWifiInfo) model;

            TextView wifiName = (TextView) view.findViewById(R.id.wifi_name);
            wifiName.setText(wifiInfo.getWifiName());

            ImageView wifiType = (ImageView) view.findViewById(R.id.wifi_type);
            // TODO this place need change.
            wifiType.setImageResource(SecurityType.NONE.equals(wifiInfo
                    .getSecurityType()) ? R.drawable.wifi_no_password_icon
                    : R.drawable.wifi_need_password_icon);

            ImageView wifiStrengthIcon = (ImageView) view
                    .findViewById(R.id.wifi_strength_icon);
            wifiStrengthIcon.setImageResource(getWifiStrengthIcon(wifiInfo
                    .getSignalStrengthPercent()));

            View bottomLine = view.findViewById(R.id.bottom_line);
            bottomLine.setVisibility(showBottomLine ? View.VISIBLE : View.GONE);

            TextView wifiStrengthText = (TextView) view
                    .findViewById(R.id.wifi_strength_text);
            wifiStrengthText.setText(wifiInfo.getSignalStrengthPercent() + "%");

            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (SecurityType.NONE.equals(wifiInfo.getSecurityType())) {
                        PhoneWifiManager.getInstance(
                                WifiApplication.getInstance()).connect(
                                wifiInfo.getWifiName(), "",
                                wifiInfo.getSecurityType());
                        return;
                    }
                    DialogManager.getInstance(v.getContext()).showConnectDialog(view.getContext(), wifiInfo);
                }
            });
        }
    };

    private interface Binder {
        void bind(View view, Object model);
    }

    private static int getWifiStrengthIcon(int percent) {
        if (percent <= 10) {
            return R.drawable.wifi_strength_none;
        } else if (percent <= 20) {
            return R.drawable.wifi_strength_tiny;
        } else if (percent <= 40) {
            return R.drawable.wifi_strength_low;
        } else if (percent <= 70) {
            return R.drawable.wifi_strength_normal;
        } else if (percent <= 90) {
            return R.drawable.wifi_strength_high;
        } else {
            return R.drawable.wifi_strength_full;
        }
    }

    private static boolean isEmpty(
            @SuppressWarnings("rawtypes") Collection collection) {
        return collection == null || collection.size() == 0;
    }

}
