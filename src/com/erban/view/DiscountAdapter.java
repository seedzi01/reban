package com.erban.view;

import java.util.List;

import com.erban.R;
import com.erban.WifiApplication;
import com.erban.bean.NormalGoods;
import com.erban.util.ViewUtils;
import com.erban.webview.WebPageActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DiscountAdapter extends BaseAdapter {
    
    public static final int LI_JI_SHI_YONG = 0; //立即使用
    
    public static final int GUO_QI_SHI_XIAO = 1; //过期失效
    
    public static final int YI_SHI_YONG = 2; //已使用
    
    public int mCurrentPage;
    
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
                    .newInstance(parent, R.layout.view_discount_item);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(ITEM_TAG, viewHolder);
        }
        final NormalGoods item = getItem(position);
        viewHolder.distance.setText("500m");
        viewHolder.title.setText(item.getGoods());
        viewHolder.subTitle.setText(item.getDesc());
//        viewHolder.count.setText(WifiApplication.getInstance().getString(R.string.already_receive) + item.getGets());
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
        switch (mCurrentPage) {
        case LI_JI_SHI_YONG:
            viewHolder.bt.setText("立即使用");
            viewHolder.bt.setBackgroundResource(R.drawable.download_selector);
            viewHolder.bt.setTextColor(Color.parseColor("#ffffff"));
            break;
        case GUO_QI_SHI_XIAO:
            viewHolder.bt.setText("过期失效");
            viewHolder.bt.setBackgroundResource(R.drawable.guo_qi_shi_xiao);
            viewHolder.bt.setTextColor(Color.parseColor("#000000"));
            break;
        case YI_SHI_YONG:
            viewHolder.bt.setText("已使用");
            viewHolder.bt.setBackgroundResource(R.drawable.yi_shi_yong);
            viewHolder.bt.setTextColor(Color.parseColor("#ffffff"));
            break;
        default:
            break;
        }
        return convertView;
    }

    private class ViewHolder {

        private ImageView icon;
        private TextView distance;
        private TextView title;
        private TextView subTitle;
//        private TextView count;
        private TextView current;
        private TextView origin;
        private TextView bt;

        public ViewHolder(View view) {
            icon = (ImageView) view.findViewById(R.id.icon);
            distance = (TextView) view.findViewById(R.id.distance);
            title = (TextView) view.findViewById(R.id.title);
            subTitle = (TextView) view.findViewById(R.id.subTitle);
//            count = (TextView) view.findViewById(R.id.count);
            current = (TextView) view.findViewById(R.id.current);
            origin = (TextView) view.findViewById(R.id.origin);
            bt = (TextView) view.findViewById(R.id.bt);
        }

    }

}
