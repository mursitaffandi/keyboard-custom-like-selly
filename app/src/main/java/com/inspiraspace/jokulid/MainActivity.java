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
import android.support.v7.widget.SearchView;
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
import com.inspiraspace.jokulid.subactivities.SearchActivity;
import com.inspiraspace.jokulid.utils.BaseAuthActivity;
import com.inspiraspace.jokulid.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseAuthActivity
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
    protected void onStop() {
        this.fragment = fragment;
        super.onStop();
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
        getMenuInflater().inflate(R.menu.main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint(getResources().getString(R.string.label_menu_search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                intent.putExtra(Constant.KEY_SEARCH_TRANSACTION, query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
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
        switch (item.getItemId()) {
            case R.id.nav_transactions:
                fragment = new MainFragment().newInstance(0);
                break;
            case R.id.nav_shippment:
                fragment = new ShippmentFeeFragment();
                break;
            case R.id.nav_report:
                fragment = new ReportFragment();
                break;
            case R.id.nav_customers:
                fragment = new CustomersFragment();
                break;
            case R.id.nav_transactionachieve:
                fragment = new MainFragment().newInstance(1);
                break;
            case R.id.nav_gratis:
                fragment = new GratisFragment();
                break;
            case R.id.nav_settings:
                fragment = new SettingsFragment();
                break;
            case R.id.nav_help:
                fragment = new HelpFragment();
                break;
            default:
                break;
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

        if (!id.equals(Constant.URI_KEYBOARD_JOKULID)) {
            showChangeKeyboardDialog();
        }
    }

    public void showChangeKeyboardDialog() {
        AlertDialog.Builder dialogChangeKeyboard = new AlertDialog.Builder(this);
        dialogChangeKeyboard.setTitle(R.string.dialog_title_chagekeyboard);
        dialogChangeKeyboard.setMessage(R.string.dialog_question_chagekeyboard);
        dialogChangeKeyboard.setPositiveButton(R.string.dialog_positive_chagekeyboard, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeKeyboardSetting();
            }
        });
        dialogChangeKeyboard.setNegativeButton(R.string.dialog_negative_chagekeyboard, new DialogInterface.OnClickListener() {
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
