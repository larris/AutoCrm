package com.hovernet.webviewexample;

import android.os.Bundle;

import org.apache.cordova.DroidGap;

public class MainActivity extends DroidGap {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.init();
        super.clearCache();

        String urlString = getIntent().getStringExtra("urlString");

        super.loadUrl(urlString);
    }

    public void onReceivedError(int errorCode, String description, String failingUrl) {
        super.loadUrl("file:///android_asset/www/noconnection.html");
        return;
    }

    @Override
    public void onBackPressed() {
        finish();
        return;

    }

}
//    OLD ACTIVITY
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        WebView myWebView = (WebView) findViewById(R.id.myWebView);
//
//
//        myWebView.setWebViewClient(new WebViewClient());
//
//        myWebView.setWebViewClient(new WebViewClient());
//        myWebView.getSettings().setJavaScriptEnabled(true);
//        myWebView.getSettings().setDomStorageEnabled(true);
//
//        String urlString = getIntent().getStringExtra("urlString");
//
//        myWebView.loadUrl(urlString);
//    }
