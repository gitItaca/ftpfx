package fxml.ftpfx;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;

import fxml.ftpfx.conexiones.ConexionFTP;
import fxml.ftpfx.utils.Leer;

public class ClienteFTP {

	public static void main(String[] args) {
		FTPClient clienteFTP = new FTPClient();		
		int opcion;
		do {
			opcion = menu();	
			switch (opcion) {
			case 1: ConexionFTP.conectar(clienteFTP);
				break;
			case 2: ConexionFTP.desconectar(clienteFTP);
				break;
			case 3: ConexionFTP.listarFicheros(clienteFTP);
				break;
			case 4: ConexionFTP.guardarFicheros(clienteFTP);
				break;
			case 5: ConexionFTP.descargarFicheros(clienteFTP);
				break;
			case 6: ConexionFTP.eliminarFichero(clienteFTP);
				break;
			case 7: ConexionFTP.eliminarDirectorio(clienteFTP);
				break;
			case 8: ConexionFTP.crearDirectorio(clienteFTP);
				break;
			case 9: ConexionFTP.cambiarDirectorioActual(clienteFTP);
			break;
			} 
		}while(opcion != 0);
		
					
	}//FIN main
	
	public static int menu() {
		System.out.println("Elige una de las siguientes opciones, para salir pulse 0.");
		System.out.println("1- Iniciar sesión.");
		System.out.println("2- Cerrar sesión.");
		System.out.println("3- Listar ficheros y directorios.");
		System.out.println("4- Subir fichero.");
		System.out.println("5- Descargar fichero del servidor.");
		System.out.println("6- Eliminar fichero.");
		System.out.println("7- Eliminar directorio.");
		System.out.println("8- Crear directorio.");
		System.out.println("9- Cambiar directorio actual.");
		System.out.println("10- Establecer conexion remotamente.");
		int opcion = Leer.pedirEnteroValidar();		
		
		return opcion;
	}

}
