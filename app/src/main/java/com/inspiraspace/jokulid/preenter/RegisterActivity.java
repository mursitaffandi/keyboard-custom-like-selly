package com.inspiraspace.jokulid.preenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.network.main.PulsePostUser;
import com.inspiraspace.jokulid.presenter.PostUser;
import com.inspiraspace.jokulid.utils.UtilValidation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements PulsePostUser {

    @BindView(R.id.etd_register_name)
    TextInputEditText etdRegisterName;
    @BindView(R.id.edt_register_email)
    TextInputEditText edtRegisterEmail;
    @BindView(R.id.edt_register_tokoname)
    TextInputEditText edtRegisterTokoname;
    @BindView(R.id.edt_register_tokonohp)
    TextInputEditText edtRegisterTokonohp;
    @BindView(R.id.edt_register_tokourl)
    TextInputEditText edtRegisterTokourl;
    @BindView(R.id.edt_register_password)
    TextInputEditText edtRegisterPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_register_submit)
    public void onViewClicked() {
        boolean isAllFieldValid = false;

        for (EditText edt : getRegisterEdittext())
            isAllFieldValid = UtilValidation.edittextValidation(edt);

        if (isAllFieldValid)
            new PostUser(this,
                    edtRegisterEmail.getText().toString(),
                    edtRegisterPassword.getText().toString(),
                    etdRegisterName.getText().toString(),
                    edtRegisterTokoname.getText().toString(),
                    edtRegisterTokonohp.getText().toString(),
                    edtRegisterTokourl.getText().toString()
                    );
    }

    public EditText[] getRegisterEdittext() {
        return new EditText[]{
                edtRegisterEmail,
                edtRegisterPassword,
                etdRegisterName,
                edtRegisterTokoname,
                edtRegisterTokonohp,
                edtRegisterTokourl
        };
    }

    @Override
    public void OnSuccessPostUser() {
        Toast.makeText(this, "New Account Created", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void OnErrorPostuser(String message) {

    }
}
