package com.hovernet.webviewexample;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
               }
            }
        });

    }


}
