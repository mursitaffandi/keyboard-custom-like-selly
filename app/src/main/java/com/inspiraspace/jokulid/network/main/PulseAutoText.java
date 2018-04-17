package com.inspiraspace.jokulid.network.main;

import com.inspiraspace.jokulid.model.autotext.Response;

import java.util.List;

/**
 * Created by mursitaffandi on 4/12/18.
 */

public interface PulseAutoText {
    void onSuccess(List<Response> response);
    void onError(String msgerror);
}
