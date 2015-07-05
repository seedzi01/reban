package com.erban;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.GeofenceClient;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.erban.bean.Location;

public class LbsManager {
	
	
	private static LbsManager mInstance;
	
	private LbsManager(Context context){
		onCreate(context);
	}
	
	public static LbsManager getInstance(Context context){
		if(mInstance == null){
			mInstance = new LbsManager(context);
		}
		return mInstance;
	}

	
	public LocationClient mLocationClient;
	public GeofenceClient mGeofenceClient;
	public MyLocationListener mMyLocationListener;
	public Vibrator mVibrator;
	
	private String tempcoor="gcj02";
	private Location mLocation;
	
	public void onCreate(Context context){
		mLocationClient = new LocationClient(context);
		mMyLocationListener = new MyLocationListener();
		mLocationClient.registerLocationListener(mMyLocationListener);
		mGeofenceClient = new GeofenceClient(context);
		mVibrator =(Vibrator)context.getSystemService(Service.VIBRATOR_SERVICE);
		mLocation = new Location();
	}
	
	
	
	/**
	 * 实现实位回调监听 (lbs)
	 */
	public class MyLocationListener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
			//Receive Location 
			StringBuffer sb = new StringBuffer(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getLocType());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			sb.append("\nradius : ");
			sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation){
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
				sb.append("\ndirection : ");
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				sb.append(location.getDirection());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				//运营商信息
				sb.append("\noperationers : ");
				sb.append(location.getOperators());
			}
			Log.d("BaiduLocationApiDem", sb.toString());
			mLocation.setmLatitude(String.valueOf(location.getLatitude()));
			mLocation.setmLongitude(String.valueOf(location.getLongitude()));
		}
	}
	
	public void start(){
		InitLocation();
		mLocationClient.start();
	}
	
	public void stop(){
		mLocationClient.stop();
	}
	
	private void InitLocation(){
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);//设置定位模式
		option.setCoorType(tempcoor);//返回的定位结果是百度经纬度，默认值gcj02
		int span=1000;
		try {
			span = 1000*60;//1分钟
		} catch (Exception e) {
			// TODO: handle exception
		}
		option.setScanSpan(span);//设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);
		mLocationClient.setLocOption(option);
	}
	
	public Location getLocation(){
		return mLocation;
	}
}
