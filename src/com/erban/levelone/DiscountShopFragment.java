package com.erban.levelone;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchDiscountList();
    }
    
    protected void initTabs(List<FilterView> tabs) {
        tabs.get(0).setTitle(getString(R.string.distance));
        tabs.get(0).setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                bindDistances();
            }
        });
        tabs.get(1).setTitle(getString(R.string.category));
        tabs.get(2).setTitle(getString(R.string.sort));
    }

    protected void showHeader(NormalGoods topGoods) {
        
        View header = ViewUtils.newInstance(shopListView, R.layout.view_shop_banner);
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

    private void bindDistances() {
        // bind disntances.
        SingleItemAdapter itemAdapter = new SingleItemAdapter();
        ExecuteItem item = new ExecuteItem();
        item.title = "hulan";
        ExecuteItem item1 = new ExecuteItem();
        item1.title = "fujian";
        ExecuteItem item2 = new ExecuteItem();
        item2.title = "beijing";
        List<ExecuteItem> executeItems = new ArrayList<SingleItemAdapter.ExecuteItem>();
        executeItems.add(item);
        executeItems.add(item1);
        executeItems.add(item2);
        itemAdapter.setItems(executeItems);
        choicesListView.setVisibility(View.VISIBLE);
        choicesListView.setAdapter(itemAdapter);
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
                            DiscountContentModel contentModel = listModel.getMsg();
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
