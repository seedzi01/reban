package com.erban.view;

import com.erban.R;
import com.erban.WifiApplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FilterView extends RelativeLayout {

    private View bottomLine;
    private View rightLine;
    private TextView titleView;

    public FilterView(Context context) {
        super(context);
    }

    public FilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        bottomLine = findViewById(R.id.bottom_line);
        rightLine = findViewById(R.id.right_line);
        titleView = (TextView) findViewById(R.id.title);
    }

    public void setSelected(boolean selected) {
        bottomLine.setVisibility(selected ? View.VISIBLE : View.GONE);
        Drawable rightIcon = getResources().getDrawable(
                selected ? R.drawable.pull_up : R.drawable.pull_down);
        titleView.setTextColor(selected 
                ? WifiApplication.getInstance().getResources().getColor(R.color.font_module_pressed) 
                : WifiApplication.getInstance().getResources().getColor(R.color.color_app_black));
        rightIcon.setBounds(0, 0, rightIcon.getMinimumWidth(), rightIcon.getMinimumHeight());
        titleView.setCompoundDrawables(
                null, null, rightIcon, null);
        invalidate();
    }

    public void setRightLineVisibility(boolean visible) {
        rightLine.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
    
    public void setTitle(CharSequence title) {
        titleView.setText(title);
    }
}
