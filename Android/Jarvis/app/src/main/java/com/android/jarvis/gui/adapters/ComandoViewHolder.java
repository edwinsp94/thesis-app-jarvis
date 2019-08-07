package com.android.jarvis.gui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.jarvis.R;
import com.android.jarvis.gui.fragments.ListaComandosFragment;
import com.android.jarvis.modelos.Comando;

public class ComandoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private CheckBox checkBox;
    private TextView textViewNombre;
    private ListaComandosFragment listaComandosFragment;

    public ComandoViewHolder(View itemView, ListaComandosFragment listaComandosFragment) {
        super(itemView);
        this.checkBox = itemView.findViewById(R.id.RecyclerViewComando_checkBox);
        this.textViewNombre = itemView.findViewById(R.id.RecyclerViewComando_textView_nombre);
        this.listaComandosFragment = listaComandosFragment;
    }

    public void bind(final Comando comando,
                     final ComandoAdapter.OnItemClickListener clickListener,
                     final ComandoAdapter.OnItemLongListener longClikListener) {
        textViewNombre.setText(comando.getNombre());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(comando, getAdapterPosition());
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                longClikListener.onItemLongClick(comando, getAdapterPosition());
                return  true;
            }
        });
        if (!listaComandosFragment.estaModoAccion) {
            checkBox.setVisibility(View.INVISIBLE);
        } else {
            checkBox.setVisibility(View.VISIBLE);
        }
        checkBox.setChecked(comando.isSeleccionado());
        checkBox.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        listaComandosFragment.clickCheckBox(checkBox.isChecked(), getAdapterPosition());
    }

}
