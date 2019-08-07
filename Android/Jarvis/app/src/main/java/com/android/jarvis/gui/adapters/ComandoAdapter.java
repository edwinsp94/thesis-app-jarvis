package com.android.jarvis.gui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.jarvis.gui.fragments.ListaComandosFragment;
import com.android.jarvis.modelos.Comando;

import java.util.List;

public class ComandoAdapter extends RecyclerView.Adapter<ComandoViewHolder> {

    private List<Comando> comandos;
    private int layout;
    private OnItemClickListener itemClickListener;
    private OnItemLongListener itemLongClickListener;
    private ListaComandosFragment listaComandosFragment;

    public ComandoAdapter(ListaComandosFragment listaComandosFragment,
                          List<Comando> comandos, int layout,
                          OnItemClickListener itemClickListener,
                          OnItemLongListener itemLongClickListener) {
        this.listaComandosFragment = listaComandosFragment;
        this.comandos = comandos;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
        this.itemLongClickListener = itemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Comando comando, int posicion);
    }

    public interface OnItemLongListener {
        void onItemLongClick(Comando comando, int posicion);
    }

    @NonNull
    @Override
    public ComandoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ComandoViewHolder(v, listaComandosFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull ComandoViewHolder holder, int position) {
        holder.bind(comandos.get(position), itemClickListener, itemLongClickListener);
    }

    @Override
    public int getItemCount() {
        return this.comandos.size();
    }

}
