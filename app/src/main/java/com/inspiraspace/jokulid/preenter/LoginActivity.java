package com.inspiraspace.jokulid.preenter;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.inspiraspace.jokulid.JokulidApplication;
import com.inspiraspace.jokulid.MainActivity;
import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.model.login.Response;
import com.inspiraspace.jokulid.network.main.PulseLogin;
import com.inspiraspace.jokulid.presenter.PostLogin;
import com.inspiraspace.jokulid.utils.UtilValidation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements PulseLogin {

    @BindView(R.id.edt_login_email)
    TextInputEditText edtLoginEmail;
    @BindView(R.id.edt_login_password)
    TextInputEditText edtLoginPassword;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login_submit)
    public void onViewClicked() {
        boolean isAllFieldValid = false;
        for (EditText edt : getLoginEdittext())
            isAllFieldValid = UtilValidation.edittextValidation(edt);
        if (isAllFieldValid)
            new PostLogin(this, edtLoginEmail.getText().toString(), edtLoginPassword.getText().toString());
    }

    @Override
    public void OnSuccessLogin(Response responseLogin) {
        JokulidApplication.getInstance().setLoginInfo(
                responseLogin.getUserName(),
                responseLogin.getUserId(),
                responseLogin.getUserEmail(),
                "",
                "",
                responseLogin.getTokoId(),
                responseLogin.getTokoName(),
                responseLogin.getTokoNoHp(),
                responseLogin.getTokoUrl());
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void OnErrorLogin(String errmsg) {
        Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
    }

    public EditText[] getLoginEdittext() {
        return new EditText[]{
                edtLoginEmail, edtLoginPassword
        };
    }

    @OnClick(R.id.tv_login_btn_register)
    public void onTextViewClicked() {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}

