package com.android.jarvis.gui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.jarvis.gui.fragments.ListaGrabacionesFragment;
import com.android.jarvis.modelos.Grabacion;

import java.util.List;

public class GrabacionAdapter extends RecyclerView.Adapter<GrabacionViewHolder> {

    private List<Grabacion> grabaciones;
    private int layout;
    private OnItemClickListener itemClickListener;
    private OnItemLongListener itemLongClickListener;
    private ListaGrabacionesFragment listaGrabacionesFragment;

    public GrabacionAdapter(ListaGrabacionesFragment listaGrabacionesFragment,
                            List<Grabacion> grabaciones, int layout,
                            OnItemClickListener itemClickListener,
                            OnItemLongListener itemLongClickListener) {
        this.listaGrabacionesFragment = listaGrabacionesFragment;
        this.grabaciones = grabaciones;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
        this.itemLongClickListener = itemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Grabacion grabacion, int posicion);
    }

    public interface OnItemLongListener {
        void onItemLongClick(Grabacion grabacion, int posicion);
    }

    @NonNull
    @Override
    public GrabacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new GrabacionViewHolder(v, listaGrabacionesFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull GrabacionViewHolder holder, int position) {
        holder.bind(grabaciones.get(position), itemClickListener, itemLongClickListener);
    }

    @Override
    public int getItemCount() {
        return this.grabaciones.size();
    }

}
