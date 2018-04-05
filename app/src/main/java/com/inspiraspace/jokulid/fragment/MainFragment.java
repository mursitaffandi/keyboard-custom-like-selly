package com.inspiraspace.jokulid.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.adapter.AdapterFragmentPager;
import com.inspiraspace.jokulid.fragment.transactions.ListTransactionFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    private static final String ARG_PAGE_MAIN = "main";
    @BindView(R.id.viewpager)
    ViewPager vpMain;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    AdapterFragmentPager adapterViewPager;
    private int mainpage;

    public static MainFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_MAIN, page);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainpage = getArguments().getInt(ARG_PAGE_MAIN);
    }
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,view);
        adapterViewPager = new AdapterFragmentPager(getChildFragmentManager());

        if (mainpage == 0){
            adapterViewPager.addFragment(new ListTransactionFragment().newInstance(0), getString(R.string.label_tab_pending));
            adapterViewPager.addFragment(new ListTransactionFragment().newInstance(1), getString(R.string.label_tab_paid));
            adapterViewPager.addFragment(new ListTransactionFragment().newInstance(2), getString(R.string.label_tab_shipped));
        } else {
            adapterViewPager.addFragment(new ListTransactionFragment().newInstance(3), getString(R.string.label_tab_done));
            adapterViewPager.addFragment(new ListTransactionFragment().newInstance(4), getString(R.string.label_tab_cancel));
        }


        vpMain.setAdapter(adapterViewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(vpMain);

        tabLayout.setOnTabSelectedListener(this);

        selectActionTab(0);

        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        selectActionTab(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    private void selectActionTab(int position) {
        tabLayout.getTabAt(position).select();
    }
}
