package com.inspiraspace.jokulid.preenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.utils.BaseAuthedActivity;

public class PreloadActivity extends BaseAuthedActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload);
    }
}
