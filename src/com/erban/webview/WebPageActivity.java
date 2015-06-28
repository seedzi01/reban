package com.erban.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.erban.R;

public class WebPageActivity extends Activity {

    private static final String ARGU_URL = "argument_url";
    
    private String url;
    private WebView webView;
    private View back;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        handleIntent();
        launchWebView();
    }
    
    private void handleIntent() {
        if (getIntent() != null) {
            url = getIntent().getStringExtra(ARGU_URL);
        }
        if (TextUtils.isEmpty(url)) {
            finish();
        }
    }
    
    public static void launch(Context context, String url) {
        Intent intent = new Intent(context, WebPageActivity.class);
        intent.putExtra(ARGU_URL, url);
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

}
