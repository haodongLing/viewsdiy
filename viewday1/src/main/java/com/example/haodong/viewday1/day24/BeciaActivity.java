package com.example.haodong.viewday1.day24;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.haodong.viewday1.R;
import com.example.haodong.viewday1.packagereceiver.PackageReceiverHelperCompat;

public class BeciaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_becia);
        PackageReceiverHelperCompat.getsInstance(BeciaActivity.this).
                addOnAppsChangedCallback(
                        new
                                PackageReceiverHelperCompat.OnAppsChangedCallbackCompat() {


                                    @Override
                                    public void onPackageRemoved(String packageName) {
                                        Log.e("lhl28", "onPackageRemoved: " );
                                    }

                                    @Override
                                    public void onPackageAdded(String packageName) {
                                        Log.e("lhl28", "onPackageAdded: " );
                                    }

                                    @Override
                                    public void onPackageChanged(String packageName) {
                                        Log.e("lhl28", "onPackageChanged: " );
                                    }

                                    @Override
                                    public void onPackagesAvailable(String[] packageNames, boolean replacing) {

                                    }

                                    @Override
                                    public void onPackagesUnavailable(String[] packageNames, boolean replacing) {

                                    }
                                });
    }

}
