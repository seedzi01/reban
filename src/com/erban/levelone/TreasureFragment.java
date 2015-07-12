package com.erban.levelone;

import java.util.List;

import com.erban.R;
import com.erban.widget.TitleBar;
import com.nostra13.universalimageloader.core.ImageLoader;

import cn.waps.AdInfo;
import cn.waps.AppConnect;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TreasureFragment extends Fragment {

    private List<AdInfo> adInfoList;
    private  LayoutInflater inflater;
    private ViewGroup mRootView;
    private ListView mListView;
    private TreasureAdapter mAdapter;
    private TitleBar mTitlebar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        initData();
        setupview();
        return mRootView;
    }
    
    private void setupview(){
        inflater = LayoutInflater.from(getActivity());
        mRootView = (ViewGroup) inflater.inflate(R.layout.fragment_treasure_layout, null);
        
        mTitlebar = (TitleBar) mRootView.findViewById(R.id.titlebar);
        mTitlebar.setBackgroundColor(Color.parseColor("#28b937"));
        mTitlebar.findViewById(R.id.right_root).setVisibility(View.GONE);
        mTitlebar.setTitle("百宝箱");
        
        mListView = (ListView) mRootView.findViewById(R.id.list);
        mAdapter = new TreasureAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                if(adInfoList==null ||adInfoList.get(position)==null){
                    return;
                }
                AppConnect.getInstance(getActivity()).clickAd(getActivity(), adInfoList.get(position).getAdId());
            }
        });
    }
    
    private void initData(){
        adInfoList = AppConnect.getInstance(getActivity()).getAdInfoList();
    }
    
    private class TreasureAdapter extends BaseAdapter implements View.OnClickListener{

        @Override
        public int getCount() {
            return  adInfoList==null?0:adInfoList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            AdInfo anInfo = adInfoList.get(position);
            Holder holder = null;
            if(inflater == null)
                inflater = LayoutInflater.from(parent.getContext());
            if(convertView == null){
                convertView = inflater.inflate(R.layout.item_adinfo_layout, null);
                holder = new Holder();
                holder.titleTv = (TextView) convertView.findViewById(R.id.title);
                holder.contentTv = (TextView) convertView.findViewById(R.id.content);
                holder.iconIv = (ImageView) convertView.findViewById(R.id.icon);
                holder.download = (TextView) convertView.findViewById(R.id.download);
                holder.download.setOnClickListener(this);
                convertView.setTag(holder);
            }
            holder = (Holder) convertView.getTag();
            holder.titleTv.setText(anInfo.getAdName());
            holder.contentTv.setText(anInfo.getAdText());
            holder.iconIv.setImageBitmap(anInfo.getAdIcon());
            holder.download.setTag(Integer.valueOf(position));
            return convertView;
        }
        
        @Override
    	public void onClick(View v) {
    		int position = (Integer) v.getTag();
    		AppConnect.getInstance(getActivity()).downloadAd(getActivity(), adInfoList.get(position).getAdId());
    	}
        
    }
    
    private class Holder{
        private TextView titleTv;
        private TextView contentTv;
        private ImageView iconIv;
        private TextView descriptionTv;
        private TextView sizeTv;
        private View download;
    }

	
}
