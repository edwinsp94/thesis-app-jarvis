package com.android.jarvis.modelos;

import com.android.jarvis.R;

public class Conexion {

    private int icono;
    private String nombre;
    private String estado;

    public Conexion(String nombre) {
        this.icono = R.drawable.ic_phonelink_off;
        this.nombre = nombre;
        this.estado = "Desconectado";
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
