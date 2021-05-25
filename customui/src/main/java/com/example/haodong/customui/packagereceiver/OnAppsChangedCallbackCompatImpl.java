package com.example.haodong.customui.packagereceiver;

/**
 * describe:
 * created at 2019/2/28
 * Author linghailong
 */
public class OnAppsChangedCallbackCompatImpl implements PackageReceiverHelperCompat.OnAppsChangedCallbackCompat {

    @Override
    public void onPackageRemoved(String packageName) {
//        CleanAppService.startCleanAppService(context, packageName,CleanAppService.CLEAN_TYPE_INSTALL);
    }

    @Override
    public void onPackageAdded(String packageName) {

    }

    @Override
    public void onPackageChanged(String packageName) {

    }

    @Override
    public void onPackagesAvailable(String[] packageNames, boolean replacing) {

    }

    @Override
    public void onPackagesUnavailable(String[] packageNames, boolean replacing) {

    }
}
