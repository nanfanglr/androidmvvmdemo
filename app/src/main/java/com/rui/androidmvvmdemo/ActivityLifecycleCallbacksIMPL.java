package com.rui.androidmvvmdemo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import timber.log.Timber;


/**
 * Activity生命周期监听接口：ActivityLifecycleCallbacks使用场景
 * https://blog.csdn.net/jaconwong07/article/details/53462682
 * 1. 需求场景
 * 项目遇到新需求：给App设置指纹解锁或者手势密码解锁，例如，App切换至后台或者进程关闭，时间如果超过20sec，
 * App再次切换至前台或者重新打开，要让用户验证指纹或者手势密码，验证通过的话才能进入home页，当然，具体的时间长短，后端可配。
 * 2. 方案分析
 * 记录App切换至后台或进程被杀死的时间点是解决问题的关键点一，假设这个时间点是T1；
 * 记录App再次切换至前台或者重新打开的时间点是解决问题的关键点二，假设这个时间点是T2。
 * 只要能准确的记录T1和T2，上述需求就迎刃而解(假设后端配置的时间间隔为timeSpan)。
 * if (T1-T2>timeSpan){
 * // 超时，验证指纹或者手势密码
 * }
 */
public class ActivityLifecycleCallbacksIMPL implements Application.ActivityLifecycleCallbacks {


    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        Timber.d("ActivityLifecycleCallbacks.onActivityCreated=" + activity.getClass().getSimpleName());

    }

    @Override
    public void onActivityStarted(Activity activity) {
        Timber.d("ActivityLifecycleCallbacks.onActivityStarted=" + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Timber.d("ActivityLifecycleCallbacks.onActivityResumed=" + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Timber.d("ActivityLifecycleCallbacks.onActivityPaused=" + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Timber.d("ActivityLifecycleCallbacks.onActivityStopped=" + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Timber.d("ActivityLifecycleCallbacks.onActivitySaveInstanceState=" + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Timber.d("ActivityLifecycleCallbacks.onActivityDestroyed=" + activity.getClass().getSimpleName());
    }

}
