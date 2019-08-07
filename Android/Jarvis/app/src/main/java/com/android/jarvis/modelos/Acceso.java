package com.android.jarvis.modelos;

import com.android.jarvis.app.MyApplication;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Acceso extends RealmObject {

    @PrimaryKey
    private int id;
    @Required
    private String fecha;
    @Required
    private String hora;
    @Required
    private String cuentaDni;

    public Acceso() {

    }

    public Acceso(String fecha, String hora, String cuentaDni) {
        this.id = MyApplication.AccesoID.incrementAndGet();
        this.fecha = fecha;
        this.hora = hora;
        this.cuentaDni = cuentaDni;
    }

    public int getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getCuentaDni() {
        return cuentaDni;
    }

}
