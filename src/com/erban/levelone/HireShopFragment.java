package com.erban.levelone;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
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
import com.erban.view.VerticalItemAdapter;
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
                    bindZoom(filterListView);
                    break;
                case 1:
                    bindSalarey(filterListView);
                    break;
                case 2:
                    bindType();
                    break;
                default:
                    break;
                }
            }
        });
    }
    
    private void bindType() {
        // bind disntances.
        GridView gridView = new GridView(getActivity());
        VerticalItemAdapter itemAdapter = new VerticalItemAdapter();
        ExecuteItem item = new ExecuteItem();
        item.title = "服务员";
        item.icon = String.valueOf(R.drawable.fuwuyuan);
        ExecuteItem item1 = new ExecuteItem();
        item1.title = "销售";
        item1.icon = String.valueOf(R.drawable.xiaoshou);
        ExecuteItem item3 = new ExecuteItem();
        item3.title = "司机";
        item3.icon = String.valueOf(R.drawable.siji);
        ExecuteItem item4 = new ExecuteItem();
        item4.title = "技工";
        item4.icon = String.valueOf(R.drawable.jigong);
        ExecuteItem item6 = new ExecuteItem();
        item6.title = "厨师";
        item6.icon = String.valueOf(R.drawable.chushi);
        ExecuteItem item7 = new ExecuteItem();
        item7.title = "保安";
        item7.icon = String.valueOf(R.drawable.baoan);
        ExecuteItem item8 = new ExecuteItem();
        item8.title = "客服";
        item8.icon = String.valueOf(R.drawable.kefu);
        ExecuteItem item9 = new ExecuteItem();
        item9.title = "营业员";
        item9.icon = String.valueOf(R.drawable.shouying);
        ExecuteItem item10 = new ExecuteItem();
        item10.title = "快递员";
        item10.icon = String.valueOf(R.drawable.kuaidi);
        ExecuteItem item11 = new ExecuteItem();
        item11.title = "跟单员";
        item11.icon = String.valueOf(R.drawable.gendan);
        ExecuteItem item12 = new ExecuteItem();
        item12.title = "财务";
        item12.icon = String.valueOf(R.drawable.caowu);
        ExecuteItem item13 = new ExecuteItem();
        item13.title = "行政";
        item13.icon = String.valueOf(R.drawable.xingzeng);
        List<ExecuteItem> executeItems = new ArrayList<SingleItemAdapter.ExecuteItem>();
        executeItems.add(item);
        executeItems.add(item1);
        executeItems.add(item3);
        executeItems.add(item4);
        executeItems.add(item6);
        executeItems.add(item7);
        executeItems.add(item8);
        executeItems.add(item9);
        executeItems.add(item10);
        executeItems.add(item11);
        executeItems.add(item12);
        executeItems.add(item13);
        itemAdapter.setItems(executeItems);
        gridView.setAdapter(itemAdapter);
        gridView.setGravity(Gravity.CENTER);
        gridView.setNumColumns(4);
        popupWindow.setContentView(gridView);
        popupWindow.showAsDropDown(tabs.get(0));
    }

    private void bindSalarey(ListView listview) {

        // bind disntances.
        SingleItemAdapter itemAdapter = new SingleItemAdapter();
        ExecuteItem item = new ExecuteItem();
        item.title = "不限";
        ExecuteItem item1 = new ExecuteItem();
        item1.title = "面议";
        ExecuteItem item2 = new ExecuteItem();
        item2.title = "2000元以下";
        ExecuteItem item3 = new ExecuteItem();
        item3.title = "2000-3000元";
        ExecuteItem item4 = new ExecuteItem();
        item4.title = "3000-5000元";
        ExecuteItem item5 = new ExecuteItem();
        item5.title = "5000-8000元";
        List<ExecuteItem> executeItems = new ArrayList<SingleItemAdapter.ExecuteItem>();
        executeItems.add(item);
        executeItems.add(item1);
        executeItems.add(item2);
        executeItems.add(item3);
        executeItems.add(item4);
        executeItems.add(item5);
        itemAdapter.setItems(executeItems);
        listview.setAdapter(itemAdapter);
        popupWindow.setContentView(filterListView);

        popupWindow.showAsDropDown(tabs.get(0));
    }
    
    private void bindZoom(ListView listview) {

        // bind disntances.
        SingleItemAdapter itemAdapter = new SingleItemAdapter();
        ExecuteItem item = new ExecuteItem();
        item.title = "全北京";
        ExecuteItem item1 = new ExecuteItem();
        item1.title = "海淀";
        ExecuteItem item2 = new ExecuteItem();
        item2.title = "朝阳";
        ExecuteItem item3 = new ExecuteItem();
        item3.title = "东城";
        ExecuteItem item4 = new ExecuteItem();
        item4.title = "崇文";
        ExecuteItem item5 = new ExecuteItem();
        item5.title = "丰台";
        List<ExecuteItem> executeItems = new ArrayList<SingleItemAdapter.ExecuteItem>();
        executeItems.add(item);
        executeItems.add(item1);
        executeItems.add(item2);
        executeItems.add(item3);
        executeItems.add(item4);
        executeItems.add(item5);
        itemAdapter.setItems(executeItems);
        listview.setAdapter(itemAdapter);
        popupWindow.setContentView(filterListView);

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