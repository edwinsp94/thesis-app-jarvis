package com.android.jarvis.gui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.jarvis.modelos.Cuenta;

import java.util.List;

public class CuentaAdapter extends RecyclerView.Adapter<CuentaViewHolder> {

    private List<Cuenta> cuentas;
    private int layout;
    private OnItemClickListener itemClickListener;

    public CuentaAdapter(List<Cuenta> cuentas, int layout, OnItemClickListener itemClickListener) {
        this.cuentas = cuentas;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Cuenta cuenta, int posicion);
    }

    @NonNull
    @Override
    public CuentaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new CuentaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CuentaViewHolder holder, int position) {
        holder.bind(cuentas.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return this.cuentas.size();
    }

}
