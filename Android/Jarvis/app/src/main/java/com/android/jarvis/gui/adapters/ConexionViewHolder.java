package com.android.jarvis.gui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.jarvis.R;
import com.android.jarvis.modelos.Conexion;

public class ConexionViewHolder extends RecyclerView.ViewHolder  {

    private ImageView imageView;
    private TextView textViewNombre;
    private TextView textViewEstado;

    public ConexionViewHolder(View itemView) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.RecyclerViewConexion_imageView);
        this.textViewNombre = itemView.findViewById(R.id.RecyclerViewConexion_textView_nombre);
        this.textViewEstado = itemView.findViewById(R.id.RecyclerViewConexion_textView_estado);
    }

    public void bind(final Conexion conexion, final ConexionAdapter.OnItemClickListener listener) {
        imageView.setImageResource(conexion.getIcono());
        textViewNombre.setText(conexion.getNombre());
        textViewEstado.setText(conexion.getEstado());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(conexion, getAdapterPosition());
            }
        });
    }

}
