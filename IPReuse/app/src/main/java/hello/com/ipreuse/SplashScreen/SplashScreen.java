package hello.com.ipreuse.SplashScreen;


import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.Toast;

import hello.com.ipreuse.MainActivity;
import hello.com.ipreuse.R;

/**
 * Created by Rupsi on 25-05-2017
 */

public class SplashScreen extends Activity {


    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(null);
        setContentView(R.layout.activity_splash);
        ProgressBar pg = (ProgressBar) findViewById(R.id.progressBar);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(pg, "progress", 0, 240);
        progressAnimator.setDuration(5000);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();
//
        //pg.getIndeterminateDrawable().setColorFilter(Color.MAGENTA, PorterDuff.Mode.MULTIPLY);
        //pg.setProgress(10);
        Thread background = new Thread() {
            @SuppressLint("SdCardPath")
            public void run() {

                try {
                    sleep(10 * 200);

                    if(checkConnectivity()==true) {
                        Toast.makeText(getApplicationContext(), "Internet connection is available!", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Internet connection is not available!", Toast.LENGTH_LONG).show();
                    }
                        Intent i = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    //}

                } catch (Exception e) {

                }
            }
        };

        background.start();

    }



    public boolean checkConnectivity() {
        ConnectivityManager connectivityManager;
        NetworkInfo wifiInfo, mobileInfo;
        boolean connectivity;

        try {
            connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (wifiInfo.isConnected() || mobileInfo.isConnected()) {
                connectivity = true;
            } else {
                connectivity = false;
            }
        } catch (Exception e) {
            connectivity = false;
        }

        return connectivity;
    }

}

