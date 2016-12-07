package com.miles.ufuck;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * 描述：Activity的管理类
 * Created by milesloner on 2016/12/8.
 * author tianpc0318@163.com
 */

public class AppManager {
    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * 单例模式
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 将启动的Activity存入堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 从堆栈中取出当前的Activity(栈顶元素), 当不销毁栈顶元素
     */
    public Activity currentActivity() {
        return activityStack.lastElement();
    }

    /**
     * 结束当前Activity(栈顶元素)
     */
    public void finishActivity() {
        Activity activity = activityStack.pop();
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束特定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 获取指定的Activity，没有则为null
     */
    public Activity getActivity(Class<?> cls) {
        if (null != activityStack) {
            for (Activity activity : activityStack) {
                if (null != activity && activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        }
        return null;
    }

    /**
     * 结束所有的Activities
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                finishActivity(activityStack.get(i));
            }
        }
        activityStack.clear();
    }

    /**
     * 退出App
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            // 杀死该应用程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {

        }
    }

}
