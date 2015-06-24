package com.erban.levelone;

import java.util.ArrayList;
import java.util.List;

import com.erban.R;
import com.erban.bean.NormalGoods;
import com.erban.bean.TopGoods;
import com.erban.util.ViewUtils;
import com.erban.view.FilterView;
import com.erban.view.ShopAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class BaseShopFragment extends Fragment {

    protected ListView shopListView;

    protected FilterView first;
    protected FilterView second;
    protected FilterView third;

    private FilterViewGroup filterViewGroup;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopListView = (ListView) view.findViewById(R.id.shopListView);

        first = (FilterView) view.findViewById(R.id.first);
        second = (FilterView) view.findViewById(R.id.second);
        third = (FilterView) view.findViewById(R.id.third);

        List<FilterView> tabs = new ArrayList<FilterView>();
        tabs.add(first);
        tabs.add(second);
        tabs.add(third);
        initTabs(tabs);

        filterViewGroup = new FilterViewGroup();
        filterViewGroup.appendView(first);
        filterViewGroup.appendView(second);
        filterViewGroup.appendView(third);

        initItems();
        
        initHeader();
    }

    protected void initTabs(List<FilterView> tabs) {
        // hock function.
    }

    protected void initHeader() {
    }
    
    protected void initItems() {
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return ViewUtils.newInstance(inflater, container,
                R.layout.fragment_item_list);
    }

    private class FilterViewGroup {
        private final List<FilterView> filterViews = new ArrayList<FilterView>();

        public void appendView(FilterView view) {
            filterViews.add(view);
            view.setSelected(false);
            updateStatus();
        }

        private void updateStatus() {
            for (int i = 0; i < filterViews.size(); i++) {
                final int position = i;
                if (i == filterViews.size() - 1) {
                    filterViews.get(i).setRightLineVisibility(false);
                } else {
                    filterViews.get(i).setRightLineVisibility(true);
                }
                filterViews.get(i).setOnClickListener(
                        new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                select(position);
                            }
                        });
            }
        }

        private void select(int position) {
            for (int i = 0; i < filterViews.size(); i++) {
                filterViews.get(i).setSelected(i == position);
            }
        }
    }
}
