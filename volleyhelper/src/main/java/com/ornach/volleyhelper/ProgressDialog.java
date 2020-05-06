package com.ornach.volleyhelper;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.ProgressBar;


public class ProgressDialog {


    private ProgressDialog(Context context){

    }
/*
    public ProgressDialog newInstance(Context context){
        return new ProgressDialog(context);
    }*/


    private PDialog pDialog;
    private static ProgressDialog dialog;

    public static ProgressDialog show(Context context) {
        return show(context, false);
    }

    public static ProgressDialog show(Context context, boolean cancelable) {

        dialog = new ProgressDialog(context);

        dialog.pDialog = new PDialog(context);
        dialog.pDialog.setCancelable(cancelable);
        dialog.pDialog.show();

        return dialog;
    }


    public void dismiss() {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

    private static class PDialog extends Dialog {

        PDialog(@NonNull Context context) {
            super(context);
            //getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            if (getWindow()!=null) {
                getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }

            ProgressBar progressBar = new ProgressBar(context);
            progressBar.getIndeterminateDrawable().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
            setContentView(progressBar);
        }
    }
}
