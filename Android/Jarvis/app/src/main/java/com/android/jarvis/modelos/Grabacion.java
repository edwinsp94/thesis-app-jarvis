package com.android.jarvis.modelos;

public class Grabacion {

    private String nombre;
    private String fecha;
    private String duracion;
    private boolean seleccionado;

    public Grabacion(String nombre, String fecha, String duracion) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.duracion = duracion;
        this.seleccionado = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
}
