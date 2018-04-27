package com.inspiraspace.jokulid.network.main;
import com.inspiraspace.jokulid.model.shop.Response;

/**
 * Created by mursitaffandi on 4/26/18.
 */

public interface PulseShop {
    void OnSuccessShop(Response responseShops);
    void OnErrorShop(String errmsg);

    void OnSuccessUpdateShop();
    void OnErrorUpdateShop(String errmsg);
}
