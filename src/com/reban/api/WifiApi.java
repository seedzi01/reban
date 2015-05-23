package com.reban.api;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.reban.api.exception.XiaoMeiCredentialsException;
import com.reban.api.exception.XiaoMeiIOException;
import com.reban.api.exception.XiaoMeiJSONException;
import com.reban.api.exception.XiaoMeiOtherException;
import com.reban.api.http.AbstractHttpApi;
import com.reban.api.http.HttpApi;
import com.reban.api.http.HttpApiWithSession;

/**
 * Created by huzhi on 15-2-17.
 */
public class WifiApi {

    private HttpApi mHttpApi;
    private HttpUrlManager urlManager;

    public WifiApi(String clientVersion, Context mContext) {
        mHttpApi = new HttpApiWithSession(AbstractHttpApi.createHttpClient(),
                clientVersion);
        urlManager = new HttpUrlManager();
    }

	
}
