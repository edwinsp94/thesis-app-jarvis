package conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author EDWIN JONATAN SANCHEZ PEDRO
 */

public class Atencion extends Thread {
    
    private final Socket socket;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private boolean conectado;
    private boolean visible;
    private String user;
    
    public Atencion(Socket conexion) {
        this.socket = conexion;
        this.conectado = false;
        this.visible = false;
        this.user = null;
    }

    public boolean obtenerFlujos() {
        try {
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());
            conectado = true;
            visible = true;
        } catch (IOException ex) {
            String msj = "No se pudo obtner los flujos!";
            JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return conectado;
    }

    public void enviarAlCliente(String mensaje) {
        try {
            salida.writeUTF(mensaje);
        } catch (IOException ex) {
            String msj = "No se pudo enviar la informacion al cliente!";
            JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarLista () {
        String cad = "";
        for (Atencion a : Servidor.clientes) {
            if (a != this && a.visible) {
                cad += (a.user+"@");
            }
        }
        if (!cad.isEmpty()) {
            cad = cad.substring(0, cad.length()-1);
        }
        enviarAlCliente("visibles@"+cad);
    }
     
    private void salir() {
        cerrarConexion();
        eliminarCliente(this);
    }
    
    private void eliminarCliente(Atencion a) {
        Servidor.clientes.remove(a);
    }

    public void cerrarConexion() {
        try {
            conectado = false;
            entrada.close();
            salida.close();
            socket.close();
        } catch (IOException ex) {
            String msj = "No se pudo cerrar la conexion!";
            JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean existe(String user) {
        boolean existe = false;
        if (!Servidor.clientes.isEmpty()) {
            for (int i = 0; !existe && i < Servidor.clientes.size(); i++) {
                Atencion a = Servidor.clientes.get(i);
                if (a != this && a.user.equals(user)) {
                    existe = true;
                }
            }
        }
        return existe;
    }
    
    @Override   
    public void run() {
        while (conectado) {
            try {
                String mensaje = entrada.readUTF();
                if (mensaje.indexOf("ingreso") == 0) {
                    user = mensaje.split("@")[1];
                    if (existe(user)) {
                        enviarAlCliente("malIngreso");
                        salir();
                    } else {
                        enviarAlCliente("buenIngreso");
                    }
                } else if (mensaje.indexOf("apagar") == 0) {
                    enviarAlCliente("apagar");
                    salir();
                } else if (mensaje.indexOf("getVisibles") == 0) {
                    cargarLista();
                } else if (mensaje.indexOf("activo") == 0) {
                    visible = Boolean.parseBoolean(mensaje.split("@")[1]);
                } else if (mensaje.indexOf("online") == 0) {
                    enviarAlOtroCliente(mensaje.split("@")[1], user, "enviarSolicitud");
                } else if (mensaje.indexOf("aceptar") == 0) {
                    String[] cad = mensaje.split("@");
                    enviarAlOtroCliente(cad[1], cad[2], "aceptado");
                } else if (mensaje.indexOf("rechazar") == 0) {
                    String[] cad = mensaje.split("@");
                    enviarAlOtroCliente(cad[1], cad[2], "rechazado");
                } else if (mensaje.indexOf("empezar") == 0) {
                    String[] cad = mensaje.split("@");
                    enviarAlOtroCliente(cad[1], cad[2], "empezar");
                } else if (mensaje.indexOf("parar") == 0) {
                    enviarAlOtroCliente(mensaje.split("@")[1], "", "parar");
                } else if (mensaje.indexOf("audio") == 0) {
                    String[] cad = mensaje.split("@");
                    enviarAlOtroCliente(cad[1], cad[2], "audioGrabado");
                } else if (mensaje.indexOf("salir") == 0) {
                    String[] cad = mensaje.split("@");
                    enviarAlOtroCliente(cad[1], cad[2], "saliendo");
                }
            } catch (IOException ex) {
            }
        }
    }
    
    private void enviarAlOtroCliente(String otroUsuario, String mensaje, String accion) {
        for (Atencion a : Servidor.clientes) {
            if (a.user.equals(otroUsuario)) {
                switch (accion) {
                    case "empezar":
                        a.enviarAlCliente("empezarGrabar@"+mensaje);
                        break;
                    case "parar":
                        a.enviarAlCliente("pararGrabar");
                        break;
                    case "audioGrabado":
                        a.enviarAlCliente("audioRuido@"+mensaje);
                        break;
                    case "enviarSolicitud":
                        a.enviarAlCliente("solicitud@"+mensaje);
                        break;
                    case "aceptado":
                        a.enviarAlCliente("aceptado@"+mensaje);
                        break;
                    case "rechazado":
                        a.enviarAlCliente("rechazado@"+mensaje);
                        break;
                    case "saliendo":
                        a.enviarAlCliente("saliendo@"+mensaje);
                        break;
                }
            }
        }
    }
    
}
