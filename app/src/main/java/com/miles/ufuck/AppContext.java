package com.miles.ufuck;

import com.miles.ufuck.base.BaseApplication;

import java.util.Properties;

/**
 * 描述：全局应用程序类，用于保存和调用全局应用配置及访问网络数据
 * Created by milesloner on 2016/12/8.
 * author tianpc0318@163.com
 */

public class AppContext extends BaseApplication {

    private static AppContext instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /**
     * 获得当前app运行的AppContext
     *
     * @return
     */
    public static AppContext getInstance() {
        return instance;
    }

    public Properties getProperties() {
        return AppConfig.getAppConfig(this).get();
    }

    public void setProperty(String key, String value) {
        AppConfig.getAppConfig(this).set(key, value);
    }

    /**
     * 是否是首次启动
     */
    public static boolean isFirstStart() {
        return getPreferences().getBoolean(AppConfig.KEY_FIRST_START, false);
    }

    /**
     * 夜间模式
     */
    public static boolean getNightModeSwitch() {
        return getPreferences().getBoolean(AppConfig.KEY_NIGHT_MODE_SWITCH, false);
    }

    /**
     * 设置夜间模式
     */
    public static void setNightModeSwitch(boolean on) {

    }

}
