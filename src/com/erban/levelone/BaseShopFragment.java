package com.erban.levelone;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

import com.erban.R;
import com.erban.util.ViewUtils;
import com.erban.view.FilterView;

public class BaseShopFragment extends Fragment {

    protected ListView shopListView;

    protected FilterView first;
    protected FilterView second;
    protected FilterView third;

    protected PopupWindow popupWindow;
    protected View shadowView;

    protected FilterViewGroup filterViewGroup;

    protected List<FilterView> tabs;

    protected ListView filterListView;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopListView = (ListView) view.findViewById(R.id.shopListView);
        shopListView.setDivider(null);
        shadowView = view.findViewById(R.id.shadow);
        first = (FilterView) view.findViewById(R.id.first);
        second = (FilterView) view.findViewById(R.id.second);
        third = (FilterView) view.findViewById(R.id.third);

        tabs = new ArrayList<FilterView>();
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

        initPopUpWindow();
    }

    protected void initPopUpWindow() {
        filterListView = new ListView(getActivity());
        popupWindow = new PopupWindow(getActivity());
        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(filterListView);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                tabs.get(0).setSelected(false);
                tabs.get(1).setSelected(false);
                tabs.get(2).setSelected(false);
                shadowView.setVisibility(View.GONE);
            }
        });
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        popupWindow.setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.color_white)));
        filterListView.setDivider(null);
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

    public class FilterViewGroup {
        private final List<FilterView> filterViews = new ArrayList<FilterView>();

        private onSelectedListener listener;

        public void setListener(onSelectedListener listener) {
            this.listener = listener;
        }

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
                                if (listener != null) {
                                    listener.onSelected(position);
                                }
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

    public interface onSelectedListener {
        void onSelected(int pos);
    }
}
