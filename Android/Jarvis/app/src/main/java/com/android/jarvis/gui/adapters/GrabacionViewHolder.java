package com.android.jarvis.gui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.jarvis.R;
import com.android.jarvis.gui.fragments.ListaGrabacionesFragment;
import com.android.jarvis.modelos.Grabacion;

public class GrabacionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private CheckBox checkBox;
    private TextView textViewNombre;
    private TextView textViewFecha;
    private TextView textViewDuracion;
    private ListaGrabacionesFragment listaGrabacionesFragment;

    public GrabacionViewHolder(View itemView, ListaGrabacionesFragment listaGrabacionesFragment) {
        super(itemView);
        this.checkBox = itemView.findViewById(R.id.RecyclerViewGrabacion_checkBox);
        this.textViewNombre = itemView.findViewById(R.id.RecyclerViewGrabacion_textView_nombre);
        this.textViewFecha = itemView.findViewById(R.id.RecyclerViewGrabacion_textView_fecha);
        this.textViewDuracion = itemView.findViewById(R.id.RecyclerViewGrabacion_textView_duracion);
        this.listaGrabacionesFragment = listaGrabacionesFragment;
    }

    public void bind(final Grabacion grabacion, final GrabacionAdapter.OnItemClickListener clickListener,
                     final GrabacionAdapter.OnItemLongListener longClikListener) {
        textViewNombre.setText(grabacion.getNombre());
        textViewFecha.setText(grabacion.getFecha());
        textViewDuracion.setText(grabacion.getDuracion());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(grabacion, getAdapterPosition());
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                longClikListener.onItemLongClick(grabacion, getAdapterPosition());
                return  true;
            }
        });
        if (!listaGrabacionesFragment.estaModoAccion) {
            checkBox.setVisibility(View.INVISIBLE);
        } else {
            checkBox.setVisibility(View.VISIBLE);
        }
        checkBox.setChecked(grabacion.isSeleccionado());
        checkBox.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        listaGrabacionesFragment.clickCheckBox(checkBox.isChecked(), getAdapterPosition());
    }
}
