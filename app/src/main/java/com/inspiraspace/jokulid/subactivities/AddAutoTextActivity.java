package com.inspiraspace.jokulid.subactivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.network.main.PulsePostAutotext;
import com.inspiraspace.jokulid.presenter.PostAutotext;
import com.inspiraspace.jokulid.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAutoTextActivity extends AppCompatActivity implements PulsePostAutotext {


    @BindView(R.id.edt_addautotext_title)
    EditText edtAddautotextTitle;

    @BindView(R.id.edt_addautotext_content)
    EditText edtAddautotextContent;
String shortcut, content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_auto_text);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_addautotext_add)
    public void onViewClicked() {
        shortcut = edtAddautotextTitle.getText().toString();
        content = edtAddautotextContent.getText().toString();
        new PostAutotext(this, Constant.SESSION_USER_ID,shortcut,content);
    }

    @Override
    public void OnSuccessPostBankAccount() {
        Toast.makeText(this, "New Autotext Created", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void OnErrorPostBankAccount() {
        Toast.makeText(this, "Fail Create new Autotext", Toast.LENGTH_SHORT).show();
    }
}
