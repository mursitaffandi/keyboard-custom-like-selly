package com.inspiraspace.jokulid.subactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.adapter.AdpAutoTexts;
import com.inspiraspace.jokulid.model.autotext.Response;
import com.inspiraspace.jokulid.network.main.PulseAutoText;
import com.inspiraspace.jokulid.presenter.GeneratorAutoTexts;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AutoTextActivity extends AppCompatActivity implements PulseAutoText {

    @BindView(R.id.edt_subcdt_autotext_search)
    EditText
    edt_subcdt_autotext_search;

    @OnClick(R.id.btn_subcdt_autotext_search)
    public void searcAutotext(){
        generatorAutoTexts.getAutoText(edt_subcdt_autotext_search.getText().toString());
    }

    @OnClick(R.id.fab_autotext)
    public void toNewAutotext(){
        Intent i = new Intent(this, AddAutoTextActivity.class);
        startActivity(i);
    }


    @BindView(R.id.lv_subcdt_autotext_search)
    ListView
    lv_subcdt_autotext_search;

    AdpAutoTexts adpAutoTexts;
    GeneratorAutoTexts generatorAutoTexts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subcdt_autotext);
        ButterKnife.bind(this);
        generatorAutoTexts = new GeneratorAutoTexts(this);

        adpAutoTexts = new AdpAutoTexts( this);
        lv_subcdt_autotext_search.setAdapter(adpAutoTexts);

        lv_subcdt_autotext_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        generatorAutoTexts.getAutoText("");
    }

    @Override
    public void onSuccess(List<Response> response) {
        adpAutoTexts.swapLogs(response);
    }

    @Override
    public void onError(String msgerror) {

    }
}
