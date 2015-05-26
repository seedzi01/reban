package com.yuekuapp.proxy.callback;

import java.lang.reflect.Method;

public interface Interceptor {

	public Object intercept(final Object proxy, Method method,
			final Object[] args) throws Throwable;
	
}
