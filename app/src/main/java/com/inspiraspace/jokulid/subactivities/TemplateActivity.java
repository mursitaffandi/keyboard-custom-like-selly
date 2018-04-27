package com.inspiraspace.jokulid.subactivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.model.template.ResponseTemplate;
import com.inspiraspace.jokulid.network.main.PulseTemplate;
import com.inspiraspace.jokulid.presenter.GeneratorTemplate;
import com.inspiraspace.jokulid.utils.Constant;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TemplateActivity extends AppCompatActivity implements PulseTemplate, TextWatcher {
    @BindView(R.id.edt_template_content)
    MultiAutoCompleteTextView edt_template_content;
    private String template;

    @BindView(R.id.tv_template_preview)
    TextView tv_template_preview;

    @BindView(R.id.textView)
    TextView textView;

    @BindView(R.id.tv_template_availabletag)
    TextView tv_template_availabletag;

    GeneratorTemplate generatorTemplate;

    String editTemplate = "";
    HashMap<String, String> mapKeyTemplate = new HashMap<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        ButterKnife.bind(this);
        template = getIntent().getStringExtra(Constant.KEY_INTENT_TEMPALTE);
        textView.setText(template);
        String tag_available = getResources().getString(R.string.tag_available_template);
        for (String s : Constant.LIST_TAG_KEY_TEMPLATE)
            tag_available = tag_available + s + " ";

        tv_template_availabletag.setText(tag_available);
        generatorTemplate = new GeneratorTemplate(this);

        generatorTemplate.getTemplate(template);
        setMapTemplate();
        edt_template_content.addTextChangedListener(this);

        adapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, Constant.LIST_TAG_KEY_TEMPLATE);
        edt_template_content.setAdapter(adapter);
        edt_template_content.setTokenizer(new SpaceTokenizer());


    }

    @OnClick(R.id.btn_template_refresh_preview)
    public void saveTemplate() {
        generatorTemplate.updateTemplate(template, editTemplate);
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
    public void OnSuccessUpdateTemplate() {
        Toast.makeText(this, "Template Updated", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void OnErrorUpdateTemplate(String errmsg) {
        Toast.makeText(this, "Fail update template " + errmsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable edt) {
        if (edt_template_content.isFocused()) {
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

    public class SpaceTokenizer implements MultiAutoCompleteTextView.Tokenizer {

        public int findTokenStart(CharSequence text, int cursor) {
            int i = cursor;

            while (i > 0 && text.charAt(i - 1) != ' ') {
                i--;
            }
            while (i < cursor && text.charAt(i) == ' ') {
                i++;
            }

            return i;
        }

        public int findTokenEnd(CharSequence text, int cursor) {
            int i = cursor;
            int len = text.length();

            while (i < len) {
                if (text.charAt(i) == ' ') {
                    return i;
                } else {
                    i++;
                }
            }

            return len;
        }

        public CharSequence terminateToken(CharSequence text) {
            int i = text.length();

            while (i > 0 && text.charAt(i - 1) == ' ') {
                i--;
            }

            if (i > 0 && text.charAt(i - 1) == ' ') {
                return text;
            } else {
                if (text instanceof Spanned) {
                    SpannableString sp = new SpannableString(text + " ");
                    TextUtils.copySpansFrom((Spanned) text, 0, text.length(),
                            Object.class, sp, 0);
                    return sp;
                } else {
                    return text + " ";
                }
            }
        }
    }
}
