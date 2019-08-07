package com.android.jarvis.modelos;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Cuenta extends RealmObject {

    @PrimaryKey
    private String dni;
    @Required
    private String nombre;
    @Required
    private String sexo;
    @Required
    private String tipo;
    @Required
    private String foto;

    private RealmList<Acceso> accesos;

    public Cuenta() {

    }

    public Cuenta(String dni, String nombre, String sexo, String tipo, String foto) {
        this.dni = dni;
        this.nombre = nombre;
        this.sexo = sexo;
        this.tipo = tipo;
        this.foto = foto;
        this.accesos = new RealmList<>();
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public RealmList<Acceso> getAccesos() {
        return accesos;
    }

}
