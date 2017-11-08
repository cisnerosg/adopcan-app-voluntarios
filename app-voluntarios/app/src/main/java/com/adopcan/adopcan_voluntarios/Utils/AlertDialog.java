package com.adopcan.adopcan_voluntarios.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog.Builder;

import com.adopcan.adopcan_voluntarios.R;


/**
 * Created by german on 12/8/2017.
 */

public class AlertDialog {

    public void showAlertWithAcept(Context context, String title, String description) {
        Builder msg = new Builder(context);
        msg.setMessage(description);
        msg.setIcon(R.drawable.alerta);
        msg.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        msg.setTitle(title);
        msg.create();
        msg.show();
    }


}
