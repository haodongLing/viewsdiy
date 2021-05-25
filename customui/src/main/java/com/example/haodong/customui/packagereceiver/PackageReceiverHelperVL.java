package com.example.haodong.customui.packagereceiver;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.LauncherApps;
import android.os.Build;
import android.os.UserHandle;

import java.util.HashMap;
import java.util.Map;

/**
 * describe:
 * created at 2019/2/28
 * Author linghailong
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class PackageReceiverHelperVL extends PackageReceiverHelperCompat {
    private LauncherApps mLauncherApps;

    private Map<OnAppsChangedCallbackCompat, WrappedCallback> mCallbacks = new HashMap<>();

    public PackageReceiverHelperVL(Context context) {
        super();
        mLauncherApps=(LauncherApps) context.getSystemService("launcherapps");
    }

    @Override
    public void addOnAppsChangedCallback(OnAppsChangedCallbackCompat listener) {
        WrappedCallback wrappedCallback = new WrappedCallback(listener);
        synchronized (mCallbacks) {
            mCallbacks.put(listener, wrappedCallback);
        }
        try {
            //@see https://bugly.qq.com/v2/crash-reporting/crashes/c215821c42/25958?pid=1
            //may throw deadSystemException
            mLauncherApps.registerCallback(wrappedCallback);
        } catch (Throwable ignore) {

        }
    }

    @Override
    public void removeOnAppsChangedCallback(OnAppsChangedCallbackCompat listener) {
        WrappedCallback wrappedCallback = null;
        synchronized (mCallbacks) {
            wrappedCallback = mCallbacks.remove(listener);
        }
        if (wrappedCallback != null) {
            mLauncherApps.unregisterCallback(wrappedCallback);
        }
    }

    private static class WrappedCallback extends LauncherApps.Callback {
        private PackageReceiverHelperCompat.OnAppsChangedCallbackCompat mCallback;

        public WrappedCallback(PackageReceiverHelperCompat.OnAppsChangedCallbackCompat callback) {
            mCallback = callback;
        }

        @Override
        public void onPackageRemoved(String packageName, UserHandle user) {
            mCallback.onPackageRemoved(packageName);
        }

        @Override
        public void onPackageAdded(String packageName, UserHandle user) {
            mCallback.onPackageAdded(packageName);
        }

        @Override
        public void onPackageChanged(String packageName, UserHandle user) {
            mCallback.onPackageChanged(packageName);
        }

        @Override
        public void onPackagesAvailable(String[] packageNames, UserHandle user, boolean replacing) {
            mCallback.onPackagesAvailable(packageNames, replacing);
        }

        @Override
        public void onPackagesUnavailable(String[] packageNames, UserHandle user, boolean
                replacing) {
            mCallback.onPackagesUnavailable(packageNames, replacing);
        }
    }
}
