package com.erban.levelone;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.erban.R;
import com.erban.bean.NormalCompany;
import com.erban.bean.NormalGoods;
import com.erban.bean.TopCompany;
import com.erban.util.ViewUtils;
import com.erban.view.FilterView;
import com.erban.view.HireAdapter;
import com.erban.view.ShopAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

public class HireShopFragment extends BaseShopFragment {

    @Override
    protected void initTabs(List<FilterView> tabs) {
        tabs.get(0).setTitle(getString(R.string.zoom));
        tabs.get(1).setTitle(getString(R.string.pay));
        tabs.get(2).setTitle(getString(R.string.category));
    }

    @Override
    protected void initHeader() {
        TopCompany topCompany = new TopCompany();
        topCompany.logo = "http://www.kchaiguang.com/kchg/images/DSC_0882.JPG";
        topCompany.name = "北京海光儀器公司";
        topCompany.detail = "招聘運營專員";
        topCompany.salary = "5000-8000";
        
        View header = ViewUtils.newInstance(shopListView, R.layout.view_shop_banner);
        // load background image.
        ImageView banner = (ImageView) header.findViewById(R.id.banner_bg);
        ImageLoader.getInstance().displayImage(topCompany.logo, banner);
        // set title & goods description.
        TextView title = (TextView) header.findViewById(R.id.title);
        title.setText(topCompany.name);
        TextView desc = (TextView) header.findViewById(R.id.desc);
        desc.setText(topCompany.detail);
        TextView discount = (TextView) header.findViewById(R.id.discount);
        discount.setText(topCompany.salary);
        shopListView.addHeaderView(header);
    }

    @Override
    protected void initItems() {

        HireAdapter adapter = new HireAdapter();
        List<NormalCompany> items = new ArrayList<NormalCompany>();
        items.add(generateMockCompany());
        items.add(generateMockCompany());
        items.add(generateMockCompany());
        items.add(generateMockCompany());
        items.add(generateMockCompany());
        items.add(generateMockCompany());
        items.add(generateMockCompany());
        adapter.setItems(items);
        shopListView.setAdapter(adapter);
    }

    private NormalCompany generateMockCompany() {
        NormalCompany company = new NormalCompany();
        company.title = "销售招聘";
        company.salary = "5000-10000元";
        company.detail = "5人 焊工，钳工";
        company.company = "北京味多美公司";
        company.address = "国贸-北京";
        company.date = "06-15";
        return company;
    }
    
}