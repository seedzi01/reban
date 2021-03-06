package com.erban.view;

import java.util.List;

import com.erban.R;
import com.erban.WifiApplication;
import com.erban.bean.NormalGoods;
import com.erban.util.ViewUtils;
import com.erban.webview.WebPageActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopAdapter extends BaseAdapter {

    private int ITEM_TAG = R.id.tag;

    private List<NormalGoods> items;

    public void setItems(List<NormalGoods> items) {
        this.items = items;
    }
    
    public List<NormalGoods> getItems(){
    	return this.items;
    }

    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public NormalGoods getItem(int position) {
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
                    .newInstance(parent, R.layout.view_shop_item);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(ITEM_TAG, viewHolder);
        }
        final NormalGoods item = getItem(position);
        viewHolder.distance.setText("500m");
        viewHolder.title.setText(item.getGoods());
        if(TextUtils.isEmpty(item.getDesc())){
        	viewHolder.subTitle.setText(item.getDesc());	
        }
        viewHolder.count.setText(WifiApplication.getInstance().getString(R.string.already_receive) + item.getGets());
        viewHolder.current.setText(item.getDprice());
        viewHolder.origin.setText("￥" + item.getPrice());
        viewHolder.origin.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        ImageLoader.getInstance().displayImage(item.getLogo(), viewHolder.icon);
        convertView.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(item.jump)) {
                    return;
                }
                WebPageActivity.launch(v.getContext(), item.jump, String.valueOf(item.id));
            }
        });
        return convertView;
    }

    private class ViewHolder {

        private ImageView icon;
        private TextView distance;
        private TextView title;
        private TextView subTitle;
        private TextView count;
        private TextView current;
        private TextView origin;

        public ViewHolder(View view) {
            icon = (ImageView) view.findViewById(R.id.icon);
            distance = (TextView) view.findViewById(R.id.distance);
            title = (TextView) view.findViewById(R.id.title);
            subTitle = (TextView) view.findViewById(R.id.subTitle);
            count = (TextView) view.findViewById(R.id.count);
            current = (TextView) view.findViewById(R.id.current);
            origin = (TextView) view.findViewById(R.id.origin);
        }

    }
}
