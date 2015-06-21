package com.erban.levelone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erban.R;
import com.erban.util.ViewUtils;

/**
 * Container will contain two fragment.
 * 1. for ShopFragment.
 * 2. for HireFragment
 */
public class ShopContainerFragment extends Fragment {

    private TextView shopTab;
    private TextView hireTab;
    private View shopLine;
    private View hireLine;

    private DiscountShopFragment shopFragment;
    private HireShopFragment hireFragment;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return ViewUtils.newInstance(inflater, container, R.layout.fragment_shop_container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        shopTab = (TextView) view.findViewById(R.id.shop_tab);
        hireTab = (TextView) view.findViewById(R.id.hire_tab);
        shopLine = view.findViewById(R.id.shop_white_line);
        hireLine = view.findViewById(R.id.hire_white_line);
        
        select(true);
        shopTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select(true);
                updateFragment(true);
            }
        });
        hireTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select(false);
                updateFragment(false);
            }
        });
        updateFragment(true);
    }

    private void select(boolean isShop) {
        shopTab.setSelected(isShop);
        hireTab.setSelected(!isShop);
        shopLine.setVisibility(isShop ? View.VISIBLE : View.GONE);
        hireLine.setVisibility(isShop ? View.GONE : View.VISIBLE);
    }

    private void updateFragment(boolean isShop) {
        Fragment displayFragment;
        if (isShop) {
            if (shopFragment == null) {
                shopFragment = new DiscountShopFragment();
            }
            displayFragment = shopFragment;
        } else {
            if (hireFragment == null) {
                hireFragment = new HireShopFragment();
            }
            displayFragment = hireFragment;
        }
        getChildFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, displayFragment)
            .commit();
    }
}