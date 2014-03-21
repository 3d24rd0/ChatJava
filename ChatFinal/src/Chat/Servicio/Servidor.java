/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat.Servicio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author eduardo
 */
public class Servidor extends Thread {

    private Socket clientSocket;

    private Servidor(Socket socket) {
        clientSocket = socket;
    }

    public Servidor() {
        System.out.println("Creando socket servidor");
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket();
            System.out.println("Realizando el bild");

            InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 6555);
            serverSocket.bind(addr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Aceptando conexiones");

        while (serverSocket != null) {
            try {
                Socket newSocket = serverSocket.accept();//espera

                System.out.println("Conexion recibida");

                Servidor hilo = new Servidor(newSocket);
                hilo.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Terminado");
    }

    public void run() {
        try {
            System.out.println("Arrancando hilo");

            InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream();

            System.out.println("Esperando mensaje de operacion");

            byte[] buffer = new byte[1];
            is.read(buffer);
            String operacion = new String(buffer);

            System.out.println("Operacion recibida: " + new String(operacion));

            if (operacion.equals("+") || operacion.equals("-")
                    || operacion.equals("*") || operacion.equals("/")) {
                System.out.println("Esperando primer operado");

                int op1 = is.read();

                System.out.println("Primer operador: " + op1);
                System.out.println("Esperando segundo operador");

                int op2 = is.read();

                System.out.println("Segundo operador: " + op2);
                System.out.println("Calculando resultado");

                int result = Integer.MIN_VALUE;

                if (operacion.equals("+")) {
                    result = op1 + op2;
                } else if (operacion.equals("-")) {
                    result = op1 - op2;
                } else if (operacion.equals("*")) {
                    result = op1 * op2;
                } else if (operacion.equals("/")) {
                    result = op1 / op2;
                }

                System.out.println("Enviando resultado");

                os.write(result);
            } else {
                System.out.println("Operacion no reconocida");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Hilo terminado");
    }

}
