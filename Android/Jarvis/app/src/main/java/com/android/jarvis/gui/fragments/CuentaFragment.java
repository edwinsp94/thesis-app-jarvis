package com.android.jarvis.gui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.jarvis.R;
import com.android.jarvis.gui.activities.MainActivity;
import com.android.jarvis.modelos.Cuenta;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class CuentaFragment extends Fragment {

    private EditText editTextDni;
    private EditText editTextNombre;
    private Spinner spinnerSexo;
    private Spinner spinnerTipo;
    private View view;

    public ListaComandosFragment listaComandosFragment;
    public Cuenta cuenta;
    public String titleFragment;
    public String menuAccion;

    public CuentaFragment() {
        this.view = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view ==  null) {
            view = inflater.inflate(R.layout.fragment_cuenta, container, false);
            editTextDni = view.findViewById(R.id.FragmentCuenta_editText_dni);
            editTextNombre = view.findViewById(R.id.FragmentCuenta_editText_nombre);
            spinnerSexo = view.findViewById(R.id.FragmentCuenta_spinner_sexos);
            spinnerTipo = view.findViewById(R.id.FragmentCuenta_spinner_tipos_usuarios);
            Bundle bundle = getArguments();
            if (bundle != null) {
                titleFragment = "Editar Cuenta";
                cuenta = MainActivity.realm.where(Cuenta.class).equalTo("dni", bundle.getString("cuenta_dni")).findFirst();
                if (cuenta != null) {
                    ImageView imageViewFoto = view.findViewById(R.id.FragmentCuenta_imageView_foto);
                    Picasso.get().load(new File(cuenta.getFoto())).fit().into(imageViewFoto);
                    editTextDni.setText(cuenta.getDni());
                    editTextDni.setEnabled(false);
                    editTextNombre.setText(cuenta.getNombre());
                    if (cuenta.getSexo().equals("Femenino")) {
                        spinnerSexo.setSelection(0);
                    } else {
                        spinnerSexo.setSelection(1);
                    }
                    switch (cuenta.getTipo()) {
                        case "Administrador":
                            spinnerTipo.setSelection(0);
                            break;
                        case "Normal":
                            spinnerTipo.setSelection(1);
                            break;
                        default:
                            spinnerTipo.setSelection(2);
                            break;
                    }
                    SharedPreferences preferences = Objects.requireNonNull(getContext()).getSharedPreferences("PreferencesJarvis", Context.MODE_PRIVATE);
                    if (preferences.getString("admin_dni", "").equals(cuenta.getDni())) {
                        spinnerTipo.setEnabled(false);
                    }
                }
            } else {
                titleFragment = "Agregar Cuenta";
                cuenta = null;
            }
        }
        menuAccion = "";
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_cuenta_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cuenta_menu_accion_guardar:
                menuAccion = "guardar";
                String error = verificarDatos();
                if (error.equals("")) {
                    if (cuenta == null) {
                        String dni = editTextDni.getText().toString().trim();
                        Cuenta c = MainActivity.realm.where(Cuenta.class).equalTo("dni", dni).findFirst();
                        if (c == null) {
                            if (crearDirectorios(Objects.requireNonNull(getContext()).getFilesDir().getPath(), dni)) {
                                String nombre = editTextNombre.getText().toString().trim();
                                String sexo = spinnerSexo.getSelectedItem().toString();
                                String tipo = spinnerTipo.getSelectedItem().toString();
                                String fecha = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault()).format(new Date());
                                String foto = Objects.requireNonNull(getContext()).getFilesDir().getPath() + "/Fotos/" + dni + "_" + fecha + ".jpg";
                                try {
                                    FileOutputStream out = new FileOutputStream(new File(foto));
                                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.foto_usuario_default);
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                                    out.flush();
                                    out.close();
                                    MainActivity.realm.beginTransaction();
                                    cuenta = new Cuenta(dni, nombre, sexo, tipo, foto);
                                    MainActivity.realm.copyToRealm(cuenta);
                                    MainActivity.realm.commitTransaction();
                                    Toast.makeText(getContext(), "Cuenta creada", Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(getContext(), "No se pudo crear los directorios", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Ya existe una cuenta con ese DNI", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        String nombre = editTextNombre.getText().toString().trim();
                        String sexo = spinnerSexo.getSelectedItem().toString();
                        String tipo = spinnerTipo.getSelectedItem().toString();
                        MainActivity.realm.beginTransaction();
                        cuenta.setNombre(nombre);
                        cuenta.setSexo(sexo);
                        cuenta.setTipo(tipo);
                        MainActivity.realm.copyToRealmOrUpdate(cuenta);
                        MainActivity.realm.commitTransaction();
                        Toast.makeText(getContext(), "Cuenta actualizada", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.cuenta_menu_accion_patron:
                if (cuenta != null) {
                    menuAccion = "patron";
                    ListaGrabacionesFragment listaGrabacionesFragment = new ListaGrabacionesFragment();
                    listaGrabacionesFragment.URI = cuenta.getDni();
                    insertarFragmento(listaGrabacionesFragment, "ListaGrabacionesFragment", "Patron de Voz");
                } else {
                    Toast.makeText(getContext(), "Primero cree la cuenta", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.cuenta_menu_accion_comandos:
                if (cuenta != null) {
                    menuAccion = "comando";
                    listaComandosFragment = new ListaComandosFragment();
                    listaComandosFragment.cuentaFragment = this;
                    insertarFragmento(listaComandosFragment, "ListaComandosFragment", "Lista de Comandos");
                } else {
                    Toast.makeText(getContext(), "Primero cree la cuenta", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String verificarDatos() {
        String error = "";
        if (titleFragment.equals("Editar Cuenta")) {
            if (TextUtils.isEmpty(editTextNombre.getText().toString().trim())) {
                error = "Por favor, ingrese el nombre";
            }
        } else {
            if (editTextDni.getText().toString().trim().length() != 8) {
                error = "DNI inválido, debe tener 8 digitos";
            } else {
                if (TextUtils.isEmpty(editTextNombre.getText().toString().trim())) {
                    error = "Por favor, ingrese el nombre";
                }
            }
        }
        return error;
    }

    private boolean crearDirectorios(String ruta, String dni) {
        boolean creoAudiosComandos = true;
        boolean creoAudiosPatrones = true;
        boolean creoMFFCComandos = true;
        boolean creoMFCCPatrones = true;

        File directorio = new File(ruta+"/Audios/Comandos/"+dni);
        if (!directorio.exists()) {
            creoAudiosComandos = directorio.mkdir();
        }
        directorio = new File(ruta+"/Audios/Patrones/"+dni);
        if (!directorio.exists()) {
            creoAudiosPatrones = directorio.mkdir();
        }
        directorio = new File(ruta+"/MFCC/Comandos/"+dni);
        if (!directorio.exists()) {
            creoMFFCComandos = directorio.mkdir();
        }
        directorio = new File(ruta+"/MFCC/Patrones/"+dni);
        if (!directorio.exists()) {
            creoMFCCPatrones = directorio.mkdir();
        }

        return  (creoAudiosComandos && creoAudiosPatrones && creoMFFCComandos && creoMFCCPatrones);
    }

    private void insertarFragmento(Fragment fragmentNew, String nameFragment, String titleFragment){
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.setTitle(titleFragment);
            MainActivity.FRAGMENT_ACTUAL = nameFragment;
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
