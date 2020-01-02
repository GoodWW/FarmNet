package com.cdzp.farmnet.utils;

import android.util.Log;

import com.cdzp.farmnet.config.Envs;

/**
 * 作者：张人文
 * 日期：2019/11/5 9:38
 * 邮箱：479696877@QQ.COM
 * 描述：Log工具类
 */
public class LogUtil {
    public static void i(String tag, String info) {
        if (Envs.isDebug) {
            Log.i(tag, info);
        }
    }

    public static void d(String tag, String info) {
        if (Envs.isDebug) {
            Log.d(tag, info);
        }
    }

    public static void e(String tag, String info) {
        if (Envs.isDebug) {
            Log.e(tag, info);
        }
    }


    public static void w(String tag, String info) {
        if (Envs.isDebug) {
            Log.w(tag, info);
        }
    }
}
