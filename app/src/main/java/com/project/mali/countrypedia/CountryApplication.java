package com.project.mali.countrypedia;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

public class CountryApplication extends Application {
    private static Application mInstance;

    public static synchronized Context getApplication() {
        return mInstance;
    }

    public static CountryApplication getInstance() {
        return (CountryApplication) CountryApplication.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
