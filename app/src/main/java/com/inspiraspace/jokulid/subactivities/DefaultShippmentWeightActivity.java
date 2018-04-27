package com.inspiraspace.jokulid.subactivities;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;

import com.inspiraspace.jokulid.JokulidApplication;
import com.inspiraspace.jokulid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DefaultShippmentWeightActivity extends AppCompatActivity {

    @BindView(R.id.edt_defaultweight_weight)
    TextInputEditText edtDefaultweightWeight;
String settedDefaullWight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_shippment_weight);
        ButterKnife.bind(this);
        edtDefaultweightWeight.setText(JokulidApplication.getInstance().getShippmentWeight());
    }

    @OnClick(R.id.btn_defaultweight_save)
    public void onViewClicked() {
        settedDefaullWight= edtDefaultweightWeight.getText().toString();
        JokulidApplication.getInstance().setShippmentWeight(settedDefaullWight);
        finish();
    }
}
