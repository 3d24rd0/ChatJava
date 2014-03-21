package Chat.Servicio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;


public class ClienteCalc {
	public static void main(String[] args) {
		try {
			System.out.println("Creando socket cliente");
			
			Socket clienteSocket = new Socket();
			
			System.out.println("Estableciendo la conexion");
			
			InetSocketAddress addr = new InetSocketAddress("localhost", 6555);
			clienteSocket.connect(addr);
			InputStream is = clienteSocket.getInputStream();
			OutputStream os = clienteSocket.getOutputStream();
			
			System.out.println("Enviando peticion de suma");
			
			os.write("+".getBytes());
			
			System.out.println("Enviando primer operador");
			
			os.write(59);
			
			System.out.println("Enviando segundo operador");
			
			os.write(130);
			
			System.out.println("Recibiendo resultado");
			
			int result = is.read();
			
			System.out.println("Resultado de la suma: " + result);
			System.out.println("Cerrando el socket cliente");
			
			clienteSocket.close();
			
			System.out.println("Terminado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
