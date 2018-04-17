package com.inspiraspace.jokulid;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.inspiraspace.jokulid.fragment.CustomersFragment;
import com.inspiraspace.jokulid.fragment.GratisFragment;
import com.inspiraspace.jokulid.fragment.HelpFragment;
import com.inspiraspace.jokulid.fragment.MainFragment;
import com.inspiraspace.jokulid.fragment.ReportFragment;
import com.inspiraspace.jokulid.fragment.SettingsFragment;
import com.inspiraspace.jokulid.fragment.ShippmentFeeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Fragment fragment;
    private FragmentManager fragmentManager;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        fragment = new MainFragment().newInstance(0);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.frame_container, fragment).commit();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_transactions) {
            fragment = new MainFragment().newInstance(0);
        } else if (id == R.id.nav_shippment) {
            fragment = new ShippmentFeeFragment();
        } else if (id == R.id.nav_report) {
            fragment = new ReportFragment();
        } else if (id == R.id.nav_customers) {
            fragment = new CustomersFragment();
        } else if (id == R.id.nav_transactionachieve) {
            fragment = new MainFragment().newInstance(1);
        } else if (id == R.id.nav_gratis) {
            fragment = new GratisFragment();
        } else if (id == R.id.nav_settings) {
            fragment = new SettingsFragment();
        } else if (id == R.id.nav_help) {
            fragment = new HelpFragment();
        }

        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment).commit();

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        String id = Settings.Secure.getString(
                getContentResolver(),
                Settings.Secure.DEFAULT_INPUT_METHOD);

        if (!id.equals("com.inspiraspace.jokulid/.keylogger.softkeyboard.SoftKeyboard")) {
            showChangeKeyboardDialog();
        }
    }

    public void showChangeKeyboardDialog() {
        AlertDialog.Builder dialogChangeKeyboard = new AlertDialog.Builder(this);
        dialogChangeKeyboard.setTitle("Jokul.id Keyboard");
        dialogChangeKeyboard.setMessage("Ganti keyboard dengan Jokul.id sekarang?");
        dialogChangeKeyboard.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeKeyboardSetting();
            }
        });
        dialogChangeKeyboard.setNegativeButton("Nanti", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = dialogChangeKeyboard.create();
        alert.show();
    }

    public void changeKeyboardSetting() {
        Intent in = new Intent();
        in.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$InputMethodAndLanguageSettingsActivity"));
        startActivity(in);
    }
}
