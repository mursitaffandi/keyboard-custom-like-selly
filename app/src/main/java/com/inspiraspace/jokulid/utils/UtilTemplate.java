package com.inspiraspace.jokulid.utils;

import com.inspiraspace.jokulid.presenter.GeneratorTemplate;

import java.util.HashMap;

/**
 * Created by mursitaffandi on 4/30/18.
 */

public class UtilTemplate {
    String account_name = "John";
    String account_number = "0821911";
    String amount = "20000";
    String bank_name = "BRI";
    String bank_address = "90128749202";
    String customer_address = "Gamping, Slemana, DIY";
    String customer_contact = "08121341";
    String customer_name = "Beni";
    String invoice_id = "1";
    String notes = "tolong bungkus yg rapi";
    String product_list = "1 X buku";
    String shipping_cost = "10000";
    String store_name = "OL Shop";
    String total_amount = "30000";
    String shipping_provider = "JNE";
    String resi_number = "080840202048";

    HashMap<String, String> mapKeyTemplate = new HashMap<>();

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public void setBank_address(String bank_address) {
        this.bank_address = bank_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public void setCustomer_contact(String customer_contact) {
        this.customer_contact = customer_contact;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setProduct_list(String product_list) {
        this.product_list = product_list;
    }

    public void setShipping_cost(String shipping_cost) {
        this.shipping_cost = shipping_cost;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public void setShipping_provider(String shipping_provider) {
        this.shipping_provider = shipping_provider;
    }

    public void setResi_number(String resi_number) {
        this.resi_number = resi_number;
    }

    private void setMapTemplate() {
        mapKeyTemplate.put("#account-name", account_name);
        mapKeyTemplate.put("#account-number", account_number);
        mapKeyTemplate.put("#amount", amount);
        mapKeyTemplate.put("#bank-name", bank_name);
        mapKeyTemplate.put("#bank-address", bank_address);
        mapKeyTemplate.put("#customer-address", customer_address);
        mapKeyTemplate.put("#customer-contact", customer_contact);
        mapKeyTemplate.put("#customer-name", customer_name);
        mapKeyTemplate.put("#invoice-id", invoice_id);
        mapKeyTemplate.put("#notes", notes);
        mapKeyTemplate.put("#product-list", product_list);
        mapKeyTemplate.put("#shipping-cost", shipping_cost);
        mapKeyTemplate.put("#store-name", store_name);
        mapKeyTemplate.put("#total-amount", total_amount);
        mapKeyTemplate.put("#shipping-provider", shipping_provider);
        mapKeyTemplate.put("#resi-number", resi_number);
    }

    public String convertTemplate(String rawTemplate) {
        setMapTemplate();
        String replacement = rawTemplate.replace("\n", " \n ");
        String[] strSplited = replacement.split(" ");
        String strResult;
        StringBuilder builder = new StringBuilder();
        for (String s : strSplited) {
            String value = mapKeyTemplate.get(s);
            if (value != null) s = value;

            builder.append(s + " ");
        }
        strResult = builder.toString();
        return strResult;
    }
}
