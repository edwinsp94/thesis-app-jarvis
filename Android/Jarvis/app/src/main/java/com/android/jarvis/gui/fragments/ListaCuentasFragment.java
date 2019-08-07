package com.android.jarvis.gui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
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
import com.android.jarvis.gui.adapters.CuentaAdapter;
import com.android.jarvis.modelos.Cuenta;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmResults;

public class ListaCuentasFragment extends Fragment {

    private List<Cuenta> cuentas;
    private List<Cuenta> cuentas_copia;
    private CuentaAdapter adapter;
    private FloatingActionButton fabAgregar;
    private boolean isCollapse;

    public CuentaFragment cuentaFragment;

    public ListaCuentasFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_cuentas, container, false);
        fabAgregar = view.findViewById(R.id.FragmentListaCuentas_fab_agregar);
        RealmResults<Cuenta> cuentas_results = MainActivity.realm.where(Cuenta.class).findAll();
        cuentas = new ArrayList<>();
        cuentas.addAll(cuentas_results);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_lista_cuentas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new CuentaAdapter(cuentas, R.layout.recycler_view_cuenta, new CuentaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Cuenta cuenta, int posicion) {
                onItemClickCuenta(cuenta);
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    fabAgregar.hide();
                } else if (dy < 0) {
                    fabAgregar.show();
                }
            }
        });
        recyclerView.setAdapter(adapter);
        fabAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickFabAgregar();
            }
        });
        cuentas_copia = null;
        cuentaFragment = null;
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
                    cuentas.clear();
                    cuentas.addAll(filtrarCuentas(newText));
                    adapter.notifyDataSetChanged();
                    fabAgregar.hide();
                } else {
                    if (!isCollapse) {
                        cuentas.clear();
                        adapter.notifyDataSetChanged();
                        fabAgregar.hide();
                    }
                }
                return true;
            }
        });
        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                cuentas.clear();
                adapter.notifyDataSetChanged();
                fabAgregar.hide();
                isCollapse = false;
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                cuentas.clear();
                cuentas.addAll(cuentas_copia);
                adapter.notifyDataSetChanged();
                fabAgregar.show();
                isCollapse = true;
                return true;
            }
        });
    }

    private List<Cuenta> filtrarCuentas(String patron) {
        List<Cuenta> cuentas_filtradas = new ArrayList<>();
        boolean esNumero = patron.matches("[0-9]+");
        for (int i = 0; i < cuentas_copia.size(); i++) {
            Cuenta cuenta = cuentas_copia.get(i);
            if (cuenta != null) {
                if (esNumero) {
                    if (cuenta.getDni().indexOf(patron) == 0) {
                        cuentas_filtradas.add(cuenta);
                    }
                } else {
                    if (cuenta.getNombre().toLowerCase().contains(patron.toLowerCase())) {
                        cuentas_filtradas.add(cuenta);
                    }
                }
            }
        }
        return cuentas_filtradas;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_accion_buscar:
                cuentas_copia = new RealmList<>();
                cuentas_copia.addAll(cuentas);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onItemClickCuenta(Cuenta cuenta) {
        Bundle bundle = new Bundle();
        bundle.putString("cuenta_dni", cuenta.getDni());
        cuentaFragment = new CuentaFragment();
        cuentaFragment.setArguments(bundle);
        insertarFragmento(cuentaFragment, "Editar Cuenta");
    }

    public void onClickFabAgregar() {
        cuentaFragment = new CuentaFragment();
        insertarFragmento(cuentaFragment, "Agregar Cuenta");
    }

    private void insertarFragmento(Fragment fragmentNew, String titleFragment) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.setTitle(titleFragment);
            MainActivity.FRAGMENT_ACTUAL = "CuentaFragment";
            FragmentManager fm = activity.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment fragmentOld = fm.findFragmentById(R.id.mainFrame);
            if (fragmentOld != null) {
                ft.remove(fragmentOld);
            }
            ft.add(R.id.mainFrame, fragmentNew);
            ft.commit();
        }
    }

}
