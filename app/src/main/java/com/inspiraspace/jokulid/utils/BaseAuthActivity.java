package com.inspiraspace.jokulid.utils;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.inspiraspace.jokulid.JokulidApplication;
import com.inspiraspace.jokulid.preenter.LoginActivity;
import com.inspiraspace.jokulid.preenter.RegisterActivity;

/**
 * Created by mursitaffandi on 4/17/18.
 */

public class BaseAuthActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!JokulidApplication.getInstance().getStatusUserIsauthed()) {
            startActivity(new Intent(this, LoginActivity.class));
        }

    }


}
