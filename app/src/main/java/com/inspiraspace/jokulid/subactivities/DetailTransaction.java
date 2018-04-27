package com.inspiraspace.jokulid.subactivities;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.inspiraspace.jokulid.BuildConfig;
import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.adapter.AdpLVItemTransaction;
import com.inspiraspace.jokulid.adapter.AdpLVLogTransaction;
import com.inspiraspace.jokulid.adapter.AdpSpinnerChatapp;
import com.inspiraspace.jokulid.adapter.AdpSpinnerPayment;
import com.inspiraspace.jokulid.model.preaddtransaction.Chatapp;
import com.inspiraspace.jokulid.model.preaddtransaction.Payment;
import com.inspiraspace.jokulid.model.transactions.Item;
import com.inspiraspace.jokulid.model.transactions.Log;
import com.inspiraspace.jokulid.model.transactions.Response;
import com.inspiraspace.jokulid.network.main.PulseTransactionStatus;
import com.inspiraspace.jokulid.presenter.GeneratorChatappPayment;
import com.inspiraspace.jokulid.presenter.PostTransactionStatus;
import com.inspiraspace.jokulid.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailTransaction extends AppCompatActivity implements GeneratorChatappPayment.PulsePreDetailTransaction, PulseTransactionStatus {


    @BindView(R.id.edt_detailtr_ety_customername)
    EditText edt_detailtr_ety_customername;


    @BindView(R.id.sp_detailtr_ety_chatapp)
    Spinner sp_detailtr_ety_chatapp;

    @BindView(R.id.edt_detailtr_ety_customernumber)
    EditText edt_detailtr_ety_customernumber;


    @BindView(R.id.edt_detailtr_ety_customeraddr)
    EditText edt_detailtr_ety_customeraddr;

    @BindView(R.id.edt_detailtr_ety_customeritemtotalprice)
    EditText edt_detailtr_ety_customeritemtotalprice;

    @BindView(R.id.sp_detailtr_ety_paymentmethod)
    Spinner sp_detailtr_ety_paymentmethod;

    @BindView(R.id.edt_detailtr_ety_customershippmentfee)
    EditText edt_detailtr_ety_customershippmentfee;

    @BindView(R.id.edt_detailtr_ety_customernote)
    EditText edt_detailtr_ety_customernote;

    @BindView(R.id.btn_detailtr_add_customeritem)
    Button btn_detailtr_add_customeritem;

    @BindView(R.id.lv_detailtr_ety_customeritem)
    ListView lv_detailtr_ety_customeritem;

    @BindView(R.id.btn_detailtr_add_customerlog)
    Button btn_detailtr_add_customerlog;

    @BindView(R.id.lv_detailtr_ety_customerlog)
    ListView lv_detailtr_ety_customerlog;


    Intent rc_transaction;
    String customer_name,
            customer_chatapp_id, customer_number,
            customer_addr,
            customer_totalitemplprice,
            customer_paymentmethod_name,
            customer_paymentmethod_number,
            customer_shippmentfee,
            customer_note;

    List<Item> customer_items;
    List<Log> customer_logs;

    @BindView(R.id.iv_detail_chatapp)
    ImageView ivDetailChatapp;
    @BindView(R.id.tv_detail_chatapp)
    TextView tvDetailChatapp;
    @BindView(R.id.iv_detail_payment)
    ImageView ivDetailPayment;
    @BindView(R.id.tv_detail_payment)
    TextView tvDetailPayment;
    @BindView(R.id.tv_detail_payment_name)
    TextView tvDetailPaymentName;
    @BindView(R.id.tv_detail_payment_number)
    TextView tvDetailPaymentNumber;
    @BindView(R.id.btn_detailtr_sendresi)
    Button btnDetailtrSendresi;
    @BindView(R.id.btn_detailtr_remaindtopay)
    Button btnDetailtrRemaindtopay;

    private ArrayList<Chatapp> dataChatapps;
    private ArrayList<Payment> dataPayments;

    AdpSpinnerChatapp adpSpinnerChatapp;
    AdpSpinnerPayment adpSpinnerPayment;

    AdpLVLogTransaction adpLVLogTransaction;
    AdpLVItemTransaction adpLVItemTransaction;

    PostTransactionStatus postTransactionStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaction);

        ButterKnife.bind(this);
        postTransactionStatus = new PostTransactionStatus(this);
        new GeneratorChatappPayment(this);
        rc_transaction = getIntent();
        setupViewFromIntent();

        disableEdittext(edt_detailtr_ety_customername);
        disableEdittext(edt_detailtr_ety_customernumber);
        disableEdittext(edt_detailtr_ety_customeraddr);
        disableEdittext(edt_detailtr_ety_customeritemtotalprice);
        disableEdittext(edt_detailtr_ety_customershippmentfee);
        disableEdittext(edt_detailtr_ety_customernote);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detailtransaction, menu);
        return true;
    }

    String idTransaction, transactionStatus, newtransactionStatus = "", appPackage, appName;
    Response item_transaction;

    private void setupViewFromIntent() {
        item_transaction = rc_transaction.getParcelableExtra(Constant.TAG_TRANSACTION);
        idTransaction = item_transaction.getTransactionId();
        transactionStatus = item_transaction.getTransactionStatus();
        appPackage = item_transaction.getChatappPackage();
        appName = item_transaction.getChatappName();

        customer_name = item_transaction.getCustomerCustomerName();
        customer_addr = item_transaction.getShipmentAddrDestination();
        customer_totalitemplprice = item_transaction.getTotal();
        customer_paymentmethod_name = item_transaction.getBankAccountBankAccountName();
        customer_paymentmethod_number = item_transaction.getBankAccountBankAccountNumber();
        customer_shippmentfee = item_transaction.getShipmentOngkir();
        customer_note = item_transaction.getTransactionNote();

        customer_number = item_transaction.getCustomerCustomerNohp();
        customer_chatapp_id = item_transaction.getCustomerCustomerChatappId();
        customer_items = item_transaction.getItem();
        customer_logs = item_transaction.getLog();

        Glide.with(this).load(BuildConfig.BASE_URL_MAIN_IMAGE_PAYMENT + item_transaction.getChatappImage()).into(ivDetailChatapp);
        tvDetailChatapp.setText(item_transaction.getChatappName());

        Glide.with(this).load(BuildConfig.BASE_URL_MAIN_IMAGE_PAYMENT + item_transaction.getBankBankImage()).into(ivDetailPayment);
        tvDetailPayment.setText(item_transaction.getBankBankName());
        tvDetailPaymentName.setText(item_transaction.getBankAccountBankAccountName());
        tvDetailPaymentNumber.setText(item_transaction.getBankAccountBankAccountNumber());

        edt_detailtr_ety_customername.setText(customer_name);
        edt_detailtr_ety_customernumber.setText(customer_number);
        edt_detailtr_ety_customeraddr.setText(customer_addr);
        edt_detailtr_ety_customeritemtotalprice.setText(customer_totalitemplprice);
        edt_detailtr_ety_customershippmentfee.setText(customer_shippmentfee);
        edt_detailtr_ety_customernote.setText(customer_note);

        adpLVLogTransaction = new AdpLVLogTransaction(item_transaction.getLog(), this);
        lv_detailtr_ety_customerlog.setAdapter(adpLVLogTransaction);

        adpLVItemTransaction = new AdpLVItemTransaction(item_transaction.getItem(), this);
        lv_detailtr_ety_customeritem.setAdapter(adpLVItemTransaction);

        if (transactionStatus.equals("2"))
            btnDetailtrSendresi.setVisibility(View.VISIBLE);
        if (transactionStatus.equals("0"))
            btnDetailtrRemaindtopay.setVisibility(View.VISIBLE);
    }


    public void disableEdittext(EditText target) {
        target.setEnabled(false);
        /*target.setTag(target.getKeyListener());
        target.setKeyListener(null);
        target.setFocusable(false);*/
    }

    public void enableEdittext(EditText target) {
        target.setEnabled(true);
        /*target.setKeyListener((KeyListener) target.getTag());
        target.setFocusable(true);*/
    }

    public void openChatapp() {
        Intent i;

        if (item_transaction.getCustomerCustomerChatappId().equals("1")) {
            i = new Intent(Intent.ACTION_VIEW);
//            customer_number =
            String url = Constant.URL_WA_DIRECTCHAT + customer_number;
            i.setData(Uri.parse(url));
        } else {
            PackageManager pm = getPackageManager();
            i = pm.getLaunchIntentForPackage(appPackage);
        }
        try {
            startActivity(i);
        } catch (Exception e) {
            Toast.makeText(this, appName + " Not Installed", Toast.LENGTH_SHORT).show();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onSuccesPayment(com.inspiraspace.jokulid.model.preaddtransaction.Response payments) {
        dataPayments = payments.getArrPayment();
        dataChatapps = payments.getArrChatapp();

        adpSpinnerChatapp = new AdpSpinnerChatapp(this, R.id.tv_item_chatapp, dataChatapps);
        sp_detailtr_ety_chatapp.setAdapter(adpSpinnerChatapp);

        adpSpinnerPayment = new AdpSpinnerPayment(this, R.id.tv_item_payment, dataPayments);
        sp_detailtr_ety_paymentmethod.setAdapter(adpSpinnerPayment);

        /*enableEdittext(edt_detailtr_ety_customername);
        enableEdittext(edt_detailtr_ety_customernumber);
        enableEdittext(edt_detailtr_ety_customeraddr);
        enableEdittext(edt_detailtr_ety_customeritemtotalprice);
        enableEdittext(edt_detailtr_ety_customershippmentfee);
        enableEdittext(edt_detailtr_ety_customernote);*/
    }

    @Override
    public void onFailure(String message) {

    }

    public void shareTextToChatapp(String text_chat) {
        PackageManager pm = getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(appPackage, PackageManager.GET_META_DATA);

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);

            sendIntent.setPackage(appPackage);
            sendIntent.putExtra(Intent.EXTRA_TEXT, text_chat);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, appName + " Not Installed", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.btn_detailtr_sendresi)
    public void onViewClicked() {
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_sendresi);

//        TODO: why this not visible??
        dialog.setTitle("jgujg");

        // set the custom dialog components - text, image and button
        TextInputEditText edt_sendresi_number = dialog.findViewById(R.id.edt_sendresi_number);
        Spinner sp_sendresi_shippmentcompany = dialog.findViewById(R.id.sp_sendresi_shippmentcompany);
        Button btn_sendresi_send = dialog.findViewById(R.id.btn_sendresi_send);

        sp_sendresi_shippmentcompany.setAdapter(new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, Constant.LIST_SHIPPMENTCOMPANY));
        // if button is clicked, close the custom dialog
        btn_sendresi_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.toolbar_detailtr_chatting:
                openChatapp();
                break;
            case R.id.toolbar_detailtr_sendinvoice:
                shareTextToChatapp("invoice");
                break;
            case R.id.toolbar_detailtr_changestatus_pending:
                newtransactionStatus = "9";
                break;
            case R.id.toolbar_detailtr_changestatus_paid:
                newtransactionStatus = "1";
                break;
            case R.id.toolbar_detailtr_changestatus_shipped:
                newtransactionStatus = "2";
                break;
            case R.id.toolbar_detailtr_changestatus_done:
                newtransactionStatus = "11";
                break;
            case R.id.toolbar_detailtr_changestatus_cancel:
                newtransactionStatus = "12";
                break;
            case R.id.home:
                System.out.println("back pressed");
                break;
            default:
                break;
        }

        changeStatusTransaction();

        return true;
    }

    private void changeStatusTransaction() {
        if (!newtransactionStatus.equals(""))
            postTransactionStatus.updateTransactionStatus(idTransaction, newtransactionStatus);
    }


    @Override
    public void OnSuccessUpdateTransactionStatus() {
        finish();
    }

    @Override
    public void OnErrorUpdateTransactionStatus(String errmsg) {

    }

}
