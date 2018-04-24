package com.inspiraspace.jokulid.presenter.shippmentfare;

import android.widget.AutoCompleteTextView;

import java.util.Map;

/**
 * Created by mursitaffandi on 4/18/18.
 */

public interface OnPresentShippmentfare {
    void OnCount(String weight, String originID, String destinantionId);
    void OnSearchAddress(String keyAddressOrigin, AutoCompleteTextView field);

    void OnClickCopyOngkir(Map<String,String> str);
}
