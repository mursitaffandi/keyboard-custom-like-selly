package com.inspiraspace.jokulid.network.inspiralocal;

import android.widget.AutoCompleteTextView;

import com.inspiraspace.jokulid.model.searchsubdistrict.Datum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mursitaffandi on 4/13/18.
 */

public interface PulseFindingsSubdistrict {
    void onSuccessFindingsSubdistrict(ArrayList<Datum> findins, AutoCompleteTextView field);
    void onFailFindins(String errmsg);
}
