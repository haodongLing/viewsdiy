package com.example.haodong.viewday1.packagereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * describe:
 * created at 2019/2/28
 * Author linghailong
 */
public class PackageReceiverHelperV16 extends PackageReceiverHelperCompat {
    private PackageManager mPm;
    private Context mContext;
    private List<OnAppsChangedCallbackCompat> mCallbacks = new ArrayList<>();
    private PackageMonitor mPackageMonitor;

    PackageReceiverHelperV16(Context context) {
        mPm = context.getPackageManager();
        mContext = context;
        mPackageMonitor = new PackageMonitor();
    }


    synchronized List<OnAppsChangedCallbackCompat> getCallbacks() {
        return new ArrayList<>(mCallbacks);
    }

    @Override
    public void addOnAppsChangedCallback(OnAppsChangedCallbackCompat callback) {
        if (callback != null && !mCallbacks.contains(callback)) {
            mCallbacks.add(callback);
            if (mCallbacks.size() == 1) {
                registerForPackageIntents();
            }
        }
    }

    @Override
    public void removeOnAppsChangedCallback(OnAppsChangedCallbackCompat callback) {
        mCallbacks.remove(callback);
        if (mCallbacks.size() == 0) {
            unregisterForPackageIntents();
        }
    }

    private void unregisterForPackageIntents() {
        mContext.unregisterReceiver(mPackageMonitor);
    }

    private void registerForPackageIntents() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        filter.addDataScheme("package");
        mContext.registerReceiver(mPackageMonitor, filter);
        filter = new IntentFilter();
        filter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE);
        filter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE);
        mContext.registerReceiver(mPackageMonitor, filter);
    }

    class PackageMonitor extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (Intent.ACTION_PACKAGE_CHANGED.equals(action)
                    || Intent.ACTION_PACKAGE_REMOVED.equals(action)
                    || Intent.ACTION_PACKAGE_ADDED.equals(action)) {
                final String packageName = intent.getData().getSchemeSpecificPart();
                final boolean replacing = intent.getBooleanExtra(Intent.EXTRA_REPLACING, false);

                if (packageName == null || packageName.length() == 0) {
                    // they sent us a bad intent
                    return;
                }
                if (Intent.ACTION_PACKAGE_CHANGED.equals(action)) {
                    for (OnAppsChangedCallbackCompat callback : getCallbacks()) {
                        callback.onPackageChanged(packageName);
                    }
                } else if (Intent.ACTION_PACKAGE_REMOVED.equals(action)) {
                    if (!replacing) {
                        for (OnAppsChangedCallbackCompat callback : getCallbacks()) {
                            callback.onPackageRemoved(packageName);
                        }
                    }
                    // else, we are replacing the package, so a PACKAGE_ADDED will be sent
                    // later, we will update the package at this time
                } else if (Intent.ACTION_PACKAGE_ADDED.equals(action)) {
                    if (!replacing) {
                        for (OnAppsChangedCallbackCompat callback : getCallbacks()) {
                            callback.onPackageAdded(packageName);
                        }
                    } else {
                        for (OnAppsChangedCallbackCompat callback : getCallbacks()) {
                            callback.onPackageChanged(packageName);
                        }
                    }
                }
            } else if (Intent.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE.equals(action)) {
                // EXTRA_REPLACING is available Kitkat onwards. For lower devices, it is broadcasted
                // when moving a package or mounting/un-mounting external storage. Assume that
                // it is a replacing operation.
                final boolean replacing = intent.getBooleanExtra(Intent.EXTRA_REPLACING,
                        !Utilities.ATLEAST_KITKAT);
                String[] packages = intent.getStringArrayExtra(Intent.EXTRA_CHANGED_PACKAGE_LIST);
                for (OnAppsChangedCallbackCompat callback : getCallbacks()) {
                    callback.onPackagesAvailable(packages, replacing);
                }
            } else if (Intent.ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE.equals(action)) {
                // This intent is broadcasted when moving a package or mounting/un-mounting
                // external storage.
                // However on Kitkat this is also sent when a package is being updated, and
                // contains an extra Intent.EXTRA_REPLACING=true for that case.
                // Using false as default for Intent.EXTRA_REPLACING gives correct value on
                // lower devices as the intent is not sent when the app is updating/replacing.
                final boolean replacing = intent.getBooleanExtra(Intent.EXTRA_REPLACING, false);
                String[] packages = intent.getStringArrayExtra(Intent.EXTRA_CHANGED_PACKAGE_LIST);
                for (OnAppsChangedCallbackCompat callback : getCallbacks()) {
                    callback.onPackagesUnavailable(packages, replacing);
                }
            }
        }

    }
}
