package com.kuangch.selectorImageView;

import android.util.Log;

/**
 * Created by KuangCH on 2015/11/21.
 */
public class MyLogger {

    private static boolean isLogOpen = true;

    public static void i (String tag,String msg){
        if(isLogOpen)
            Log.i(tag,msg);
    }

}
