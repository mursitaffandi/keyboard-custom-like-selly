package com.inspiraspace.jokulid.utils;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.inspiraspace.jokulid.event.AuthListener;

/**
 * Created by mursitaffandi on 4/17/18.
 */

public class BaseAuthActivity extends AppCompatActivity implements AuthListener {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void OnSuccesLogin() {

    }

    @Override
    public void OnFailLogin() {

    }

    @Override
    public void OnUnauthedSession() {

    }

    @Override
    public void OnLogout() {

    }
}
