package com.android.jarvis.gui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.jarvis.modelos.Conexion;

import java.util.List;

public class ConexionAdapter extends RecyclerView.Adapter<ConexionViewHolder> {

    private List<Conexion> conexiones;
    private int layout;
    private OnItemClickListener itemClickListener;


    public ConexionAdapter(List<Conexion> conexiones, int layout, OnItemClickListener itemClickListener) {
        this.conexiones = conexiones;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Conexion conexion, int posicion);
    }

    @NonNull
    @Override
    public ConexionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ConexionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ConexionViewHolder holder, int position) {
        holder.bind(conexiones.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return this.conexiones.size();
    }

}
