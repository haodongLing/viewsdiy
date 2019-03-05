package com.example.disignmode.mysingleton;

import android.app.Activity;

import java.util.Stack;

/**
 * description:
 * author: linghaoDo
 * date: 2019/3/4
 */
public class ActivityManager {
    private ActivityManager() {
    }

    private static final class Holder {
        private static final ActivityManager INSTANCE = new ActivityManager();
    }

    public ActivityManager getInstance() {
        return Holder.INSTANCE;
    }

    // 集合用谁 List LinkedList Stack  ?? 删除和添加比较多
    private Stack<Activity> mActivities;

    /*dcl*/
//    private static volatile ActivityManager sInstance;
//    public ActivityManager getsInstance(){
//        if (sInstance==null){
//            synchronized (ActivityManager.class){
//                if (sInstance==null){
//                    sInstance=new ActivityManager();
//                }
//            }
//        }
//        return sInstance;
//    }
    public void attach(Activity activity) {
        mActivities.add(activity);
    }

    public void detach(Activity detachActivity) {
        // for 去移除有没有问题？ 一边循环一边移除会出问题 ，
        // 既然这个写法有问题，自己又想不到什么解决方法，参考一下别人怎么写的
        /*for (Activity activity : mActivities) {
            if(activity == detachActivity){
                mActivities.remove(activity);
            }
        }*/
        int size = mActivities.size();
        for (int i = 0; i < size; i++) {
            Activity activity = mActivities.get(i);
            if (activity == detachActivity) {
                mActivities.remove(i);
                i--;
                size--;
            }
        }
    }

    public void finish(Activity finishActivity) {
        int size = mActivities.size();
        for (int i = 0; i < size; i++) {
            Activity activity = mActivities.get(i);
            if (activity == finishActivity) {
                mActivities.remove(activity);
                i--;
                size--;
            }
        }
    }

    public void finish(Class<? extends Activity> activityClass) {
        int size = mActivities.size();
        for (int i = 0; i < size; i++) {
            Activity activity = mActivities.get(i);
            if (activity.getClass().getSimpleName().equals(activityClass.getSimpleName())) {
                mActivities.remove(activity);
                i--;
                size--;
            }
        }


    }

    /**
     * 退出整个应用
     */
    public void exitApplication() {

    }

    /**
     * 获取当前的Activity（最前面）
     *
     * @return
     */
    public Activity currentActivity() {
        return mActivities.lastElement();
    }


}
