package com.inspiraspace.jokulid.subactivities;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.model.shop.Response;
import com.inspiraspace.jokulid.network.main.PulseShop;
import com.inspiraspace.jokulid.presenter.GeneratorShop;
import com.inspiraspace.jokulid.utils.BaseAuthActivity;
import com.inspiraspace.jokulid.utils.UtilValidation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailShopActivity extends BaseAuthActivity implements PulseShop{

    @BindView(R.id.etd_shop_name)
    TextInputEditText etdShopName;
    @BindView(R.id.edt_shop_nohp)
    TextInputEditText edtShopNohp;
    @BindView(R.id.edt_shop_url)
    TextInputEditText edtShopUrl;

    GeneratorShop generatorShop;
    String shopName = "", shopNohp ="", shopUrl = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_shop);
        ButterKnife.bind(this);
generatorShop = new GeneratorShop(this);
generatorShop.getShop();
    }

    @OnClick(R.id.btn_shop_save)
    public void onViewClicked() {
        shopName = etdShopName.getText().toString();
        shopNohp = edtShopNohp.getText().toString();
        shopUrl = edtShopUrl.getText().toString();

        boolean isAllFieldValid = false;

        for (EditText edt : getAllEditableField())
            isAllFieldValid = UtilValidation.edittextValidation(edt);

        if (isAllFieldValid)
        generatorShop.updateShop(shopName, shopNohp,shopUrl);
    }

    private EditText[] getAllEditableField() {
        return new EditText[]{
                etdShopName,
                edtShopUrl,
                edtShopNohp
        };
    }

    @Override
    public void OnSuccessShop(Response responseShops) {
        etdShopName.setText(responseShops.getTokoName());
        edtShopNohp.setText(responseShops.getTokoNoHp());
        edtShopUrl.setText(responseShops.getTokoUrl());
    }

    @Override
    public void OnErrorShop(String errmsg) {

    }

    @Override
    public void OnSuccessUpdateShop() {
        Toast.makeText(this, "Detail Shop Updated", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void OnErrorUpdateShop(String errmsg) {
        Toast.makeText(this, "Fail Update Detail Shop", Toast.LENGTH_SHORT).show();
    }
}
