package com.erban.webview;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.erban.R;
import com.erban.WifiApplication;
import com.erban.bean.FavBean;
import com.erban.util.UserUtil;
import com.erban.util.ViewUtils;
import com.erban.volley.GsonRequest;
import com.erban.volley.HttpUrls;

public class WebPageActivity extends Activity {

    private static final String ARGU_URL = "argument_url";
    private static final String ARGU_SALES_ID = "argument_sales_id";
    
    private String url;
    private String salesId;
    private String favId;

    private WebView webView;
    private View back;
    private ImageView collection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        
        handleIntent();
        
        back = findViewById(R.id.back);
        collection = (ImageView) findViewById(R.id.collection);
        back.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (!TextUtils.isEmpty(salesId)) {
            collection.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    toggleFav();
                }
            });
            updateFavIcon();
        } else {
            collection.setVisibility(View.GONE);
        }
        launchWebView();
    }
    
    private void handleIntent() {
        if (getIntent() != null) {
            url = getIntent().getStringExtra(ARGU_URL);
            salesId = getIntent().getStringExtra(ARGU_SALES_ID);
        }
        if (TextUtils.isEmpty(url)) {
            finish();
        }
    }

    public static void launch(Context context, String url) {
        launch(context, url, null);
    }

    public static void launch(Context context, String url, String salesId) {
        Intent intent = new Intent(context, WebPageActivity.class);
        intent.putExtra(ARGU_URL, url);
        if (!TextUtils.isEmpty(salesId)) {
            intent.putExtra(ARGU_SALES_ID, salesId);
        }
        context.startActivity(intent);
    }
    
    private void launchWebView(){
       webView = (WebView) findViewById(R.id.webView);
        //WebView加载web资源
       webView.loadUrl(url);
       WebSettings settings = webView.getSettings();
       settings.setJavaScriptEnabled(true);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
       webView.setWebViewClient(new WebViewClient(){
           @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
               //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
             view.loadUrl(url);
            return true;
        }
       });
    }

    private void toggleFav() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userid", UserUtil.getUser().getUserInfo().getUserid());
        params.put("token", UserUtil.getUser().getToken());
        params.put("saleid", salesId);
        if (!TextUtils.isEmpty(favId)) {
            params.put("favid", favId);
        }

        GsonRequest<FavBean> gsonRequest = new GsonRequest<FavBean>(
                Request.Method.POST, 
                TextUtils.isEmpty(favId) ? HttpUrls.getAddFav() : HttpUrls.getDelFav(), 
                FavBean.class, params,
                new Response.Listener<FavBean>() {

                    @Override
                    public void onResponse(FavBean fav) {
                        ViewUtils.showShortToast(R.string.fav_success);
                        // TODO update fav icon.
                        updateFavIcon();
                    }

                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError arg0) {
                        ViewUtils.showShortToast(R.string.fav_failed);
                    }

                });
        WifiApplication.getRequestQueue().add(gsonRequest);
    }

    private void updateFavIcon() {
        if (!TextUtils.isEmpty(favId)) {
            collection.setImageResource(R.drawable.already_fav);
        } else {
            collection.setImageResource(R.drawable.favorite);
        }
    }
}
