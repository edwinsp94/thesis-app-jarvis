package com.android.jarvis.gui.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.jarvis.R;
import com.android.jarvis.gui.activities.MainActivity;
import com.android.jarvis.gui.adapters.ComandoAdapter;
import com.android.jarvis.modelos.Comando;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListaComandosFragment extends Fragment implements View.OnClickListener {

    private List<Comando> comandos;
    private List<Comando> comandos_seleccionados;
    private ComandoAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton fabAgregar;
    private MenuItem menuItemEliminar;
    
    public CuentaFragment cuentaFragment;
    public String comandoSeleccionado;
    public boolean estaModoAccion;

    public ListaComandosFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_comandos, container, false);
        fabAgregar = view.findViewById(R.id.FragmentListaComando_fab_agregar);
        estaModoAccion = false;
        cargarDirectoriosComandos();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_lista_comandos);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new ComandoAdapter(this, comandos, R.layout.recycler_view_comando, new ComandoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Comando comando, int posicion) {
                if (estaModoAccion) {
                    if (comando.isSeleccionado()) {
                        comando.setSeleccionado(false);
                        comandos_seleccionados.remove(comando);
                        if (comandos_seleccionados.isEmpty()) {
                            estaModoAccion = false;
                            menuItemEliminar.setVisible(false);
                        }
                    } else {
                        comando.setSeleccionado(true);
                        comandos_seleccionados.add(comando);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    comandoSeleccionado = comando.getNombre();
                    ListaGrabacionesFragment listaGrabacionesFragment = new ListaGrabacionesFragment();
                    listaGrabacionesFragment.URI = cuentaFragment.cuenta.getDni()+"/"+comandoSeleccionado;
                    insertarFragmento(listaGrabacionesFragment,"Comando - "+comando.getNombre());
                }
            }
        }, new ComandoAdapter.OnItemLongListener() {
            @Override
            public void onItemLongClick(Comando comando, int posicion) {
                if (!estaModoAccion) {
                    estaModoAccion = true;
                    menuItemEliminar.setVisible(true);
                    comando.setSeleccionado(true);
                    comandos_seleccionados.clear();
                    comandos_seleccionados.add(comando);
                    adapter.notifyDataSetChanged();
                }
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
        fabAgregar.setOnClickListener(this);
        return view;
    }

    private void cargarDirectoriosComandos() {
        comandos = new ArrayList<>();
        File directorio = new File(Objects.requireNonNull(getContext()).getFilesDir().getPath()+"/Audios/Comandos/"+cuentaFragment.cuenta.getDni());
        String[] lista = directorio.list();
        if (lista != null) {
            for (String dir : lista) {
                comandos.add(new Comando(dir));
            }
        }
        comandos_seleccionados = new ArrayList<>();
    }

    @Override
    public void onClick(View view) {
        estaModoAccion = false;
        menuItemEliminar.setVisible(false);
        adapter.notifyDataSetChanged();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Agregar");
        builder.setMessage("Ingrese el nombre del comando:");
        View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.dialog_crear_comando, null);
        builder.setView(viewInflated);
        final EditText editText = viewInflated.findViewById(R.id.DialogCrearComando_editText_nombre);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String nombreComando = editText.getText().toString().trim();
                if (!TextUtils.isEmpty(nombreComando)) {
                    if (!existeComando(nombreComando)) {
                        if (crearDirectorios(Objects.requireNonNull(getContext()).getFilesDir().getPath(), nombreComando)) {
                            int posicion = comandos.size();
                            comandos.add(posicion, new Comando(nombreComando));
                            adapter.notifyItemInserted(posicion);
                            layoutManager.scrollToPosition(posicion);
                        } else {
                            Toast.makeText(getContext(), "No se pudo crear directorio", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Ya existe el comando", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Por favor, ingrese el nombre", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    private boolean crearDirectorios(String ruta, String nombreComando) {
        boolean creoAudioComando = true;
        boolean creoMFCCComando = true;
        File file = new File(ruta+"/Audios/Comandos/"+cuentaFragment.cuenta.getDni()+"/"+nombreComando);
        if (!file.exists()) {
            creoAudioComando = file.mkdir();
        }
        file = new File(ruta+"/MFCC/Comandos/"+cuentaFragment.cuenta.getDni()+"/"+nombreComando);
        if (!file.exists()) {
            creoMFCCComando = file.mkdir();
        }
        return (creoAudioComando && creoMFCCComando);
    }


    private boolean existeComando(String nombreComando) {
        boolean existe  = false;
        for (int i = 0; !existe && i < comandos.size(); i++) {
            if (comandos.get(i).getNombre().toLowerCase().equals(nombreComando.toLowerCase())) {
                existe = true;
            }
        }
        return  existe;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_lista_menu_eliminar, menu);
        super.onCreateOptionsMenu(menu, inflater);
        menuItemEliminar = menu.getItem(0);
        menuItemEliminar.setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_accion_eliminar:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Eliminar");
                if (comandos_seleccionados.size() > 1) {
                    builder.setMessage("Estos directorios se eliminarán.");
                } else {
                    builder.setMessage("Este directorio se eliminará.");
                }
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int p = 0; p < comandos_seleccionados.size(); p++) {
                            String ruta = Objects.requireNonNull(getContext()).getFilesDir().getPath();
                            String nombreComando = comandos_seleccionados.get(p).getNombre();
                            if (borrarDirectorio(ruta+"/Audios/Comandos", nombreComando) &&
                                borrarDirectorio(ruta+"/MFCC/Comandos", nombreComando)) {
                                comandos.remove(comandos_seleccionados.get(p));
                            }
                        }
                        estaModoAccion = false;
                        menuItemEliminar.setVisible(false);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.create().show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean borrarDirectorio(String ruta, String nombreDirectorio) {
        File directorio = new File(ruta+"/"+cuentaFragment.cuenta.getDni()+"/"+nombreDirectorio);
        File[] archivos = directorio.listFiles();
        boolean borro = true;
        if (archivos != null) {
            for (int i = 0; borro && i < archivos.length; i++) {
                borro = archivos[i].delete();
            }
        }
        if (borro) {
            borro = directorio.delete();
        }
        return borro;
    }

    public void clickCheckBox(boolean seleccionado, int posicion) {
        Comando comando = comandos.get(posicion);
        comando.setSeleccionado(seleccionado);
        if (seleccionado) {
            comandos_seleccionados.add(comando);
        } else {
            comandos_seleccionados.remove(comando);
            if (comandos_seleccionados.isEmpty()) {
                estaModoAccion = false;
                menuItemEliminar.setVisible(false);
            }
        }
        adapter.notifyItemChanged(posicion);
    }

    private void insertarFragmento(Fragment fragmentNew, String titleFragment){
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.setTitle(titleFragment);
            MainActivity.FRAGMENT_ACTUAL = "ListaGrabacionesFragment";
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
