package com.erban.view;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.erban.R;
import com.erban.bean.NormalCompany;
import com.erban.util.ViewUtils;

public class HireAdapter extends BaseAdapter {

    private static final int ITEM_TAG = R.id.hire_tab;
    
    private List<NormalCompany> items;
    
    public void setItems(List<NormalCompany> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public NormalCompany getItem(int position) {
        return items != null ? items.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag(ITEM_TAG);
        } else {
            convertView = ViewUtils
                    .newInstance(parent, R.layout.view_hire_item);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(ITEM_TAG, viewHolder);
        }
        NormalCompany item = getItem(position);
        viewHolder.title.setText(item.title);
        viewHolder.address.setText(item.address);
        viewHolder.date.setText(item.date);
        viewHolder.salary.setText(item.salary);
        viewHolder.detail.setText(item.detail);
        viewHolder.company.setText(item.company);
        return convertView;
    }

    private class ViewHolder {

        private ImageView type;
        private TextView address;
        private TextView date;
        private TextView title;
        private TextView salary;
        private TextView detail;
        private TextView company;

        public ViewHolder(View view) {
            type = (ImageView) view.findViewById(R.id.type);
            address = (TextView) view.findViewById(R.id.address);
            date = (TextView) view.findViewById(R.id.date);
            title = (TextView) view.findViewById(R.id.title);
            salary = (TextView) view.findViewById(R.id.salary);
            detail = (TextView) view.findViewById(R.id.detail);
            company = (TextView) view.findViewById(R.id.company);
        }

    }


}
