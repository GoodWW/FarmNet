package com.cdzp.farmnet.utils;

import android.content.Context;
import android.content.res.Resources;

/**
 * 作者：张人文
 * 时间：2020/2/24 09:25
 * 邮箱：479696877@QQ.COM
 * 描述：工具类
 */
public class UUtils {
    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }
}
