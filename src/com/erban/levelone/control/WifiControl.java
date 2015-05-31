package com.erban.levelone.control;

import com.erban.levelone.model.WifiModel;
import com.yuekuapp.BaseControl;
import com.yuekuapp.annotations.AsynMethod;
import com.yuekuapp.proxy.MessageProxy;

public class WifiControl extends BaseControl {

	private WifiModel mModel;
	public WifiControl(MessageProxy mMethodCallBack) {
		super(mMethodCallBack);
		mModel = new WifiModel();
	}
	
	@AsynMethod
	public void fetchData(){
		try {
			Thread.sleep(3000);
			mModel.setResult(1);
			sendMessage("callback");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public WifiModel getModel(){
		return mModel;
	}

}
