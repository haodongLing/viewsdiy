package com.example.haodong.viewday1.packagereceiver;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;


/**
 * describe:
 * created at 2019/2/28
 * Author linghailong
 */
public abstract class PackageReceiverHelperCompat {
    public static final String ACTION_MANAGED_PROFILE_ADDED =
            "android.intent.action.MANAGED_PROFILE_ADDED";
    public static final String ACTION_MANAGED_PROFILE_REMOVED =
            "android.intent.action.MANAGED_PROFILE_REMOVED";

    public interface OnAppsChangedCallbackCompat {
        void onPackageRemoved(String packageName);

        void onPackageAdded(String packageName);

        void onPackageChanged(String packageName);

        void onPackagesAvailable(String[] packageNames, boolean replacing);

        void onPackagesUnavailable(String[] packageNames, boolean replacing);
    }

    protected PackageReceiverHelperCompat() {
    }

    private volatile static PackageReceiverHelperCompat sInstance;
    private static final Object sInstanceLock = new Object();

    public static PackageReceiverHelperCompat getsInstance(Context context) {
        if (sInstance == null) {
            if (Utilities.ATLEAST_LOLLIPOP) {
                sInstance = new PackageReceiverHelperVL(context.getApplicationContext());
            } else {
                sInstance = new PackageReceiverHelperV16(context.getApplicationContext());
            }
        }
        return sInstance;
    }

    public abstract void addOnAppsChangedCallback(OnAppsChangedCallbackCompat listener);

    public abstract void removeOnAppsChangedCallback(OnAppsChangedCallbackCompat listener);

    public boolean isAppEnabled(PackageManager pm, String packageName, int flags) {
        try {
            //所有使用PackageManager的地方都得try一下 DeadSystemObject
            ApplicationInfo info = pm.getApplicationInfo(packageName, flags);
            return info != null && info.enabled;
        } catch (Throwable e) {
            return false;
        }
    }
}
