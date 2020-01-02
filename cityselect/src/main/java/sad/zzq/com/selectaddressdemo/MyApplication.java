package sad.zzq.com.selectaddressdemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import org.xutils.common.util.LogUtil;

import static org.xutils.x.Ext.setDebug;

/**
 *
 * Created by zzq on 2016/12/2 0002.
 */

public  class MyApplication extends Application {
    private static MyApplication instance = null;
    private static Context context;
    private boolean isEnglish;
    private static String TAG = "MyApplication";


    @Override
    public void onCreate() {
        super.onCreate();

        setDebug(true);
        instance = this;
        context = getApplicationContext();
        if (getSharedPreferences("userInfo", Context.MODE_PRIVATE)
                .getString("Language", "").equals("English")) {
            isEnglish = true;
        } else {
            isEnglish = false;
        }

        //监听该应用所有Activity的生命周期
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

                LogUtil.e(activity.getComponentName().getClassName()+"      执行  Created");
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                LogUtil.e(activity.getComponentName().getClassName()+"      执行  onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                LogUtil.e(activity.getComponentName().getClassName()+"      执行  onActivityPaused");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                LogUtil.e(activity.getComponentName().getClassName()+"      执行  onActivityStopped");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                LogUtil.e(activity.getComponentName().getClassName()+"    执行  Destroyed");
            }
        });
        createStart();
    }


    public static Context getContext() {
        return context;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public  void createStart(){

    };

}
