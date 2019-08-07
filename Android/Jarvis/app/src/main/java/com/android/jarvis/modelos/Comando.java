package com.android.jarvis.modelos;

public class Comando {

    private String nombre;
    private boolean seleccionado;

    public Comando(String nombre) {
        this.nombre = nombre;
        this.seleccionado = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
}
