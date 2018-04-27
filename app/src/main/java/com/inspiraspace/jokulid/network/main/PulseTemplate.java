package com.inspiraspace.jokulid.network.main;

import com.inspiraspace.jokulid.model.template.ResponseTemplate;

import java.util.List;

/**
 * Created by mursitaffandi on 4/24/18.
 */

public interface PulseTemplate {
    void OnSuccessTemplate(ResponseTemplate responseTemplates);
    void OnErrorTemplate(String errmsg);

    void OnSuccessUpdateTemplate();
    void OnErrorUpdateTemplate(String errmsg);
}
