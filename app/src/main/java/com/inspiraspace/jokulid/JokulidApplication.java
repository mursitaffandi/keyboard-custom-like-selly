package com.inspiraspace.jokulid;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by mursitaffandi on 4/5/18.
 */

public class JokulidApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
