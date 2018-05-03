package com.inspiraspace.jokulid.subactivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.adapter.AdapterTransactions;
import com.inspiraspace.jokulid.model.transactions.Response;
import com.inspiraspace.jokulid.network.main.PulseMainServer;
import com.inspiraspace.jokulid.presenter.GeneratorTransactions;
import com.inspiraspace.jokulid.utils.BaseAuthActivity;
import com.inspiraspace.jokulid.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseAuthActivity implements PulseMainServer {
    private AdapterTransactions adapter;
    List<Response> responses;
    @BindView(R.id.rv_transactions)
    RecyclerView rv_main;
    private GeneratorTransactions presenterTransactions;

    @Override
    public void onSuccessGetTransactions(List<Response> transaction) {
        responses= transaction;
        adapter.swipeLoadTransactions(responses);
    }

    @Override
    public void onFailOccureTransactions(String errmsg) {
        Toast.makeText(this, errmsg, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_transactions);

        ButterKnife.bind(this);
        adapter = new AdapterTransactions(this);
        rv_main.setLayoutManager(new LinearLayoutManager(this));
        rv_main.setAdapter(adapter);
        presenterTransactions = new GeneratorTransactions(this);
        presenterTransactions.getTransaction(getIntent().getStringExtra(Constant.KEY_SEARCH_TRANSACTION));
    }
}
