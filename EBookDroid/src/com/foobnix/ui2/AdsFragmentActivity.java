package com.foobnix.ui2;

import com.foobnix.pdf.info.MyADSProvider;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public abstract class AdsFragmentActivity extends FragmentActivity {

    private final MyADSProvider myAds = new MyADSProvider();

    public abstract void onFinishActivity();

    Runnable onFinish = new Runnable() {

        @Override
        public void run() {
            onFinishActivity();
        }
    };
        

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        myAds.activate(this, onFinish);
    }

    public void activateAds() {
        myAds.activate(this, onFinish);
    }

    @Override
    protected void onResume() {
        super.onResume();
        myAds.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        myAds.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myAds.destroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        myAds.activate(this, onFinish);
    }

    public void showInterstial() {
        if (myAds.showInterstial()) {
            // ok
        } else {
            onFinish.run();
        }

    }

    public boolean isInterstialShown() {
        return myAds.isInterstialShown();
    }

}
