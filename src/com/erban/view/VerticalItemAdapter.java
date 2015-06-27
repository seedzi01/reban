package com.erban.view;

import java.util.List;

import com.erban.R;
import com.erban.util.ViewUtils;
import com.erban.view.SingleItemAdapter.ExecuteItem;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class VerticalItemAdapter extends BaseAdapter  {

    private List<ExecuteItem> items;

    public void setItems(List<ExecuteItem> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public ExecuteItem getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ViewUtils.newInstance(parent, R.layout.view_vertical_single_item);
        }
        ImageView iconView = (ImageView) convertView.findViewById(R.id.icon);
        TextView titleView = (TextView) convertView.findViewById(R.id.title);
        final ExecuteItem item = getItem(pos);
        if (TextUtils.isEmpty(item.icon)) {
            iconView.setVisibility(View.GONE);
        } else {
            iconView.setVisibility(View.VISIBLE);
            iconView.setImageResource(Integer.valueOf(item.icon));
        }
        titleView.setText(item.title);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.action != null) {
                    item.action.execute();
                }
            }
        });
        return convertView;
    }

}
