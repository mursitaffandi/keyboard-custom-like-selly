package com.inspiraspace.jokulid.subactivities;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;
import android.widget.Toast;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.adapter.AdpSpinnerBank;
import com.inspiraspace.jokulid.model.bank.ResponseItem;
import com.inspiraspace.jokulid.network.main.PulseBank;
import com.inspiraspace.jokulid.network.main.PulsePostBankAccount;
import com.inspiraspace.jokulid.presenter.GeneratorBank;
import com.inspiraspace.jokulid.presenter.PostBankAccount;
import com.inspiraspace.jokulid.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddPaymentActivity extends AppCompatActivity implements PulseBank, PulsePostBankAccount {
    @BindView(R.id.sp_addpayment)
    Spinner sp_addpayment;

    @BindView(R.id.edt_addpayment_account_number)
    TextInputEditText edt_addpayment_account_number;

    @BindView(R.id.edt_addpayment_account_name)
    TextInputEditText edt_addpayment_account_name;

    AdpSpinnerBank adpSpinnerBank;
    List<ResponseItem> banklist;
    String selectedBankId, settedAccountName, settedAccountNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);
        ButterKnife.bind(this);
        new GeneratorBank(this);
    }

    @Override
    public void OnSuccesGetBanks(List<ResponseItem> banklist) {
        this.banklist = banklist;
        adpSpinnerBank = new AdpSpinnerBank(this, banklist);
        sp_addpayment.setAdapter(adpSpinnerBank);
    }

    @Override
    public void OnErrorGetBank(String errmsg) {

    }

    @OnClick(R.id.btn_addpayment_add)
    public void saveNewBankAccount() {
        ResponseItem responseItem = (ResponseItem) sp_addpayment.getSelectedItem();
        selectedBankId = responseItem.getBankId();
        settedAccountName = edt_addpayment_account_name.getText().toString();
        settedAccountNumber = edt_addpayment_account_number.getText().toString();
        new PostBankAccount(this, Constant.SESSION_USER_ID,selectedBankId,settedAccountNumber,settedAccountName);
    }

    @Override
    public void OnSuccessPostBankAccount() {
        Toast.makeText(this, "New Bank Account Created", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void OnErrorPostBankAccount() {
        Toast.makeText(this, "Fail Create new Bank Account", Toast.LENGTH_SHORT).show();
    }
}
