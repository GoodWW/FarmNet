package com.cdzp.farmnet.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseView;

/**
 * 作者：张人文
 * 时间：2020/1/9 17:10
 * 邮箱：479696877@QQ.COM
 * 描述：倒计时时间类
 */
public class CountDownTimerUtils extends CountDownTimer {

    private TextView mTextView; //显示倒计时的文字
    private BaseView context;

    /**
     * @param textView          The TextView
     * @param millisInFuture     millisInFuture  从开始调用start()到倒计时完成
     *                           并onFinish()方法被调用的毫秒数。（译者注：倒计时时间，单位毫秒）
     * @param countDownInterval 接收onTick(long)回调的间隔时间。（译者注：单位毫秒）
     */
    public CountDownTimerUtils(BaseView context, TextView textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mTextView = textView;
        this.context = context;
    }



    @Override
    public void onTick(long millisUntilFinished) {

        mTextView.setClickable(false); //设置不可点击
        mTextView.setText(context.getResources().getText(R.string.str_get_code)+"（"+millisUntilFinished / 1000 + "）");  //设置倒计时时间
//        mTextView.setBackgroundResource(R.drawable.validate_code_press_bg); //设置按钮为灰色，这时是不能点击的

        /**
         * 超链接 URLSpan
         * 文字背景颜色 BackgroundColorSpan
         * 文字颜色 ForegroundColorSpan
         * 字体大小 AbsoluteSizeSpan
         * 粗体、斜体 StyleSpan
         * 删除线 StrikethroughSpan
         * 下划线 UnderlineSpan
         * 图片 ImageSpan
         * http://blog.csdn.net/ah200614435/article/details/7914459
         */
//        SpannableString spannableString = new SpannableString(mTextView.getText().toString());  //获取按钮上的文字
//        ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
        /**
         * public void setSpan(Object what, int start, int end, int flags) {
         * 主要是start跟end，start是起始位置,无论中英文，都算一个。
         * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
         */
//        spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时的时间设置为红色
//        mTextView.setText(spannableString);
    }

    @Override
    public void onFinish() {
        mTextView.setText(context.getResources().getText(R.string.str_get_code_two));
        mTextView.setClickable(true);//重新获得点击
//        mTextView.setBackgroundResource(R.drawable.validate_code_normal_bg);  //还原背景色
    }
}
