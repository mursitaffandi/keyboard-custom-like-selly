package com.inspiraspace.jokulid.subactivities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import com.inspiraspace.jokulid.fragment.SettingsDailyReminderFragment;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;

public class ScheduleDailyreminderActivity extends AppCompatActivity {
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsDailyReminderFragment()).commit();
        setupActionBar();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
