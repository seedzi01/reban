package com.erban.view;

import java.util.List;

import com.erban.R;
import com.erban.bean.Msg;
import com.erban.util.ViewUtils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MsgAdapter extends BaseAdapter {
	
	private int ITEM_TAG = R.id.tag;
	
	private List<Msg> items;
	
	public void setItems(List<Msg> items) {
		this.items = items;
	}

	public List<Msg> getItems() {
		return this.items;
	}

	@Override
	public int getCount() {
		return items != null ? items.size() : 0;
	}

	@Override
	public Msg getItem(int position) {
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
					.newInstance(parent, R.layout.view_msg_item);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(ITEM_TAG, viewHolder);
		}
	    Msg item = getItem(position);
        viewHolder.title.setText(item.getTitle());
        viewHolder.from.setText(item.getFrom());
        viewHolder.content.setText(item.getContent());
        viewHolder.time.setText(item.getUptime());
        return convertView;
	}

	 private class ViewHolder {

	        private TextView title;
	        private TextView from;
	        private TextView content;
	        private TextView time;

	        public ViewHolder(View view) {
	            title = (TextView) view.findViewById(R.id.title);
	            from = (TextView) view.findViewById(R.id.from);
	            content = (TextView) view.findViewById(R.id.content);
	            time = (TextView) view.findViewById(R.id.time);
	        }

	    }
}
