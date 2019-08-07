package com.android.jarvis.conexion;

import android.os.Build;
import android.util.Log;

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

public class Servidor extends Thread {

    public static ArrayList<Atencion> clientes;
    private ServerSocket servidor;
    private boolean conectado;

    public Servidor() {
        clientes = new ArrayList<>();
        servidor = null;
        conectado = false;
    }

    public void encender(int maxClientes) throws IOException {
        servidor = new ServerSocket(12354, maxClientes);
        conectado = true;
        this.start();
    }

    public void apagar() throws IOException {
        while (!clientes.isEmpty()) {
            clientes.get(0).enviarAlCliente("apagoServidor");
            clientes.get(0).cerrarConexion();
            clientes.remove(0);
        }
        conectado = false;
        servidor.close();
        //this.stop();
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
                Log.e("ERROR", "Problema de I/O en el servidor");
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
                        Log.e("ERROR", "No se pudo verificar la interface si es loopback");
                    }
                }
            }
            try {
                if (ipPC == null) {
                    ipPC = InetAddress.getLocalHost().getHostAddress();
                }
                host = Build.MODEL+"/"+ipPC;
            } catch (UnknownHostException ex) {
                Log.e("ERROR", "Host desconocido");
            }
        } catch (SocketException ex) {
            Log.e("ERROR", "No se pudo obtener las interfaces de red");
        }

        return host;
    }

}
