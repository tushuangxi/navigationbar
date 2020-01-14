package com.betterzhang.androidbase.app;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static App instance;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = this.getApplicationContext();
    }

    public static App getInstance() {
        return instance;
    }

    public static Context getContext() {
        return mContext;
    }
}
