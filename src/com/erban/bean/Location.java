package com.erban.bean;

public class Location {

	private String mLatitude = "";
	
	private String mLongitude = "";

	public String getmLatitude() {
		return mLatitude;
	}

	public void setmLatitude(String mLatitude) {
		this.mLatitude = mLatitude;
	}

	public String getmLongitude() {
		return mLongitude;
	}

	public void setmLongitude(String mLongitude) {
		this.mLongitude = mLongitude;
	}
	
	public static void save(Location location){
		//TODO
	}
	
}
