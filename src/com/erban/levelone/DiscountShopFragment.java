package com.erban.levelone;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Paint;
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
import com.erban.bean.DiscountListModel;
import com.erban.bean.DiscountListModel.DiscountContentModel;
import com.erban.bean.NormalGoods;
import com.erban.util.ViewUtils;
import com.erban.view.FilterView;
import com.erban.view.ShopAdapter;
import com.erban.view.SingleItemAdapter;
import com.erban.view.SingleItemAdapter.ExecuteItem;
import com.erban.volley.GsonRequest;
import com.erban.volley.HttpUrls;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DiscountShopFragment extends BaseShopFragment {

    private List<FilterView> tabs;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchDiscountList();
    }

    protected void initTabs(List<FilterView> tabItems) {
        this.tabs = tabItems;
        tabs.get(0).setTitle(getString(R.string.distance));
        tabs.get(1).setTitle(getString(R.string.category));
        tabs.get(2).setTitle(getString(R.string.sort));
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
                    bindCategory(filterListView);
                    break;
                case 2:
                    bindSort(filterListView);
                    break;
                default:
                    break;
                }
            }
        });
    }

    protected void showHeader(NormalGoods topGoods) {

        View header = ViewUtils.newInstance(shopListView,
                R.layout.view_shop_banner);
        // load background image.
        ImageView banner = (ImageView) header.findViewById(R.id.banner_bg);
        ImageLoader.getInstance().displayImage(topGoods.logo, banner);
        // set title & goods description.
        TextView title = (TextView) header.findViewById(R.id.title);
        title.setText(topGoods.firm);
        TextView desc = (TextView) header.findViewById(R.id.desc);
        desc.setText(topGoods.goodsdesc);
        // set price & discount prices.
        TextView origin = (TextView) header.findViewById(R.id.origin_price);
        origin.setText(topGoods.price);
        // strike line.
        origin.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        TextView discount = (TextView) header.findViewById(R.id.discount);
        discount.setText(topGoods.dprice);
        shopListView.addHeaderView(header);
    }

    protected void showItems(List<NormalGoods> items) {
        ShopAdapter adapter = new ShopAdapter();
        adapter.setItems(items);
        shopListView.setAdapter(adapter);
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

    private void bindCategory(ListView listview) {

        // bind disntances.
        SingleItemAdapter itemAdapter = new SingleItemAdapter();
        ExecuteItem item = new ExecuteItem();
        item.title = "西餐";
        ExecuteItem item1 = new ExecuteItem();
        item1.title = "电影院";
        ExecuteItem item2 = new ExecuteItem();
        item2.title = "游戏";
        List<ExecuteItem> executeItems = new ArrayList<SingleItemAdapter.ExecuteItem>();
        executeItems.add(item);
        executeItems.add(item1);
        executeItems.add(item2);
        itemAdapter.setItems(executeItems);
        listview.setAdapter(itemAdapter);

        popupWindow.showAsDropDown(tabs.get(0));
    }
    
    private void bindSort(ListView listview) {

        // bind disntances.
        SingleItemAdapter itemAdapter = new SingleItemAdapter();
        ExecuteItem item = new ExecuteItem();
        item.title = "按距离排序";
        ExecuteItem item1 = new ExecuteItem();
        item1.title = "按评价排序";
        ExecuteItem item2 = new ExecuteItem();
        item2.title = "按价格排序";
        List<ExecuteItem> executeItems = new ArrayList<SingleItemAdapter.ExecuteItem>();
        executeItems.add(item);
        executeItems.add(item1);
        executeItems.add(item2);
        itemAdapter.setItems(executeItems);
        listview.setAdapter(itemAdapter);

        popupWindow.showAsDropDown(tabs.get(0));
    }
    
    public void fetchDiscountList() {
        GsonRequest<DiscountListModel> hireRequest = new GsonRequest<DiscountListModel>(
                HttpUrls.getDiscountUrl(), DiscountListModel.class,
                new Response.Listener<DiscountListModel>() {
                    @Override
                    public void onResponse(DiscountListModel listModel) {
                        if (listModel == null) {
                            return;
                        }
                        if (listModel.getCode() == 0) {
                            DiscountContentModel contentModel = listModel
                                    .getMsg();
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
