package com.android.jarvis.gui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.jarvis.R;
import com.android.jarvis.gui.activities.MainActivity;
import com.android.jarvis.gui.adapters.AccesoAdapter;
import com.android.jarvis.modelos.Acceso;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

public class ListaAccesosFragment extends Fragment {

    private List<Acceso> accesos;
    private List<Acceso> accesos_copia;
    private AccesoAdapter adapter;
    private boolean isCollapse;

    public ListaAccesosFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_accesos, container, false);
        RealmResults<Acceso> accesos_results = MainActivity.realm.where(Acceso.class).findAll();
        accesos = new ArrayList<>();
        accesos.addAll(accesos_results);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_lista_accesos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new AccesoAdapter(accesos, R.layout.recycler_view_acceso);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        accesos_copia = null;
        isCollapse = false;
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_lista_menu_buscar, menu);
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuItem = menu.findItem(R.id.menu_accion_buscar);
        SearchView searchView = new SearchView(((MainActivity)getActivity()).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(menuItem, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(menuItem, searchView);
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()) {
                    accesos.clear();
                    accesos.addAll(filtrarAccesos(newText));
                    adapter.notifyDataSetChanged();
                } else {
                    if (!isCollapse) {
                        accesos.clear();
                        adapter.notifyDataSetChanged();
                    }
                }
                return true;
            }
        });
        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                accesos.clear();
                adapter.notifyDataSetChanged();
                isCollapse = false;
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                accesos.clear();
                accesos.addAll(accesos_copia);
                adapter.notifyDataSetChanged();
                isCollapse = true;
                return true;
            }
        });
    }

    private List<Acceso> filtrarAccesos(String patron) {
        List<Acceso> accesos_filtrados = new ArrayList<>();
        boolean esNumero = patron.matches("[0-9]+");
        for (int i = 0; i < accesos_copia.size(); i++) {
            Acceso acceso = accesos_copia.get(i);
            if (acceso != null) {
                if (esNumero) {
                    if (acceso.getCuentaDni().indexOf(patron) == 0) {
                        accesos_filtrados.add(acceso);
                    }
                } else {
                    if (acceso.getFecha().contains(patron)) {
                        accesos_filtrados.add(acceso);
                    }
                }
            }
        }
        return accesos_filtrados;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_accion_buscar:
                accesos_copia = new ArrayList<>();
                accesos_copia.addAll(accesos);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
