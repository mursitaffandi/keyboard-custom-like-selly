package com.inspiraspace.jokulid.subactivities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.model.template.ResponseTemplate;
import com.inspiraspace.jokulid.network.main.PulseTemplate;
import com.inspiraspace.jokulid.presenter.GenerateTemplate;
import com.inspiraspace.jokulid.utils.Constant;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TemplateActivity extends AppCompatActivity implements PulseTemplate , TextWatcher{
@BindView(R.id.edt_template_content)
TextInputEditText edt_template_content;

@BindView(R.id.btn_template_refresh_preview)
    Button btn_template_save;

@BindView(R.id.tv_template_preview)
    TextView tv_template_preview;

GenerateTemplate generateTemplate;
String editTemplate = "";
HashMap<String, String> mapKeyTemplate = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        ButterKnife.bind(this);
        generateTemplate = new GenerateTemplate(this);
        generateTemplate.getTemplate(getIntent().getStringExtra(Constant.KEY_INTENT_TEMPALTE));
        setMapTemplate();
        edt_template_content.addTextChangedListener(this);
    }

    private void setMapTemplate() {
        mapKeyTemplate.put("#account-name", "John");
        mapKeyTemplate.put("#account-number", "0821911");
        mapKeyTemplate.put("#amount", "20000");
        mapKeyTemplate.put("#bank-name", "BRI");
        mapKeyTemplate.put("#bank-address", "90128749202");
        mapKeyTemplate.put("#customer-address", "Gamping, Slemana, DIY");
        mapKeyTemplate.put("#customer-contact", "08121341");
        mapKeyTemplate.put("#customer-name", "Beni");
        mapKeyTemplate.put("#invoice-id", "1");
        mapKeyTemplate.put("#notes", "tolong bungkus yg rapi");
        mapKeyTemplate.put("#product-list", "1 X buku");
        mapKeyTemplate.put("#shipping-cost", "10000");
        mapKeyTemplate.put("#store-name", "OL Shop");
        mapKeyTemplate.put("#total-amount", "30000");
        mapKeyTemplate.put("#total-amount", "30000");
        mapKeyTemplate.put("#shipping-provider", "JNE");
        mapKeyTemplate.put("#resi-number", "080840202048");
    }

    @Override
    public void OnSuccessTemplate(ResponseTemplate responseTemplates) {
        editTemplate = responseTemplates.getTemplate();
        edt_template_content.setText(editTemplate);
    }

    @Override
    public void OnErrorTemplate(String errmsg) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable edt) {
        if (edt_template_content.isFocused()){
            editTemplate = edt_template_content.getText().toString();
            String replacement = editTemplate.replace("\n", "\n ");
            String[] strSplited = replacement.split(" ");
            String strResult;
            StringBuilder builder = new StringBuilder();
            for (String s : strSplited) {
                String value = mapKeyTemplate.get(s);
                if (value != null) s = value;

                builder.append(s + " ");
            }
            strResult = builder.toString();
            tv_template_preview.setText(strResult);
        }
    }
}
