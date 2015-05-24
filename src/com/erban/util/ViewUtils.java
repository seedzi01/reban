package com.erban.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewUtils {

	public static View newInstance(ViewGroup parent, int resId) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		return inflater.inflate(resId, parent, false);
	}

	public static View newInstance(LayoutInflater inflater, ViewGroup parent,
			int resId) {
		return inflater.inflate(resId, parent, false);
	}

}
