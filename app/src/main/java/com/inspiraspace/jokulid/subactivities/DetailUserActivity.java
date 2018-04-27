package com.inspiraspace.jokulid.subactivities;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.model.user.Response;
import com.inspiraspace.jokulid.network.main.PulseUser;
import com.inspiraspace.jokulid.presenter.GeneratorUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailUserActivity extends AppCompatActivity implements PulseUser {

    @BindView(R.id.edt_user_name)
    TextInputEditText edtUserName;
    @BindView(R.id.edt_user_email)
    TextInputEditText edtUserEmail;
    @BindView(R.id.edt_user_password)
    TextInputEditText edtUserPassword;

    GeneratorUser generatorUser;
    String name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        ButterKnife.bind(this);
        generatorUser = new GeneratorUser(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        generatorUser.getUser();

    }

    @OnClick(R.id.btn_user_save)
    public void onViewClicked() {
        name = edtUserName.getText().toString();
        email = edtUserEmail.getText().toString();
        password = edtUserPassword.getText().toString();
        generatorUser.updateUser(name, email, password);
    }

    @Override
    public void OnSuccessUser(Response responseUsers) {
        edtUserName.setText(responseUsers.getUserName());
        edtUserEmail.setText(responseUsers.getUserEmail());
        edtUserPassword.setText(responseUsers.getUserPassword());
    }

    @Override
    public void OnErrorUser(String errmsg) {

    }

    @Override
    public void OnSuccessUpdateUser() {
        Toast.makeText(this, "User Info Updated", Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void OnErrorUpdateUser(String errmsg) {
        Toast.makeText(this, "Fail Update User Info", Toast.LENGTH_SHORT).show();
    }
}
