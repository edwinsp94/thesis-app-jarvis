package com.android.jarvis.gui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.jarvis.R;
import com.android.jarvis.conexion.Cliente;
import com.android.jarvis.conexion.Servidor;
import com.android.jarvis.gui.activities.MainActivity;
import com.android.jarvis.gui.adapters.ConexionAdapter;
import com.android.jarvis.modelos.Conexion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListaConexionesFragment extends Fragment {

    private List<Conexion> conexiones;
    private ConexionAdapter adapter;
    private TextView textView;
    private MenuItem menuItemActivar;
    private MenuItem menuItemDesactivar;
    private MenuItem menuItemActualizar;
    private View view;
    private SharedPreferences preferences;

    public ListaConexionesFragment() {
        this.view = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_lista_conexiones, container, false);
            textView = view.findViewById(R.id.FragmentListaConexiones_textView);
            conexiones = new ArrayList<>();
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView_lista_conexiones);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            adapter = new ConexionAdapter(conexiones, R.layout.recycler_view_conexion, new ConexionAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Conexion conexion, int posicion) {
                    if (conexion.getEstado().equals("Conectado")) {
                        MainActivity.cliente.enviarInformacion("salir@"+MainActivity.cliente.getOtroUsuario()+"@"+MainActivity.cliente.getUsuario());
                        MainActivity.cliente.enviarInformacion("activo@" + true);
                        MainActivity.cliente.setOtroUsuario(null);
                        menuItemActivar.setEnabled(false);
                        menuItemDesactivar.setEnabled(true);
                        menuItemActualizar.setEnabled(true);
                        conexion.setIcono(R.drawable.ic_phonelink_off);
                        conexion.setEstado("Desconectado");
                        adapter.notifyItemChanged(posicion);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("ruido", "false");
                        editor.apply();
                    } else {
                        int hayConeccion = -1;
                        for (int i = 0; hayConeccion == -1 && i < conexiones.size(); i++) {
                            if (!conexiones.get(i).getNombre().equals(conexion.getNombre()) &&
                                conexiones.get(i).getEstado().equals("Conectado")) {
                                hayConeccion = i;
                            }
                        }
                        if (hayConeccion != -1) {
                            Toast.makeText(getContext(), "Primero desconectese de "+conexiones.get(hayConeccion).getNombre(), Toast.LENGTH_SHORT).show();
                        } else {
                            MainActivity.cliente.enviarInformacion("online@"+conexion.getNombre());
                        }
                    }
                }
            });
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            preferences = Objects.requireNonNull(getContext()).getSharedPreferences("PreferencesJarvis", Context.MODE_PRIVATE);
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_lista_conexion_menu, menu);
        menuItemActivar = menu.getItem(0);
        menuItemDesactivar = menu.getItem(1);
        menuItemActualizar = menu.getItem(2);
        if (MainActivity.servidor == null && MainActivity.cliente == null) {
            menuItemActivar.setEnabled(true);
            menuItemDesactivar.setEnabled(false);
            menuItemActualizar.setEnabled(false);
        } else {
            if (MainActivity.cliente.getOtroUsuario() == null) {
                menuItemActivar.setEnabled(false);
                menuItemDesactivar.setEnabled(true);
                menuItemActualizar.setEnabled(true);
            } else {
                menuItemActivar.setEnabled(false);
                menuItemDesactivar.setEnabled(false);
                menuItemActualizar.setEnabled(false);
            }
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.conexion_menu_accion_activar:
                ConnectivityManager connManager = (ConnectivityManager) Objects.requireNonNull(getContext()).getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connManager != null) {
                    NetworkInfo wifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                    if (wifi.isConnected()) {
                        if (MainActivity.servidor == null) {
                            String ipNombrePC = Servidor.getIpNombrePC();
                            if (ipNombrePC != null) {
                                String[] host = ipNombrePC.split("/");
                                MainActivity.servidor = new Servidor();
                                try {
                                    MainActivity.servidor.encender(10);
                                    MainActivity.cliente = new Cliente(host[0], this);
                                    if (MainActivity.cliente.inicializarConexion(host[1])) {
                                        MainActivity.cliente.start();
                                        menuItemActivar.setEnabled(false);
                                        menuItemDesactivar.setEnabled(true);
                                        menuItemActualizar.setEnabled(true);
                                        textView.setVisibility(View.GONE);
                                        Toast.makeText(getContext(), "Cliente: "+host[0]+"/"+host[1], Toast.LENGTH_LONG).show();
                                    } else {
                                        MainActivity.cliente = null;
                                        Toast.makeText(getContext(), "No se pudo conectar al servidor", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (IOException e) {
                                    Toast.makeText(getContext(), "No se pudo crear el servidor", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getContext(), "No se pudo obtener la IP", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(getContext(), "Encienda su Wi-Fi y conectese a una red", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            case R.id.conexion_menu_accion_desactivar:
                if (MainActivity.cliente != null) {
                    MainActivity.cliente.enviarInformacion("apagar");
                    MainActivity.cliente = null;
                    if (MainActivity.servidor != null) {
                        try {
                            MainActivity.servidor.apagar();
                        } catch (IOException e) {
                            Toast.makeText(getContext(), "No se pudo apagar correctamente el servidor", Toast.LENGTH_SHORT).show();
                        }
                        MainActivity.servidor = null;
                        conexiones.clear();
                        adapter.notifyDataSetChanged();
                        menuItemActivar.setEnabled(true);
                        menuItemDesactivar.setEnabled(false);
                        menuItemActualizar.setEnabled(false);
                        textView.setVisibility(View.VISIBLE);
                        Toast.makeText(getContext(), "Desactivado", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            case R.id.conexion_menu_accion_actualizar:
                conexiones.clear();
                MainActivity.cliente.enviarInformacion("getVisibles");
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Actualizar", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void cargarListaUsuarios(String[] clientes) {
        if (clientes != null) {
            for (String cliente : clientes) {
                conexiones.add(new Conexion(cliente));
            }
        }
    }

    public void aceptado(String otroUsuario) {
        MainActivity.cliente.enviarInformacion("activo@"+false);
        int posicion = -1;
        for (int i = 0; posicion == -1 && i < conexiones.size(); i++) {
            if (conexiones.get(i).getNombre().equals(otroUsuario)) {
                posicion = i;
            }
        }
        if (posicion != -1) {
            Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                public void run() {
                    menuItemActivar.setEnabled(false);
                    menuItemDesactivar.setEnabled(false);
                    menuItemActualizar.setEnabled(false);
                }
            });
            conexiones.get(posicion).setIcono(R.drawable.ic_phonelink_on);
            conexiones.get(posicion).setEstado("Conectado");
            adapter.notifyItemChanged(posicion);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("ruido", "true");
            editor.apply();
        }
    }

    public void rechazado() {
        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
            public void run() {
                menuItemActivar.setEnabled(false);
                menuItemDesactivar.setEnabled(true);
                menuItemActualizar.setEnabled(true);
            }
        });
    }

    public void salio() {
        MainActivity.cliente.enviarInformacion("activo@"+true);
        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
            public void run() {
                menuItemActivar.setEnabled(false);
                menuItemDesactivar.setEnabled(true);
                menuItemActualizar.setEnabled(true);
            }
        });
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ruido", "false");
        editor.apply();
    }

}
