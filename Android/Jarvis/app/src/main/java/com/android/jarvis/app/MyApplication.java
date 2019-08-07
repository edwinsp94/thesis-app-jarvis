package com.android.jarvis.app;

import android.app.Application;
import android.os.SystemClock;

import com.android.jarvis.modelos.Acceso;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MyApplication extends Application {

    public static AtomicInteger AccesoID = new AtomicInteger();

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder().name("bdjarvis.realm").deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getDefaultInstance();
        AccesoID = getIdByTable(realm, Acceso.class);
        realm.close();
        SystemClock.sleep(1000);
    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass) {
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicInteger(Objects.requireNonNull(results.max("id")).intValue()) : new AtomicInteger();
    }

}
