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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.model.Response;
import com.inspiraspace.jokulid.model.rajaongkir.Result;
import com.inspiraspace.jokulid.network.ongkir.PulseOngkir;
import com.inspiraspace.jokulid.presenter.GeneratorFindings;
import com.inspiraspace.jokulid.presenter.GeneratorOngkir;
import com.inspiraspace.jokulid.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShippmentFeeFragment extends Fragment implements TextWatcher, PulseOngkir/*, PulseFindingsSubdistrict*/ {
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

    GeneratorFindings generatorFindings;
    ArrayAdapter autoTextAdapter;
    ArrayList<String> arrSubdistrictOrigin;
    List<Response> arrSubdistrict;
    List<Result> resultOngkir;

    private String idShippmentOrigin = null;
    private String idShippmentDestination = null;
    private Context mContext;

    public ShippmentFeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.subcdt_shippmentfee, container, false);
        ButterKnife.bind(this, view);

        this.mContext = this.getContext();
//        generatorFindings = new GeneratorFindings(this);
        arrSubdistrict = Constant.getSampleSubdistrictID().getResponse();

        arrSubdistrictOrigin = Constant.getSampleSubdistrictID().getArrSubdistrict();
        float scale = mContext.getResources().getDisplayMetrics().density;
        etFrom.setDropDownHeight((int) (100 * scale + 0.5f));
        etDestination.setDropDownHeight((int) (100 * scale + 0.5f));

        etFrom.setThreshold(3);
        etDestination.setThreshold(3);

        etFrom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                          @Override
                                          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                              idShippmentOrigin = arrSubdistrict.get(position).getId();
                                          }
                                      }
        );
        etFrom.addTextChangedListener(this);
        etDestination.addTextChangedListener(this);

        return view;
    }

    public void searchSubdistrict(String keyword, AutoCompleteTextView field) {
        generatorFindings.searchSubdistrict(keyword, field);
    }


    @Override
    public void onSuccessGetOngkir(List<Result> resultOngkir) {
        this.resultOngkir = resultOngkir;
    }

    @Override
    public void onFailOccureTransactions(String errmsg) {
        System.out.println("error ongkir" + errmsg);
    }

    /*@Override
    public void onSuccessFindingsSubdistrict(ArrayList<Datum> findins, AutoCompleteTextView field) {
        autoTextAdapter = new ArrayAdapter(mContext, R.layout.item_subdistrict, findins);
        autoTextAdapter.setDropDownViewResource(R.layout.item_subdistrict);
        float scale = getResources().getDisplayMetrics().density;
        field.setDropDownHeight((int) (100 * scale + 0.5f));
        field.setThreshold(3);
        field.setAdapter(autoTextAdapter);

        if (field.getText().length() >= field.getThreshold() && !autoTextAdapter.isEmpty()) {
            field.showDropDown();
        }
    }*/

    /*@Override
    public void onFailFindins(String errmsg) {

    }*/


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (etFrom.isFocused()) {
            if (s.length() >= etFrom.getThreshold()) {
                //searchSubdistrict(etFrom.getText().toString(), etFrom);
                autoTextAdapter = new ArrayAdapter(mContext, R.layout.item_subdistrict, arrSubdistrictOrigin);
                autoTextAdapter.setDropDownViewResource(R.layout.item_subdistrict);

                etFrom.setAdapter(autoTextAdapter);
                if (etFrom.getText().length() >= etFrom.getThreshold() && !autoTextAdapter.isEmpty()) {
                    etFrom.showDropDown();
                }
            }
        } else if (etDestination.isFocused()) {
            if (s.length() >= etDestination.getThreshold()) {
                //searchSubdistrict(etDestination.getText().toString(), etDestination);
            }
        }
    }
}