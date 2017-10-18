package com.softianstech.jopportal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Lenovo on 6/1/2017.
 */

public class Logo  extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    Context c;

    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
//               /

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
