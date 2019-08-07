package com.android.jarvis.gui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.jarvis.R;
import com.android.jarvis.modelos.Cuenta;
import com.squareup.picasso.Picasso;

import java.io.File;

public class CuentaViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageViewFoto;
    private TextView textViewDni;
    private TextView textViewNombre;

    public CuentaViewHolder(View itemView) {
        super(itemView);
        this.textViewDni = itemView.findViewById(R.id.RecyclerViewCuenta_textView_dni);
        this.textViewNombre = itemView.findViewById(R.id.RecyclerViewCuenta_textView_nombre);
        this.imageViewFoto = itemView.findViewById(R.id.RecyclerViewCuenta_imageView_foto);
    }

    public void bind(final Cuenta cuenta, final CuentaAdapter.OnItemClickListener listener) {
        textViewDni.setText(cuenta.getDni());
        textViewNombre.setText(cuenta.getNombre());
        Picasso.get().load(new File(cuenta.getFoto())).fit().into(imageViewFoto);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(cuenta, getAdapterPosition());
            }
        });
    }

}
