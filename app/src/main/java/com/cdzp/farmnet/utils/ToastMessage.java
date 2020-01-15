package com.cdzp.farmnet.utils;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.cdzp.farmnet.application.MyBaseApplication;

import es.dmoral.toasty.Toasty;

/**
 * 作者：张人文
 * 时间：2020/1/15 16:38
 * 邮箱：479696877@QQ.COM
 * 描述：toasty的封装
 */
public class ToastMessage {

    /**
     * 弹出成功消息
     *
     * @param text       需要显示的消息
     * @param isShowIcon 是否需要显示图标 默认显示
     */
    public static void toastSuccess(@NonNull String text, Boolean isShowIcon) {
        if (isShowIcon == null) {
            isShowIcon = true;
        }
        Toasty.success(MyBaseApplication.getContext(), text, Toast.LENGTH_SHORT, isShowIcon).show();
    }

    /**
     * 弹出错误消息
     *
     * @param text       需要显示的消息
     * @param isShowIcon 是否需要显示图标 默认显示
     */
    public static void toastError(@NonNull String text, Boolean isShowIcon) {
        if (isShowIcon == null) {
            isShowIcon = true;
            Toasty.error(MyBaseApplication.getContext(), text, Toast.LENGTH_SHORT, isShowIcon).show();
        }
    }

    /**
     * 弹出一般消息
     *
     * @param text 需要显示的消息
     */
    public static void toastNormal(@NonNull String text) {
        Toasty.normal(MyBaseApplication.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出警告消息
     *
     * @param text       需要显示的消息
     * @param isShowIcon 是否需要显示图标 默认显示
     */
    public static void toastWarn(@NonNull String text, Boolean isShowIcon) {
        if (isShowIcon == null) {
            isShowIcon = true;
        }
        Toasty.warning(MyBaseApplication.getContext(), text, Toast.LENGTH_SHORT, isShowIcon).show();
    }
}

