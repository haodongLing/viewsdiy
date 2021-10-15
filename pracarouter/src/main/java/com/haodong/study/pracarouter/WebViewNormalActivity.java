/**
 * Copyright (c) 2015-present, MaxLeapMobile.
 * All rights reserved.
 * ----
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.haodong.study.pracarouter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;



import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.UUID;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import io.reactivex.functions.Consumer;

public class WebViewNormalActivity extends AppCompatActivity {

    public final static String INTENT_KEY_CONTENT = "intent_key_content";
    public final static String INTENT_KEY_URL = "intent_key_url";
    public final static String INTENT_KEY_TITLE = "intent_key_title";
    public final static String INTENT_KEY_HIDE_TITLE = "intent_key_hide_title";

    private Toolbar mToolbar;
    private ProgressBar mProgressBar;
    private WebView mWebView;
//    private AMWebChromeClient amWebChromeClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mcommon_activity_normal_webview);
        initViews();
    }

    private void initViews() {
//        if (getIntent().getBooleanExtra(INTENT_KEY_HIDE_TITLE, false)) {
//            mToolbar.setVisibility(View.GONE);
//        } else {
//            mToolbar.setTitle(getIntent().getStringExtra(INTENT_KEY_TITLE));
//            setSupportActionBar(mToolbar);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mWebView.canGoBack()) {
//                        mWebView.goBack();
//                    } else {
//                        onNavigationClick();
//                    }
//                }
//            });
//        }
        mWebView = (WebView) findViewById(R.id.common_webview);
        mProgressBar = (ProgressBar) findViewById(R.id.common_webview_progress);

        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setTextZoom(100);
        mWebView.setVerticalScrollBarEnabled(false);
        if (Build.VERSION.SDK_INT >= 21) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressBar.setVisibility(View.VISIBLE);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });



        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.loadUrl("https://m.caibeike.com/ms/user/agreement/index");


    }



    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();

        } else {
            super.onBackPressed();
        }
    }

    protected void onNavigationClick() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
    }

    @Override
    protected void onPause() {
        mWebView.onPause();
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onDestroy() {
        mWebView.destroy();
        mWebView = null;
        super.onDestroy();
    }
}
