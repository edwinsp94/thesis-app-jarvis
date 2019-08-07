package com.android.jarvis.gui.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.jarvis.R;
import com.android.jarvis.app.MiProgressDialog;
import com.android.jarvis.gui.activities.MainActivity;
import com.android.jarvis.gui.adapters.GrabacionAdapter;
import com.android.jarvis.modelos.Grabacion;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListaGrabacionesFragment extends Fragment implements View.OnClickListener {

    private List<Grabacion> grabaciones;
    private List<Grabacion> grabaciones_seleccionadas;
    private GrabacionAdapter adapter;
    private FloatingActionButton fabAgregar;
    private MenuItem menuItemEliminar;

    public boolean estaModoAccion;
    public String URI;

    private MiProgressDialog miProgressDialog;

    public ListaGrabacionesFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_grabaciones, container, false);
        fabAgregar = view.findViewById(R.id.FragmentListaGrabacion_fab_agregar);
        estaModoAccion = false;
        cargarGrabaciones();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_lista_grabaciones);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new GrabacionAdapter(this, grabaciones, R.layout.recycler_view_grabacion, new GrabacionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Grabacion grabacion, int posicion) {
                if (estaModoAccion) {
                    if (grabacion.isSeleccionado()) {
                        grabacion.setSeleccionado(false);
                        grabaciones_seleccionadas.remove(grabacion);
                        if (grabaciones_seleccionadas.isEmpty()) {
                            estaModoAccion = false;
                            menuItemEliminar.setVisible(false);
                        }
                    } else {
                        grabacion.setSeleccionado(true);
                        grabaciones_seleccionadas.add(grabacion);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    GrabarFragment grabarFragment = new GrabarFragment();
                    grabarFragment.grabacion = grabacion;
                    grabarFragment.URI = URI;
                    insertarFragmento(grabarFragment);
                }
            }
        }, new GrabacionAdapter.OnItemLongListener() {
            @Override
            public void onItemLongClick(Grabacion grabacion, int posicion) {
                if (!estaModoAccion) {
                    estaModoAccion = true;
                    menuItemEliminar.setVisible(true);
                    grabacion.setSeleccionado(true);
                    grabaciones_seleccionadas.clear();
                    grabaciones_seleccionadas.add(grabacion);
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

    @SuppressLint("StaticFieldLeak")
    private void cargarGrabaciones() {
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("WrongThread")
            @Override
            protected Void doInBackground(Void... voids) {
                String ruta = Objects.requireNonNull(getContext()).getFilesDir().getPath()+"/Audios";
                if (URI.contains("/")) {
                    ruta += "/Comandos/"+URI;
                } else {
                    ruta += "/Patrones/"+URI;
                }
                File[] archivos = new File(ruta).listFiles();
                if (archivos.length > 0) {
                    MediaPlayer mp; int duracionInt; String duracion; String[] fecha;
                    for (File archivo : archivos) {
                        mp = MediaPlayer.create(getContext(), Uri.parse(String.valueOf(Uri.fromFile(archivo))));
                        duracionInt = mp.getDuration() / 1000;
                        duracion = "00:";
                        if (duracionInt < 10) {
                            duracion += "0"+duracionInt;
                        } else {
                            duracion += duracionInt;
                        }
                        fecha = archivo.getName().split("_");
                        grabaciones.add(new Grabacion(archivo.getName(), fecha[0] + "/" + fecha[1] + "/" + fecha[2], duracion));
                    }
                }
                return null;
            }

            @Override
            protected void onPreExecute() {
                miProgressDialog = new MiProgressDialog(getContext(), "Cargando Datos", "Por favor, espere un momento...");
                miProgressDialog.iniciar();
                grabaciones = new ArrayList<>();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                miProgressDialog.detener();
                grabaciones_seleccionadas = new ArrayList<>();
                adapter.notifyDataSetChanged();
                FragmentActivity activity = Objects.requireNonNull(getActivity());
                activity.setTitle(activity.getTitle() + " - " + grabaciones.size() + " archivo(s)");
            }
        }.execute();
    }

    @Override
    public void onClick(View view) {
        GrabarFragment grabarFragment = new GrabarFragment();
        grabarFragment.grabacion = null;
        grabarFragment.URI = URI;
        insertarFragmento(grabarFragment);
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
        if (item.getItemId() == R.id.menu_accion_eliminar) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Eliminar");
            if (grabaciones_seleccionadas.size() > 1) {
                builder.setMessage("Estas grabaciones se eliminarán.");
            } else {
                builder.setMessage("Esta grabación se eliminará.");
            }
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    for (int p = 0; p < grabaciones_seleccionadas.size(); p++) {
                        if (borrarGrabacion(grabaciones_seleccionadas.get(p).getNombre().split(".wav")[0])) {
                            grabaciones.remove(grabaciones_seleccionadas.get(p));
                        }
                    }
                    estaModoAccion = false;
                    menuItemEliminar.setVisible(false);
                    adapter.notifyDataSetChanged();
                    String titleFragment = Objects.requireNonNull(getActivity()).getTitle().toString().split("-")[0];
                    getActivity().setTitle(titleFragment + "- " + grabaciones.size() + " archivo(s)");
                }
            });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.create().show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean borrarGrabacion(String nombreArchivo) {
        String ruta = Objects.requireNonNull(getContext()).getFilesDir().getPath();
        String ruta1 = "", ruta2 = "";
        if (URI.contains("/")) {
            ruta1 += ruta+"/Audios/Comandos/"+URI+"/"+nombreArchivo+".wav";
            ruta2 += ruta+"/MFCC/Comandos/"+URI+"/"+nombreArchivo+".mfcc";
        } else {
            ruta1 += ruta+"/Audios/Patrones/"+URI+"/"+nombreArchivo+".wav";
            ruta2 += ruta+"/MFCC/Patrones/"+URI+"/"+nombreArchivo+".mfcc";
        }
        File file1 = new File(ruta1);
        File file2 = new File(ruta2);
        return (file1.delete() && file2.delete());
    }

    public void clickCheckBox(boolean seleccionado, int posicion) {
        Grabacion grabacion = grabaciones.get(posicion);
        grabacion.setSeleccionado(seleccionado);
        if (seleccionado) {
            grabaciones_seleccionadas.add(grabacion);
        } else {
            grabaciones_seleccionadas.remove(grabacion);
            if (grabaciones_seleccionadas.isEmpty()) {
                estaModoAccion = false;
                menuItemEliminar.setVisible(false);
            }
        }
        adapter.notifyItemChanged(posicion);
    }

    private void insertarFragmento(Fragment newFragment){
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.setTitle("Grabadora de Voz");
            MainActivity.FRAGMENT_ACTUAL = "GrabarFragment";
            FragmentManager fm = activity.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment fragmentOld = fm.findFragmentById(R.id.mainFrame);
            if (fragmentOld != null) {
                ft.remove(fragmentOld);
            }
            ft.add(R.id.mainFrame, newFragment);
            ft.commit();
        }
    }

}
