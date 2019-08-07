package conexion;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JOptionPane;

/**
 *
 * @author EDWIN JONATAN SANCHEZ PEDRO
 */

public class Servidor extends Thread {
    
    public static ArrayList<Atencion> clientes;
    private ServerSocket servidor;
    private boolean conectado;

    public Servidor() {
        clientes = new ArrayList<>();
        conectado = false;
    }

    public void encender(int maxClientes) {
        try {
            servidor = new ServerSocket(12354, maxClientes);
            conectado = true;
            this.start();
        } catch (IOException ex) {
            String msj = "No se pudo crear el servidor!";
            JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void apagar() {
        try {
            while (!clientes.isEmpty()) {
                clientes.get(0).enviarAlCliente("apagoServidor");
                clientes.get(0).cerrarConexion();
                clientes.remove(0);
            }
            conectado = false;
            servidor.close();
            this.stop();
        } catch (IOException ex) {
            String msj = "No se pudo apagar el servidor!";
            JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void run() {
        while (conectado) {
            try {
                if (!servidor.isClosed()) {
                    //Esperando Conexion...
                    Socket conexion = servidor.accept();
                    //Nueva Conexion...
                    Atencion atencion = new Atencion(conexion);
                    if (atencion.obtenerFlujos()) {
                        atencion.start();
                        clientes.add(atencion);
                    }
                }
            } catch (IOException ex) {
                String msj = "Problema de I/O en el servidor!";
                JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static String getIpNombrePC() {
        String host = null;
        
        try { //obtenemos todas las interfaces: ethernet, wifi, virtual... etc
            Enumeration<NetworkInterface> net = NetworkInterface.getNetworkInterfaces();
            String ipPC = null;
            if (net != null) {
                while(net.hasMoreElements() && ipPC == null) {
                    NetworkInterface netInterface = net.nextElement();
                    try { //descartar interfaces virtual y loopback (127.0.0.1)
                        if (!netInterface.isVirtual() && !netInterface.isLoopback()) {
                            // Wifi or ethernet interfaces
                            Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                            while (addresses.hasMoreElements()) {
                                InetAddress ip = addresses.nextElement();
                                if (ip instanceof Inet4Address) {
                                    if (ip.isSiteLocalAddress()) {
                                        String nombreInterface = netInterface.getDisplayName();
                                        if (!nombreInterface.contains("VMware") && 
                                            !nombreInterface.contains("VirtualBox")) {
                                            ipPC = ip.getHostAddress();
                                        }
                                    }
                                }
                            }
                        }
                    } catch (SocketException ex) {
                        String msj = "No se pudo verificar\nla interface si es loopback!";
                        JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            try {
                if (ipPC == null) {
                    ipPC = InetAddress.getLocalHost().getHostAddress();
                }
                host = InetAddress.getLocalHost().getHostName()+"/"+ipPC;
            } catch (UnknownHostException ex) {
                String msj = "Host desconocido!";
                JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SocketException ex) {
            String msj = "No se pudo obtener las interfaces de red!";
            JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return host;
    }
    
}
