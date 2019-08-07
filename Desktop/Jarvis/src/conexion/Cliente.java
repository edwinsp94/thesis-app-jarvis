package conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import javax.swing.JOptionPane;
import gui.GUINuevoAudio;

/**
 *
 * @author EDWIN JONATAN SANCHEZ PEDRO
 */

public class Cliente extends Thread {
    
    private Socket socket;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private boolean conectado;
    private final String usuario;
    private String otroUsuario;
    private final GUINuevoAudio guiCliente;

    public Cliente(String usuario, GUINuevoAudio gui) {
        this.conectado = false;
        this.usuario = usuario;
        this.otroUsuario = null;
        guiCliente = gui;
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
        conectarAlServidor(ip, 12354);
        if (conectado) {
            obtenerFlujos();
            enviarInformacion("ingreso@"+usuario);
        }
        return conectado;
    }
    
    private void conectarAlServidor(String ip, int puerto) {
        try {
            try {
            socket = new Socket(ip, puerto);
            conectado = true;
            } catch (ConnectException ex) {
                String msj = "Ningun servidor tiene esa IP!";
                JOptionPane.showMessageDialog(guiCliente, msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            String msj = "El servidor esta apagado!";
            JOptionPane.showMessageDialog(guiCliente, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void obtenerFlujos() {
        try {
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            String msj = "No se puede obtener los flujos!";
            JOptionPane.showMessageDialog(guiCliente, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void enviarInformacion(String mensaje) {
        try {
            salida.writeUTF(mensaje);
        } catch (IOException ex) {
            String error = "No se pudo enviar la informacion al servidor!";
            JOptionPane.showMessageDialog(guiCliente, error, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void run() {
        while (conectado) {
            try {
                String mensaje = entrada.readUTF();
                if (mensaje.indexOf("buenIngreso") == 0) {
                    guiCliente.buenIngreso();
                } else if (mensaje.indexOf("malIngreso") == 0) {
                    cerrarConexion();
                    guiCliente.malIngreso();
                } else if (mensaje.indexOf("apagar") == 0) {
                    cerrarConexion();
                } else if (mensaje.indexOf("apagoServidor") == 0) {
                    cerrarConexion();
                    guiCliente.apagoServidor();
                } else if (mensaje.indexOf("visibles") == 0) {
                    String[] usuarios = mensaje.split("@");
                    String[] visibles;
                    if (usuarios.length == 1) {
                        visibles = null;
                    } else {
                        visibles = new String[usuarios.length-1];
                        for (int i = 0; i < visibles.length; i++) {
                            visibles[i] = usuarios[i+1];
                        }
                    }
                    guiCliente.cargarListaUsuarios(visibles);
                } else if (mensaje.indexOf("solicitud") == 0) {
                    guiCliente.solicitud(mensaje.split("@")[1]);
                } else if (mensaje.indexOf("aceptado") == 0) {
                    otroUsuario = mensaje.split("@")[1];
                    guiCliente.aceptado(otroUsuario);
                } else if (mensaje.indexOf("rechazado") == 0) {
                    otroUsuario = null;
                    guiCliente.rechazado(mensaje.split("@")[1]);
                } else if (mensaje.indexOf("empezarGrabar") == 0) {
                    guiCliente.empezarGrabar(mensaje.split("@")[1].split("_"));
                } else if (mensaje.indexOf("pararGrabar") == 0) {
                    guiCliente.pararGrabar();
                } else if (mensaje.indexOf("audioRuido") == 0) {
                    guiCliente.audioRuido(mensaje.split("@")[1]);
                } else if (mensaje.indexOf("saliendo") == 0) {
                    otroUsuario = null;
                    guiCliente.salio(mensaje.split("@")[1]);
                }
            } catch (IOException ex) {
                String msj = "Cliente no se pudo leer!";
                JOptionPane.showMessageDialog(guiCliente, msj, "Error", JOptionPane.ERROR_MESSAGE);
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
            String msj = "No se pudo apagar el cliente!";
            JOptionPane.showMessageDialog(guiCliente, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}

