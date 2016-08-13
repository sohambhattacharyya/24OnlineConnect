package com.daceapps.netconnect;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);

        setContentView(R.layout.activity_main);
        WebView webView = (WebView) findViewById(R.id.webview);

        webView.reload();

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.requestFocus(View.FOCUS_DOWN);


        String url = "http://10.10.0.1/24online/webpages/client.jsp?fromlogout=true";
        String postData = "username=user&password=12345";
    //    String user = "username=sukanta_rsv";
    //    String pass = "password=ZONDER";


        //      webView.postUrl(url, postData.getBytes());


  /*      webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_UP:
                        v.requestFocusFromTouch();
                        break;
                }
                return false;
            }

        });
*/
        webView.postUrl(url, postData.getBytes());

     /*   webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_UP:
                        if (!v.hasFocus()) {
                            v.requestFocus();
                        }
                        break;
                }
                return false;
            }
        });*/

        //     webView.dispatchKeyEvent(new KeyEvent(,KeyEvent.KEYCODE_ENTER));

        final Instrumentation instrumentation = new Instrumentation();

        new Thread(new Runnable() {
            @Override
            public void run() {

            //    instrumentation.sendCharacterSync(KeyEvent.KEYCODE_TAB);

                try {
                    Thread.sleep(2000);
                } catch(InterruptedException ei) {
                    Thread.currentThread().interrupt();
                }

            //    instrumentation.sendCharacterSync(KeyEvent.KEYCODE_ENTER);
            //    instrumentation.sendCharacterSync(KeyEvent.KEYCODE_ENTER);

                instrumentation.sendStringSync("sukanta_rsv");

                instrumentation.sendCharacterSync(KeyEvent.KEYCODE_ENTER);

                instrumentation.sendStringSync("ZONDER");

                instrumentation.sendCharacterSync(KeyEvent.KEYCODE_ENTER);
            //    instrumentation.sendCharacterSync(KeyEvent.KEYCODE_ENTER);
                try {
                    Thread.sleep(500);
                } catch(InterruptedException ei) {
                    Thread.currentThread().interrupt();
                }
                instrumentation.sendCharacterSync(KeyEvent.KEYCODE_ENTER);            }
        }).start();


        Intent notifyIntent = new Intent(this, NotificationService.class);
        startService(notifyIntent);

        new ReloadWebView(this, 30, webView);

        Log.v("Example", "onCreate");
        getIntent().setAction("Already created");


    }

    @Override
    protected void onResume() {
        Log.v("Example", "onResume");

        String action = getIntent().getAction();
        // Prevent endless loop by adding a unique action, don't restart if action is present
        if(action == null || !action.equals("Already created")) {
            Log.v("MainActivity", "Force restart");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        // Remove the unique action so the next time onResume is called it will restart
        else
            getIntent().setAction(null);

        super.onResume();
    }

}

