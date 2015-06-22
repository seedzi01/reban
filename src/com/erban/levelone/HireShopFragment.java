package com.erban.levelone;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

    private NormalCompany generateMockCompany() {
        NormalCompany company = new NormalCompany();
        company.title = "销售招聘";
        company.salary = "5000-10000元";
        company.exp = "5人 焊工，钳工";
        company.firm = "北京味多美公司";
        company.address = "国贸-北京";
        company.uptime = "06-15";
        return company;
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