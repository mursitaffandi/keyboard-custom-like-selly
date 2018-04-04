package com.inspiraspace.jokulid.fragment.transactions;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.inspiraspace.jokulid.subactivities.CreateTransactionActivity;
import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.adapter.AdapterTransactions;
import com.inspiraspace.jokulid.model.Response;
import com.inspiraspace.jokulid.network.PulseTransactions;
import com.inspiraspace.jokulid.presenter.GeneratorTransactions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PendingsFragment extends Fragment implements PulseTransactions {
    private static final String TRANSACTION_LIST_STATE = "1";
    private Context context;
    private Unbinder unbinder;
    private AdapterTransactions adapter;
    List<Response> responses;
    // Store instance variables
    public static final String ARG_PAGE = "ARG_PAGE";
    private int page;

    @BindView(R.id.rv_transactions)
    RecyclerView rv_main;

    @BindView(R.id.fab)
    FloatingActionButton fab_add_transactions;

    GeneratorTransactions presenterTransactions;

    // newInstance constructor for creating fragment with arguments
    public static PendingsFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PendingsFragment fragment = new PendingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public PendingsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt(ARG_PAGE);
        presenterTransactions = new GeneratorTransactions(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);
        unbinder = ButterKnife.bind(this,view);
        context = view.getContext();
        adapter = new AdapterTransactions(context);
        rv_main.setLayoutManager(new LinearLayoutManager(context));
        rv_main.setAdapter(adapter);
        if (savedInstanceState == null)
            presenterTransactions.getTransactios(page);
        else  {
            responses = savedInstanceState.getParcelableArrayList(TRANSACTION_LIST_STATE);
            adapter.swipeLoadTransactions(responses);
        }

        fab_add_transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, CreateTransactionActivity.class));
            }
        });
        return view;
    }

    @Override
    public void onSuccessGetTransactions(List<Response> transaction) {
        responses= transaction;
        adapter.swipeLoadTransactions(responses);
    }

    @Override
    public void onFailOccureTransactions(String errmsg) {
        Toast.makeText(this.getContext(), errmsg, Toast.LENGTH_SHORT).show();
        System.out.println(errmsg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (responses != null)
            outState.putParcelableArrayList(TRANSACTION_LIST_STATE, (ArrayList<? extends Parcelable>) responses);
    }
}
