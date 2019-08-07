package com.android.jarvis.gui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.jarvis.R;
import com.android.jarvis.gui.activities.MainActivity;
import com.android.jarvis.modelos.Acceso;
import com.android.jarvis.modelos.Cuenta;
import com.squareup.picasso.Picasso;

import java.io.File;

public class AccesoViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageViewFoto;
    private TextView textViewDni;
    private TextView textViewFecha;
    private TextView textViewHora;

    public AccesoViewHolder(View itemView) {
        super(itemView);
        this.textViewDni= itemView.findViewById(R.id.RecyclerViewAcceso_textView_dni);
        this.textViewFecha = itemView.findViewById(R.id.RecyclerViewAcceso_textView_fecha);
        this.textViewHora = itemView.findViewById(R.id.RecyclerViewAcceso_textView_hora);
        this.imageViewFoto = itemView.findViewById(R.id.RecyclerViewAcceso_imageView_foto);
    }

    public void bind(final Acceso acceso) {
        Cuenta cuenta = MainActivity.realm.where(Cuenta.class).equalTo("dni", acceso.getCuentaDni()).findFirst();
        if (cuenta != null) {
            textViewDni.setText(cuenta.getDni());
            textViewFecha.setText(acceso.getFecha());
            textViewHora.setText(acceso.getHora());
            Picasso.get().load(new File(cuenta.getFoto())).fit().into(imageViewFoto);
        }
    }

}
