package com.inspiraspace.jokulid.subactivities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.inspiraspace.jokulid.JokulidApplication;
import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.adapter.AdpLVSettingShippmentcompany;
import com.inspiraspace.jokulid.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShippmentCompanysericeActivity extends AppCompatActivity {
    @BindView(R.id.lv_setting_shippmentcompany)
    ListView lv_setting_shippmentcompany;
    AdpLVSettingShippmentcompany adpLVSettingShippmentcompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shippment_companyserice);
        ButterKnife.bind(this);
        adpLVSettingShippmentcompany = new AdpLVSettingShippmentcompany(Constant.LIST_SHIPPMENTCOMPANY, this);
        lv_setting_shippmentcompany.setAdapter(adpLVSettingShippmentcompany);
    }

    @OnClick(R.id.btn_setting_shippmentcompany_save)
    public void saveShippmentcompany() {
        if (adpLVSettingShippmentcompany.getSelectedCompany().size()<1)
            Toast.makeText(this, "At least select one company service", Toast.LENGTH_SHORT).show();
        else {
        JokulidApplication.getInstance().setShippmentCompany(adpLVSettingShippmentcompany.getSelectedCompany());
        finish();}
    }
}
