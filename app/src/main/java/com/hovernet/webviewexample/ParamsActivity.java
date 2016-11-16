package com.hovernet.webviewexample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;

public class ParamsActivity extends AppCompatActivity {

    EditText primaryUrl ;
    EditText secondaryUrl ;
    Button savePrefsBtn;

    public static final String PREFS_NAME = "MyPrefsFile";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_params);

        primaryUrl = (EditText) findViewById(R.id.Ulr1EditText);
        primaryUrl.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (primaryUrl.getText().toString().length() < 1 ){
                    primaryUrl.setError(getString(R.string.editext_error_msg));
                }
            }
        });

        secondaryUrl = (EditText) findViewById(R.id.Url2EditText);
        secondaryUrl.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (secondaryUrl.getText().toString().length() < 1 ){
                    secondaryUrl.setError(getString(R.string.editext_error_msg));
                }
            }
        });

        savePrefsBtn = (Button) findViewById(R.id.savePrefsBtn);
        savePrefsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (primaryUrl.getText().length() < 1 && secondaryUrl.getText().length() < 1){
                   Toast.makeText(ParamsActivity.this, getString(R.string.editext_error_msg), Toast.LENGTH_SHORT).show();
               }else {
                   SharedPreferences pref = getApplicationContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                   SharedPreferences.Editor editor = pref.edit();
                   editor.putString("url1", primaryUrl.getText().toString());
                   editor.putString("url2", secondaryUrl.getText().toString());
                   editor.commit();

                   Toast.makeText(ParamsActivity.this, "Saved to Shared preferences", Toast.LENGTH_SHORT).show();

                   Thread thread = new Thread(){
                       @Override
                       public void run() {
                           String urlString = checkUrl(primaryUrl.getText().toString(), secondaryUrl.getText().toString());
                           // Start MainActivity
                           Intent i = new Intent(ParamsActivity.this, MainActivity.class);
                           i.putExtra("urlString", urlString);
                           startActivity(i);
                       }

                   };

                   thread.start();

               }
            }
        });

    }

    public String checkUrl(String primaryUrl, String secondaryUrl) {
        String urlString = primaryUrl; //"http://petrolmak.mine.nu:8080/apex/f?p=100";
        //tring urlString = "http://85.72.57.52:8080/apex/f?p=103:19";
        //check to see if url is reachable
        try {
            URL url = new URL(urlString);
            // set default cookie manager for handling the "too many redirects exception"
            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
            int code = connection.getResponseCode();

            if (code != 200) {

                urlString = secondaryUrl; //"http://192.168.1.111:8080/apex/f?p=103:19";
            }

        } catch (IOException e) {
            Log.d("IOException", e.getMessage());
        }
        return urlString;
    }


}
