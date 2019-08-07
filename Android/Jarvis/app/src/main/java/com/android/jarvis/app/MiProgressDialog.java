package com.android.jarvis.app;

import android.app.ProgressDialog;
import android.content.Context;

public class MiProgressDialog {

    private ProgressDialog progressDialog;

    public MiProgressDialog(Context context, String titulo, String mensaje) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(titulo);
        progressDialog.setMessage(mensaje);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
    }

    public void iniciar() {
        progressDialog.show();
    }

    public void detener() {
        progressDialog.dismiss();
    }
}
