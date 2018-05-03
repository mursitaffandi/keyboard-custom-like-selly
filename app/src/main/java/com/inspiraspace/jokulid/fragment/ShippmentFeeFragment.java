package com.inspiraspace.jokulid.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.inspiraspace.jokulid.JokulidApplication;
import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.adapter.AdpAutocomplateAddress;
import com.inspiraspace.jokulid.adapter.AdpLVResultOngkir;
import com.inspiraspace.jokulid.model.rajaongkir.Item_Ongkir;
import com.inspiraspace.jokulid.model.searchsubdistrict.Datum;
import com.inspiraspace.jokulid.presenter.shippmentfare.OnViewShippmentfare;
import com.inspiraspace.jokulid.presenter.shippmentfare.PShippmentFare;
import com.inspiraspace.jokulid.utils.UtilValidation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShippmentFeeFragment extends Fragment implements TextWatcher, OnViewShippmentfare, View.OnClickListener {
    @BindView(R.id.etItemWeight)
    TextInputEditText etItemWeight;

    @BindView(R.id.etFrom)
    AutoCompleteTextView etFrom;

    @BindView(R.id.etDestination)
    AutoCompleteTextView etDestination;

    @BindView(R.id.btn_count_shippmentfee)
    Button btn_count_shippmentfee;

    @BindView(R.id.listOngkir)
    ListView listOngkir;

    @BindView(R.id.btn_shippmentfee_copytoclipboard)
    Button btn_shippmentfee_copytoclipboard;

    Unbinder unbinder;
    AdpAutocomplateAddress autoTextAdapter;
    AdpLVResultOngkir adpResultOngkir;

    ArrayList<Datum> arrSubdistrict;
    List<Item_Ongkir> resultOngkir = new ArrayList<>();

    private String idShippmentOrigin = null;
    private String idShippmentDestination = null;
    private String weightShippment = null;
    private Context mContext;
    private PShippmentFare onPresentShippmentfare;

    public ShippmentFeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.subcdt_shippmentfee, container, false);
        unbinder = ButterKnife.bind(this, view);

        this.mContext = this.getContext();
        onPresentShippmentfare = new PShippmentFare(mContext, this);
        adpResultOngkir = new AdpLVResultOngkir(mContext, resultOngkir);
        listOngkir.setAdapter(adpResultOngkir);

        etFrom.setThreshold(3);
        etDestination.setThreshold(3);

        etFrom.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        idShippmentOrigin = arrSubdistrict.get(position).getSubdistrictId();
                    }
                }
        );

        etDestination.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        idShippmentDestination = arrSubdistrict.get(position).getSubdistrictId();
                    }
                }
        );
        etItemWeight.setText(JokulidApplication.getInstance().getShippmentWeight());
        etFrom.setText(JokulidApplication.getInstance().getShippmentOrigin().get(JokulidApplication.KEY_SHIPPMENT_ORIGIN ));
        idShippmentOrigin = JokulidApplication.getInstance().getShippmentOrigin().get(JokulidApplication.KEY_SHIPPMENT_ORIGIN_ID);

        etFrom.addTextChangedListener(this);
        etDestination.addTextChangedListener(this);


        btn_count_shippmentfee.setOnClickListener(this);
        btn_shippmentfee_copytoclipboard.setOnClickListener(this);
        return view;
    }

    public void searchSubdistrict(String keyword, AutoCompleteTextView field) {
        onPresentShippmentfare.OnSearchAddress(keyword, field);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (etFrom.isFocused()) {
            idShippmentOrigin = null;
            if (s.length() >= etFrom.getThreshold()) {
                searchSubdistrict(etFrom.getText().toString(), etFrom);
            }
        } else if (etDestination.isFocused()) {
            idShippmentDestination = null;
            if (s.length() >= etDestination.getThreshold()) {
                searchSubdistrict(etDestination.getText().toString(), etDestination);
            }
        }
        weightShippment = etItemWeight.getText().toString();
    }

    private void showAutocomplateDropdown(AutoCompleteTextView field, ArrayList<Datum> listAddress) {
        autoTextAdapter = new AdpAutocomplateAddress(mContext, R.layout.item_subdistrict, listAddress);
        autoTextAdapter.setDropDownViewResource(R.layout.item_subdistrict);

        field.setAdapter(autoTextAdapter);
        if (field.getText().length() >= field.getThreshold() && !autoTextAdapter.isEmpty()) {
            field.showDropDown();
        }
    }

    @Override
    public void OnSuccessFindingsAddress(ArrayList<Datum> datumListAddress, AutoCompleteTextView field) {
        arrSubdistrict = datumListAddress;
        showAutocomplateDropdown(field, datumListAddress);
    }

    @Override
    public void OnSuccessShippmentfare(List<Item_Ongkir> resultListOngkir) {
        listOngkir.setVisibility(View.VISIBLE);
        this.resultOngkir = resultListOngkir;
        adpResultOngkir.swipeRefresh(resultOngkir);
        adpResultOngkir.notifyDataSetChanged();
        if (resultListOngkir.size() > 0)
            btn_shippmentfee_copytoclipboard.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_shippmentfee_copytoclipboard:
                onPresentShippmentfare.OnClickCopyOngkir(adpResultOngkir.getItemsOngkir_selected());
                break;

            case R.id.btn_count_shippmentfee:
                weightShippment = etItemWeight.getText().toString();
                boolean isAllFieldValid = false;

                for (EditText edt : getAllEditableField())
                    isAllFieldValid = UtilValidation.edittextValidation(edt);

                for (String s : getAllStringField())
                    isAllFieldValid = UtilValidation.stringValidation(s);

                if (isAllFieldValid)
                onPresentShippmentfare.OnCount(weightShippment, idShippmentOrigin, idShippmentDestination);
                break;
        }
    }

    //    TODO : Put all editable view here
    private EditText[] getAllEditableField() {
        return new EditText[]{
                etItemWeight,
                etFrom,
                etDestination
        };
    }

    private String[] getAllStringField() {
        return new String[]{
                weightShippment, idShippmentOrigin, idShippmentDestination
        };
    }


}