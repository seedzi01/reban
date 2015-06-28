package com.erban.view;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.erban.R;
import com.erban.bean.MemberShip;
import com.erban.util.ViewUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MemberShipAdapter extends BaseAdapter {

    private List<MemberShip> items;
    
    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public Object getItem(int position) {
        return items == null ? null : items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ViewUtils.newInstance(parent, R.layout.view_membership_item);
        }
        MemberShip item = items.get(position);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView num = (TextView) convertView.findViewById(R.id.num_id);
        View header = convertView.findViewById(R.id.header);
        ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
        ImageView vipMark = (ImageView) convertView.findViewById(R.id.vip_mark);
        TextView address = (TextView) convertView.findViewById(R.id.address);
        TextView phone = (TextView) convertView.findViewById(R.id.phone);
        
        int bgType = position % 3;
        if (bgType == 0) {
            header.setBackgroundResource(R.drawable.red_header_bg);
            vipMark.setImageResource(R.drawable.read_vip);
        } else if (bgType == 1) {
            header.setBackgroundResource(R.drawable.yellow_header_bg);
            vipMark.setImageResource(R.drawable.yellow_vip);
        } else {
            header.setBackgroundResource(R.drawable.green_header_bg);
            vipMark.setImageResource(R.drawable.green_vip);
        }
        
        title.setText(item.firm);
        num.setText("NO." + item.no);
        ImageLoader.getInstance().displayImage(item.logo, icon);
        address.setText(item.address);
        phone.setText("服务端还没有数据");
        
        return convertView;
    }

    public void setItems(List<MemberShip> memberShips) {
        items = memberShips;
    }

}
