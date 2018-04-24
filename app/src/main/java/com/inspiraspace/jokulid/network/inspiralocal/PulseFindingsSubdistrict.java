package com.inspiraspace.jokulid.network.inspiralocal;

import android.widget.AutoCompleteTextView;

import com.inspiraspace.jokulid.model.searchsubdistrict.Datum;
import com.inspiraspace.jokulid.model.searchsubdistrict.Findings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mursitaffandi on 4/13/18.
 */

public interface PulseFindingsSubdistrict {
    void onSuccessFindingsSubdistrict(Findings findins, AutoCompleteTextView field);
    void onFailFindins(String errmsg);
}
