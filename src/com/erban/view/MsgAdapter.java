package com.erban.view;

import java.util.List;

import com.erban.R;
import com.erban.bean.Msg;
import com.erban.module.user.center.MessageDetailActivity;
import com.erban.util.ViewUtils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MsgAdapter extends BaseAdapter implements View.OnClickListener{
	
	private Activity mAc;
	
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
		if (convertView == null) {
			convertView = ViewUtils
					.newInstance(parent, R.layout.view_msg_item);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
			convertView.setOnClickListener(this);
		}
		viewHolder = (ViewHolder) convertView.getTag();
	    Msg item = getItem(position);
        viewHolder.title.setText(item.getTitle());
        viewHolder.from.setText(item.getFrom());
        viewHolder.content.setText(item.getContent());
        viewHolder.time.setText(item.getUptime());
        viewHolder.msg = item;
        return convertView;
	}

	 private class ViewHolder {

	        private TextView title;
	        private TextView from;
	        private TextView content;
	        private TextView time;
	        private Msg msg;

	        public ViewHolder(View view) {
	            title = (TextView) view.findViewById(R.id.title);
	            from = (TextView) view.findViewById(R.id.from);
	            content = (TextView) view.findViewById(R.id.content);
	            time = (TextView) view.findViewById(R.id.time);
	        }

	    }
	 
	public void setActivity(Activity ac){
		mAc = ac;
	} 

	@Override
	public void onClick(View v) {
	    ViewHolder viewHolder = (ViewHolder) v.getTag();
		MessageDetailActivity.startActivity(mAc,viewHolder.msg);
	}
}
