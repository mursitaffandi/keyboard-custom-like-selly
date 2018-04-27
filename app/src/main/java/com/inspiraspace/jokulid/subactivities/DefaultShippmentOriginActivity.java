package com.inspiraspace.jokulid.subactivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.inspiraspace.jokulid.JokulidApplication;
import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.adapter.AdpAutocomplateAddress;
import com.inspiraspace.jokulid.model.rajaongkir.Item_Ongkir;
import com.inspiraspace.jokulid.model.searchsubdistrict.Datum;
import com.inspiraspace.jokulid.presenter.shippmentfare.OnViewShippmentfare;
import com.inspiraspace.jokulid.presenter.shippmentfare.PShippmentFare;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DefaultShippmentOriginActivity extends AppCompatActivity implements OnViewShippmentfare {

    @BindView(R.id.edt_defaultweight_origin)
    AutoCompleteTextView edtDefaultweightOrigin;
    AdpAutocomplateAddress autoTextAdapter;
    ArrayList<Datum> arrSubdistrict;
    private String idShippmentOrigin, settedDefaultShippmentOrigin;
    private PShippmentFare onPresentShippmentfare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_shippment_origin);
        ButterKnife.bind(this);
        onPresentShippmentfare = new PShippmentFare(this);
        edtDefaultweightOrigin.setThreshold(3);

        edtDefaultweightOrigin.setText(JokulidApplication.getInstance().getShippmentOrigin().get(JokulidApplication.KEY_SHIPPMENT_ORIGIN ));
        idShippmentOrigin = JokulidApplication.getInstance().getShippmentOrigin().get(JokulidApplication.KEY_SHIPPMENT_ORIGIN_ID);

        edtDefaultweightOrigin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                idShippmentOrigin = null;
                settedDefaultShippmentOrigin = null;
                if (s.length() >= edtDefaultweightOrigin.getThreshold()) {
                    searchSubdistrict(edtDefaultweightOrigin.getText().toString(), edtDefaultweightOrigin);
                }
            }
        });
    }

    public void searchSubdistrict(String keyword, AutoCompleteTextView field) {
        onPresentShippmentfare.OnSearchAddress(keyword, field);
    }

    private void showAutocomplateDropdown(AutoCompleteTextView field, ArrayList<Datum> listAddress) {
        arrSubdistrict = listAddress;
        autoTextAdapter = new AdpAutocomplateAddress(this, R.layout.item_subdistrict, arrSubdistrict);
        autoTextAdapter.setDropDownViewResource(R.layout.item_subdistrict);

        field.setAdapter(autoTextAdapter);
        if (field.getText().length() >= field.getThreshold() && !autoTextAdapter.isEmpty()) {
            field.showDropDown();
        }

        edtDefaultweightOrigin.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        idShippmentOrigin = arrSubdistrict.get(position).getSubdistrictId();
                        settedDefaultShippmentOrigin =arrSubdistrict.get(position).getFullAddress();
                    }
                }
        );
    }
    @OnClick(R.id.btn_defaultweight_save)
    public void onViewClicked() {
        JokulidApplication.getInstance().setShippmentOrigin(settedDefaultShippmentOrigin,idShippmentOrigin);
        finish();
    }

    @Override
    public void OnSuccessFindingsAddress(ArrayList<Datum> datumListAddress, AutoCompleteTextView field) {
        showAutocomplateDropdown(field,datumListAddress);
    }

    @Override
    public void OnSuccessShippmentfare(List<Item_Ongkir> resultListOngkir) {

    }
}
