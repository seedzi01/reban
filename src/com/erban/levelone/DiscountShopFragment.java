package com.erban.levelone;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.erban.R;
import com.erban.bean.NormalGoods;
import com.erban.bean.TopGoods;
import com.erban.util.ViewUtils;
import com.erban.view.FilterView;
import com.erban.view.ShopAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DiscountShopFragment extends BaseShopFragment {

    protected void initTabs(List<FilterView> tabs) {
        tabs.get(0).setTitle(getString(R.string.distance));
        tabs.get(1).setTitle(getString(R.string.category));
        tabs.get(2).setTitle(getString(R.string.sort));
    }

    @Override
    protected void initHeader() {
        
        TopGoods topGoods = new TopGoods();
        topGoods.logo = "http://img4.duitang.com/uploads/blog/201307/18/20130718212246_V3JvZ.jpeg";
        topGoods.dprice = "80";
        topGoods.price = "￥120";
        topGoods.mctdesc = "阿菲儿咖啡馆";
        topGoods.goodsdesc = "双人套餐，免费WIFI";
        
        View header = ViewUtils.newInstance(shopListView, R.layout.view_shop_banner);
        // load background image.
        ImageView banner = (ImageView) header.findViewById(R.id.banner_bg);
        ImageLoader.getInstance().displayImage(topGoods.logo, banner);
        // set title & goods description.
        TextView title = (TextView) header.findViewById(R.id.title);
        title.setText(topGoods.mctdesc);
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
    
    @Override
    protected void initItems() {

        NormalGoods first = new NormalGoods();
        first.logo = "http://pic10.nipic.com/20101014/1341920_092555096491_2.jpg";
        first.gets = "200人已領取";
        first.desc = "【8店通用】，非常棒的体验，就算什么都不能阻挡，就这样吧";
        first.dprice = "257";
        first.price = "500";
        first.goods = "咖啡時刻";

        NormalGoods second = new NormalGoods();
        second.logo = "http://pic.nipic.com/2008-04-14/200841493417619_2.jpg";
        second.gets = "200人已領取";
        second.desc = "【8店通用】，非常棒的体验，就算什么都不能阻挡，就这样吧";
        second.dprice = "257";
        second.price = "500";
        second.goods = "咖啡時刻";

        NormalGoods third = new NormalGoods();
        third.logo = "http://pic1.nipic.com/2009-02-25/20092259826287_2.jpg";
        third.gets = "200人已領取";
        third.desc = "【8店通用】，非常棒的体验，就算什么都不能阻挡，就这样吧";
        third.dprice = "257";
        third.price = "500";
        third.goods = "咖啡時刻";

        NormalGoods goods = new NormalGoods();
        goods.logo = "http://pic1a.nipic.com/2008-10-15/2008101510153681_2.jpg";
        goods.gets = "200人已領取";
        goods.desc = "【8店通用】，非常棒的体验，就算什么都不能阻挡，就这样吧";
        goods.dprice = "257";
        goods.price = "500";
        goods.goods = "咖啡時刻";

        ShopAdapter adapter = new ShopAdapter();
        List<NormalGoods> items = new ArrayList<NormalGoods>();
        items.add(first);
        items.add(second);
        items.add(third);
        items.add(goods);
        items.add(third);
        items.add(first);
        adapter.setItems(items);
        shopListView.setAdapter(adapter);
    }
}
