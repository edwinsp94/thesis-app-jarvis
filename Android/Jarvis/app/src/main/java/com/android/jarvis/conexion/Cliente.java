package com.android.jarvis.conexion;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.jarvis.gui.activities.MainActivity;
import com.android.jarvis.gui.fragments.ListaConexionesFragment;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

public class Cliente extends Thread {

    private Socket socket;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private boolean conectado;
    private final String usuario;
    private String otroUsuario;
    private final ListaConexionesFragment guiCliente;
    public Context context;

    public Cliente(String usuario, ListaConexionesFragment gui) {
        this.conectado = false;
        this.usuario = usuario;
        this.otroUsuario = null;
        this.guiCliente = gui;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getOtroUsuario() {
        return otroUsuario;
    }

    public void setOtroUsuario(String otroUsuario) {
        this.otroUsuario = otroUsuario;
    }

    public boolean inicializarConexion(String ip) {
        conectarAlServidor(ip);
        if (conectado) {
            obtenerFlujos();
            if (conectado) {
                enviarInformacion("ingreso@"+usuario);
            }
        }
        return conectado;
    }

    private void conectarAlServidor(String ip) {
        try {
            try {
                socket = new Socket(ip, 12354);
                conectado = true;
            } catch (ConnectException ex) {
                Log.e("ERROR", "Ningun servidor tiene esa IP");
            }
        } catch (IOException ex) {
            Log.e("ERROR", "El servidor esta apagado");
        }
    }

    private void obtenerFlujos() {
        try {
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            conectado = false;
            Log.e("ERROR", "No se puede obtener los flujos");
        }
    }

    public void enviarInformacion(String mensaje) {
        try {
            salida.writeUTF(mensaje);
        } catch (IOException ex) {
            Log.e("ERROR", "No se pudo enviar la informacion al servidor!");
        }
    }

    @Override
    public void run() {
        while (conectado) {
            try {
                String mensaje = entrada.readUTF();
                if (mensaje.indexOf("buenIngreso") == 0) {
                    mostarMensaje("Buen ingreso");
                } else if (mensaje.indexOf("malIngreso") == 0) {
                    cerrarConexion();
                    mostarMensaje("El usuario, ya existe");
                    MainActivity.cliente = null;
                } else if (mensaje.indexOf("apagar") == 0) {
                    cerrarConexion();
                } else if (mensaje.indexOf("visibles") == 0) {
                    String[] usuarios = mensaje.split("@");
                    String[] visibles = null;
                    if (usuarios.length > 1) {
                        visibles = new String[usuarios.length-1];
                        System.arraycopy(usuarios, 1, visibles, 0, visibles.length);
                    }
                    guiCliente.cargarListaUsuarios(visibles);
                } else if (mensaje.indexOf("aceptado") == 0) {
                    otroUsuario = mensaje.split("@")[1];
                    mostarMensaje(otroUsuario+" acepto tu solicitud");
                    guiCliente.aceptado(otroUsuario);
                } else if (mensaje.indexOf("rechazado") == 0) {
                    otroUsuario = null;
                    mostarMensaje(mensaje.split("@")[1]+" rechazo tu solicitud");
                    guiCliente.rechazado();
                } else if (mensaje.indexOf("audioRuido") == 0) {
                    audioRuido(mensaje.split("@")[1]);
                } else if (mensaje.indexOf("saliendo") == 0) {
                    otroUsuario = null;
                    mostarMensaje(mensaje.split("@")[1]+" se salio");
                    guiCliente.salio();
                }
            } catch (IOException ex) {
                Log.e("ERROR", "Cliente, no se pudo leer");
            }
        }
    }

    private void cerrarConexion() {
        try {
            conectado = false;
            salida.close();
            entrada.close();
            socket.close();
        } catch (IOException ex) {
            mostarMensaje("No se pudo apagar el cliente");
        }
    }

    private void audioRuido(String urlAudio) {
        if (urlAudio.equals("error")) {
            mostarMensaje("No se pudo grabar el ruido.wav");
        } else {
            if (crearArchivo(urlAudio) == null) {
                mostarMensaje("No se pudo guardar el ruido.wav");
            }
        }
    }

    private File crearArchivo(String urlAudio) {
        File archivo = null;
        try {
            URL url = new URL(urlAudio);
            try {
                URLConnection connection = url.openConnection();
                if (((HttpURLConnection) connection).getResponseCode() == 200) {
                    InputStream in = connection.getInputStream();
                    archivo = new File(Objects.requireNonNull(context).getFilesDir().getPath()+"/ruido.wav");
                    FileOutputStream fos = new FileOutputStream(archivo);
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    in.close();
                    fos.flush();
                    fos.close();
                } else {
                    Log.i("ERROR", "No se encuentra el audio del ruido");
                }
            } catch (IOException ex) {
                Log.i("ERROR", "No se pude abrir la conexion");
            }
        } catch (MalformedURLException ex) {
            Log.i("ERROR", "No se pudo construir la URL");
        }
        return archivo;
    }

    private void mostarMensaje(final String msg) {
        Objects.requireNonNull(guiCliente.getActivity()).runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(guiCliente.getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
