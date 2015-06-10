package com.erban.util;

import com.erban.DebugRelease;


public class Log {
    
    public static void d(String tag,String msg){
        if(DebugRelease.isDebug){
            android.util.Log.d(tag, msg);
        }
    }

}
