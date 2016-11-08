package com.hovernet.webviewexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;


import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    // Set Duration of the Splash Screen
    long Delay = 8000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove Title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);


        Timer runSplash = new Timer();
        TimerTask showSplash = new TimerTask() {
            @Override
            public void run() {
                // Close SplashActivity
                finish();
                String urlString = "http://85.72.57.52:8080/apex/f?p=103:19";
                //check to see if url is reachable
                try {
                    URL url = new URL(urlString);
                    // set default cookie manager for handling the "too many redirects exception"
                    CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
                    int code = connection.getResponseCode();

                    if (code != 200) {

                        urlString = "http://192.168.1.111:8080/apex/f?p=103:19";
                    }

                } catch (IOException e) {
                    Log.d("IOException",e.getMessage());
                }



                // Start MainActivity
                Intent i = new Intent(SplashActivity.this,MainActivity.class);
                i.putExtra("urlString",urlString);
                startActivity(i);

            }
        };

        // Start the timer
        runSplash.schedule(showSplash, Delay);
    }
}
