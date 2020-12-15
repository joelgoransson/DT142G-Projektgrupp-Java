package com.example.orderapp1_2;
import android.app.Application;
import android.content.Context;

//global context class :/ source: randomdude123 on stackoverflow
public class MyApplication extends Application {

    private static Application sApplication;

    public static Application getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
