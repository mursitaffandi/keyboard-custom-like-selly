package com.inspiraspace.jokulid.subactivities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.KeyListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.adapter.AdpLVItemTransaction;
import com.inspiraspace.jokulid.adapter.AdpLVLogTransaction;
import com.inspiraspace.jokulid.adapter.AdpSpinnerChatapp;
import com.inspiraspace.jokulid.adapter.AdpSpinnerPayment;
import com.inspiraspace.jokulid.model.ListChatapp;
import com.inspiraspace.jokulid.model.transactions.Item;
import com.inspiraspace.jokulid.model.transactions.Log;
import com.inspiraspace.jokulid.model.transactions.Response;
import com.inspiraspace.jokulid.presenter.PresenterPreDetailTransaction;
import com.inspiraspace.jokulid.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailTransaction extends AppCompatActivity implements PresenterPreDetailTransaction.PulsePreDetailTransaction{

    @BindView(R.id.tv_detailtr_lb_customername)
    TextView tv_detailtr_lb_customername;

    @BindView(R.id.edt_detailtr_ety_customername)
    EditText edt_detailtr_ety_customername;

    @BindView(R.id.tv_detailtr_lb_chatapp)
    TextView tv_detailtr_lb_chatapp;

    @BindView(R.id.sp_detailtr_ety_chatapp)
    Spinner sp_detailtr_ety_chatapp;

    @BindView(R.id.tv_detailtr_lb_customernumber)
    TextView tv_detailtr_lb_customernumber;

    @BindView(R.id.edt_detailtr_ety_customernumber)
    EditText edt_detailtr_ety_customernumber;

    @BindView(R.id.tv_detailtr_lb_customeraddr)
    TextView tv_detailtr_lb_customeraddr;

    @BindView(R.id.edt_detailtr_ety_customeraddr)
    EditText edt_detailtr_ety_customeraddr;

    @BindView(R.id.tv_detailtr_lb_customeritemtotalprice)
    TextView tv_detailtr_lb_customeritemtotalprice;

    @BindView(R.id.edt_detailtr_ety_customeritemtotalprice)
    EditText edt_detailtr_ety_customeritemtotalprice;

    @BindView(R.id.tv_detailtr_lb_paymentmethod)
    TextView tv_detailtr_lb_paymentmethod;

    @BindView(R.id.sp_detailtr_ety_paymentmethod)
    Spinner sp_detailtr_ety_paymentmethod;

    @BindView(R.id.tv_detailtr_lb_customershippmentfee)
    TextView tv_detailtr_lb_customershippmentfee;

    @BindView(R.id.edt_detailtr_ety_customershippmentfee)
    EditText edt_detailtr_ety_customershippmentfee;

    @BindView(R.id.tv_detailtr_lb_customernote)
    TextView tv_detailtr_lb_customernote;

    @BindView(R.id.edt_detailtr_ety_customernote)
    EditText edt_detailtr_ety_customernote;

    @BindView(R.id.tv_detailtr_lb_customeritem)
    TextView tv_detailtr_lb_customeritem;

    @BindView(R.id.btn_detailtr_add_customeritem)
    Button btn_detailtr_add_customeritem;

    @BindView(R.id.lv_detailtr_ety_customeritem)
    ListView lv_detailtr_ety_customeritem;

    @BindView(R.id.tv_detailtr_lb_customerlog)
    TextView tv_detailtr_lb_customerlog;

    @BindView(R.id.btn_detailtr_add_customerlog)
    Button btn_detailtr_add_customerlog;

    @BindView(R.id.lv_detailtr_ety_customerlog)
    ListView lv_detailtr_ety_customerlog;

    @BindView(R.id.toolbarDetail)
    Toolbar toolbar;

    Intent rc_transaction;
    String customer_name,
            customer_chatapp_id, customer_number,
            customer_addr,
            customer_totalitemplprice, customer_paymentmethod_name, customer_paymentmethod_number,
            customer_shippmentfee,
            customer_note;

    List<Item> customer_items;
    List<Log> customer_logs;

    private ArrayList dataChatapps;
    private ArrayList dataPayments;
    private com.inspiraspace.jokulid.model.preaddtransaction.Response preaddtransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaction);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        new PresenterPreDetailTransaction(this);
        rc_transaction = getIntent();

        disableEdittext(edt_detailtr_ety_customername);
        disableEdittext(edt_detailtr_ety_customernumber);
        disableEdittext(edt_detailtr_ety_customeraddr);
        disableEdittext(edt_detailtr_ety_customeritemtotalprice);
        disableEdittext(edt_detailtr_ety_customershippmentfee);
        disableEdittext(edt_detailtr_ety_customernote);
    }

    private void setupViewFromIntent() {
        Response item_transaction = rc_transaction.getParcelableExtra(Constant.TAG_TRANSACTION);
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


        edt_detailtr_ety_customername.setText(customer_name);
        edt_detailtr_ety_customernumber.setText(customer_number);
        edt_detailtr_ety_customeraddr.setText(customer_addr);
        edt_detailtr_ety_customeritemtotalprice.setText(customer_totalitemplprice);
        edt_detailtr_ety_customershippmentfee.setText(customer_shippmentfee);
        edt_detailtr_ety_customernote.setText(customer_note);

        AdpSpinnerChatapp adpSpinnerChatapp = new AdpSpinnerChatapp(this, R.id.tv_item_chatapp, dataChatapps);
        sp_detailtr_ety_chatapp.setAdapter(adpSpinnerChatapp);
        /*sp_detailtr_ety_chatapp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/

        sp_detailtr_ety_chatapp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("sp_detailtr_ety_chatapp.setOnItemSelectedListener" + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        AdpSpinnerPayment adpSpinnerPayment = new AdpSpinnerPayment(this, R.id.tv_item_payment, dataPayments);
        sp_detailtr_ety_paymentmethod.setAdapter(adpSpinnerPayment);

        AdpLVLogTransaction adpLVLogTransaction = new AdpLVLogTransaction(item_transaction.getLog(), this);
        lv_detailtr_ety_customerlog.setAdapter(adpLVLogTransaction);

        AdpLVItemTransaction adpLVItemTransaction = new AdpLVItemTransaction(item_transaction.getItem(), this);
        lv_detailtr_ety_customeritem.setAdapter(adpLVItemTransaction);

        enableEdittext(edt_detailtr_ety_customername);
        enableEdittext(edt_detailtr_ety_customernumber);
        enableEdittext(edt_detailtr_ety_customeraddr);
        enableEdittext(edt_detailtr_ety_customeritemtotalprice);
        enableEdittext(edt_detailtr_ety_customershippmentfee);
        enableEdittext(edt_detailtr_ety_customernote);
    }


    public void disableEdittext(EditText target) {
       /* target.setTag(target.getKeyListener());
        target.setKeyListener(null);
        target.setFocusable(false);*/
    }

    public void enableEdittext(EditText target) {
        target.setKeyListener((KeyListener) target.getTag());
        target.setFocusable(true);
    }

    public void openChatapp(int id_chat) {
        Intent i;
        id_chat = --id_chat;
        String appPackage = preaddtransaction.getChatapp().get(id_chat).getPackage();
        String appName = preaddtransaction.getChatapp().get(id_chat).getName();
        if (id_chat == 0) {
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

    public void shareTextToChatapp(int id_chat, String text_chat) {
        String appPackage = preaddtransaction.getChatapp().get(id_chat).getPackage();
        String appName = preaddtransaction.getChatapp().get(id_chat).getName();
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

    @Override
    public void onSuccesPayment(com.inspiraspace.jokulid.model.preaddtransaction.Response payments) {
        preaddtransaction = payments;
        dataPayments = payments.getArrPayment();
        dataChatapps = payments.getArrChatapp();

        setupViewFromIntent();
    }

    @Override
    public void onFailure(String message) {

    }
}
