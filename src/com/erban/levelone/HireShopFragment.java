package com.erban.levelone;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.erban.R;
import com.erban.WifiApplication;
import com.erban.bean.HireListModel;
import com.erban.bean.HireListModel.HireContentModel;
import com.erban.bean.NormalCompany;
import com.erban.bean.TopCompany;
import com.erban.util.ViewUtils;
import com.erban.view.FilterView;
import com.erban.view.HireAdapter;
import com.erban.view.SingleItemAdapter;
import com.erban.view.SingleItemAdapter.ExecuteItem;
import com.erban.volley.GsonRequest;
import com.erban.volley.HttpUrls;
import com.nostra13.universalimageloader.core.ImageLoader;

public class HireShopFragment extends BaseShopFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchHireList();
    }

    @Override
    protected void initTabs(List<FilterView> tabs) {
        tabs.get(0).setTitle(getString(R.string.zoom));
        tabs.get(1).setTitle(getString(R.string.pay));
        tabs.get(2).setTitle(getString(R.string.category));
    }

    @Override
    protected void initPopUpWindow() {
        super.initPopUpWindow();
        filterViewGroup.setListener(new onSelectedListener() {
            @Override
            public void onSelected(int pos) {
                switch (pos) {
                case 0:
                    bindDistances(filterListView);
                    break;
                case 1:
                    bindDistances(filterListView);
                    break;
                case 2:
                    bindDistances(filterListView);
                    break;
                default:
                    break;
                }
            }
        });
    }
    
    private void bindDistances(ListView listview) {

        // bind disntances.
        SingleItemAdapter itemAdapter = new SingleItemAdapter();
        ExecuteItem item = new ExecuteItem();
        item.title = "海淀";
        ExecuteItem item1 = new ExecuteItem();
        item1.title = "朝阳";
        ExecuteItem item2 = new ExecuteItem();
        item2.title = "东城";
        List<ExecuteItem> executeItems = new ArrayList<SingleItemAdapter.ExecuteItem>();
        executeItems.add(item);
        executeItems.add(item1);
        executeItems.add(item2);
        itemAdapter.setItems(executeItems);
        listview.setAdapter(itemAdapter);

        popupWindow.showAsDropDown(tabs.get(0));
    }

    
    private void showHeader(TopCompany topCompany) {
        View header = ViewUtils.newInstance(shopListView,
                R.layout.view_shop_banner);
        // load background image.
        ImageView banner = (ImageView) header.findViewById(R.id.banner_bg);
        ImageLoader.getInstance().displayImage(topCompany.logo, banner);
        // set title & goods description.
        TextView title = (TextView) header.findViewById(R.id.title);
        title.setText(topCompany.firm);
        TextView desc = (TextView) header.findViewById(R.id.desc);
        desc.setText(topCompany.detail);
        TextView discount = (TextView) header.findViewById(R.id.discount);
        discount.setText(topCompany.salary);
        shopListView.addHeaderView(header);
    }

    protected void showItems(List<NormalCompany> companies) {

        HireAdapter adapter = new HireAdapter();
        adapter.setItems(companies);
        shopListView.setAdapter(adapter);
    }

    public void fetchHireList() {
        GsonRequest<HireListModel> hireRequest = new GsonRequest<HireListModel>(
                HttpUrls.getHireListUrl(), HireListModel.class,
                new Response.Listener<HireListModel>() {
                    @Override
                    public void onResponse(HireListModel listModel) {
                        if (listModel == null) {
                            return;
                        }
                        if (listModel.getCode() == 0) {
                            HireContentModel contentModel = listModel.getMsg();
                            if (contentModel != null) {
                                showHeader(contentModel.getTop());
                                showItems(contentModel.getList());
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError arg0) {
                        // handle exception.
                        Toast.makeText(
                                WifiApplication.getInstance(),
                                WifiApplication.getInstance().getString(
                                        R.string.fetch_tips),
                                Toast.LENGTH_SHORT).show();
                    }
                });
        WifiApplication.getRequestQueue().add(hireRequest);
    }
}