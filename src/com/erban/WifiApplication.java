package com.erban;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.erban.api.WifiApi;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.yuekuapp.proxy.ControlFactory;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by huzhi on 15-2-16.
 */
public class WifiApplication extends Application{

    private static WifiApplication instance;

    private static Context appContext;
    
    public synchronized static WifiApplication getInstance() {
        if(instance == null){
            instance = new WifiApplication();
        }
        return instance;
    }
    
    private WifiApi api;
    
    private LbsManager mLbsManager;
    
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appContext = this.getApplicationContext();
        api = new WifiApi("Wifi1.0", this);
        ControlFactory.init(this);
        initImageLoader(this);
        
        mLbsManager = LbsManager.getInstance(this);
        mLbsManager.start();
    }
    
    @Override
    public void onTerminate() {
    	super.onTerminate();
    	if(mLbsManager!=null)
    		mLbsManager.stop();
    }

    private void initImageLoader(Context context) {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
        .cacheInMemory(true)
        .cacheOnDisc(true)
        .considerExifParams(true)
        .bitmapConfig(Bitmap.Config.RGB_565)
        .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCacheExtraOptions(480, 1600)
                .build();
        ImageLoader.getInstance().init(config);
    }
    
    public WifiApi getApi(){
        return api;
    }
    
    private  Activity mCurrentActivity;
    
    public void setCurrentActivity(Activity ac){
        mCurrentActivity = ac;
    }
    
    public Activity getCurrentActivity(){
        return mCurrentActivity;
    } 

    private static RequestQueue reqQueue;
    
    public static RequestQueue getRequestQueue() {
    	if (reqQueue == null) {
    		reqQueue = Volley.newRequestQueue(appContext);
    	}
        return reqQueue;
    }

}