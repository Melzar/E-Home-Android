package com.ecode.ehome.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;

/**
 * Created by matuszewski on 06/05/16.
 */
public class AlertHelper extends ContextWrapper {

    private AlertDialog alertDialog;

    public AlertHelper(Context base) {
        super(base);
    }

    public void showAlert(String message, String title){
        if(alertDialog != null){
            alertDialog.dismiss();
            alertDialog = null;
        }
        this.alertDialog = generateAlert(message,title);
        this.alertDialog.show();
    }

    private AlertDialog generateAlert(String message, String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        return builder.create();
    }
}
