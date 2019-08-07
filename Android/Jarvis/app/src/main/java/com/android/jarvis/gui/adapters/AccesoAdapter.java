package com.android.jarvis.gui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.jarvis.modelos.Acceso;

import java.util.List;

public class AccesoAdapter extends RecyclerView.Adapter<AccesoViewHolder> {

    private List<Acceso> accesos;
    private int layout;

    public AccesoAdapter(List<Acceso> accesos, int layout) {
        this.accesos = accesos;
        this.layout = layout;
    }

    @NonNull
    @Override
    public AccesoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new AccesoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AccesoViewHolder holder, int position) {
        holder.bind(accesos.get(position));
    }

    @Override
    public int getItemCount() {
        return this.accesos.size();
    }

}
