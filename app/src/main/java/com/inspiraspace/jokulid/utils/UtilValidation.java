package com.inspiraspace.jokulid.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.inspiraspace.jokulid.JokulidApplication;
import com.inspiraspace.jokulid.R;

/**
 * Created by mursitaffandi on 5/3/18.
 */

public class UtilValidation {
    Context context;

    public UtilValidation(Context context) {
        this.context = context;
    }

    public static boolean edittextValidation(EditText field){
        field.setError(null);
        String content = field.getText().toString();
        if (!TextUtils.isEmpty(content)){
            return true;
        } else {
            field.setError(JokulidApplication.getInstance().getString(R.string.field_empty));
            field.requestFocus();
            return false;
        }
    }

    public static boolean stringValidation(String content){
        if (!TextUtils.isEmpty(content)){
            return true;
        } else {
            return false;
        }
    }

    public static boolean autocomplateValidation(String value, AutoCompleteTextView field){
        field.setError(null);
        String content = field.getText().toString();
        if (!TextUtils.isEmpty(content) && !TextUtils.isEmpty(value)){
            return true;
        } else {
            field.setError(JokulidApplication.getInstance().getString(R.string.field_autocomplate_empty));
            field.requestFocus();
            return false;
        }
    }
}
