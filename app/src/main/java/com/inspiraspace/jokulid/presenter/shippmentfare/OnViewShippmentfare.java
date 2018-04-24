package com.inspiraspace.jokulid.presenter.shippmentfare;

import android.widget.AutoCompleteTextView;

import com.inspiraspace.jokulid.model.rajaongkir.Cost;
import com.inspiraspace.jokulid.model.rajaongkir.Item_Ongkir;
import com.inspiraspace.jokulid.model.rajaongkir.Result;
import com.inspiraspace.jokulid.model.searchsubdistrict.Datum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mursitaffandi on 4/18/18.
 */

public interface OnViewShippmentfare {
    void OnSuccessFindingsAddress(ArrayList<Datum> datumListAddress, AutoCompleteTextView field);
    void OnSuccessShippmentfare(List<Item_Ongkir> resultListOngkir);
}
