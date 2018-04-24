package com.inspiraspace.jokulid.presenter.shippmentfare;

import android.content.Context;
import android.widget.AutoCompleteTextView;

import com.inspiraspace.jokulid.model.rajaongkir.Cost;
import com.inspiraspace.jokulid.model.rajaongkir.Item_Ongkir;
import com.inspiraspace.jokulid.model.rajaongkir.Result;
import com.inspiraspace.jokulid.model.searchsubdistrict.Findings;
import com.inspiraspace.jokulid.network.inspiralocal.PulseFindingsSubdistrict;
import com.inspiraspace.jokulid.network.ongkir.PulseOngkir;
import com.inspiraspace.jokulid.presenter.GeneratorFindings;
import com.inspiraspace.jokulid.presenter.GeneratorOngkir;
import com.inspiraspace.jokulid.utils.Clipboard_Utils;
import com.inspiraspace.jokulid.utils.Constant;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mursitaffandi on 4/18/18.
 */

public class PShippmentFare implements OnPresentShippmentfare, PulseOngkir, PulseFindingsSubdistrict {
    String weightgram;
    String searchAddress;
    String originID;
    String destinationID;
    GeneratorOngkir generatorOngkir;
    OnViewShippmentfare onViewShippmentfare;
    GeneratorFindings generatorFindings;
    Context mContext;

    public PShippmentFare(Context context, OnViewShippmentfare onViewShippmentfare) {
        this.mContext = context;
        this.onViewShippmentfare = onViewShippmentfare;
        generatorFindings = new GeneratorFindings(this);
    }

    @Override
    public void OnCount(String weight, String originID, String destinantionId) {
        this.weightgram = weight;
        this.originID = originID;
        this.destinationID = destinantionId;
        countFare(weightgram, originID, destinantionId);
    }

    @Override
    public void OnSearchAddress(String keyAddressOrigin, AutoCompleteTextView field) {
        generatorFindings.searchSubdistrict(keyAddressOrigin, field);
    }

    @Override
    public void OnClickCopyOngkir(Map<String, String> item) {
        StringBuilder toClipboard = new StringBuilder();
        if (!item.isEmpty()) {
            for (Map.Entry<String, String> entry : item.entrySet()) {
                toClipboard.append(entry.getValue()).append("\n");
            }
            Clipboard_Utils.copyToClipboard(mContext, toClipboard.toString());
        }
    }


    private void countFare(String weight, String originID, String destinantionId) {
        generatorOngkir = new GeneratorOngkir(this);
        generatorOngkir.getOngkir(
                originID,
                "subdistrict",
                destinationID,
                "subdistrict",
                weightgram,
                Constant.COMPANY_ENABLE_SERVICE
        );
    }

    @Override
    public void onSuccessGetOngkir(List<Result> resultOngkir) {
        List<Item_Ongkir> mapVal = new ArrayList<>();
        NumberFormat formatter = new DecimalFormat("#.###");
        for (int i = 0; i < resultOngkir.size(); i++) {
            Result jObjVal = resultOngkir.get(i);
            String strCourier = jObjVal.getCode();
            List<Cost> subJArray = jObjVal.getCosts();
            for (int j = 0; j < subJArray.size(); j++) {
                Cost subJObjVal = subJArray.get(j);
                String strServiceType = subJObjVal.getService();
                List<Cost> subOfArray = subJObjVal.getCost();
                for (int k = 0; k < subOfArray.size(); k++) {
                    Cost subOfJObj = subOfArray.get(k);
                    String strEtd = subOfJObj.getEtd();
                    long intCost = subOfJObj.getValue();
                    String strCost = formatter.format(intCost);
                    String strOngkir;
                    if (strEtd.toUpperCase().contains("HARI")
                            || strEtd.toUpperCase().contains("JAM")) {
                        strOngkir = strCourier.toUpperCase() + " - " + "Rp. " + strCost + " - " + strServiceType + " - " + strEtd;
                    } else {
                        strOngkir = strCourier.toUpperCase() + " - " + "Rp. " + strCost + " - " + strServiceType + " - " + strEtd + " HARI";
                    }
                    mapVal.add(new Item_Ongkir(strOngkir));
                }
            }
        }
        onViewShippmentfare.OnSuccessShippmentfare(mapVal);
    }

    @Override
    public void onFailOccureTransactions(String errmsg) {

    }

    @Override
    public void onSuccessFindingsSubdistrict(Findings findins, AutoCompleteTextView field) {
        onViewShippmentfare.OnSuccessFindingsAddress(findins.getArrDatum(), field);
    }

    @Override
    public void onFailFindins(String errmsg) {

    }
}
