package fxml.ftpfx.conexiones;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class ConexionFTP {
	//FTPClient clienteFTP = new FTPClient();
	
	public static void conectar(FTPClient clienteFTP) {
		try {
			clienteFTP.connect("localhost");
			
			if(clienteFTP.login("user1", "user1") == true) {
				System.out.println("Se ha logueado");
			}else {
				System.out.println("No ha conseguido loguearse");
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void desconectar(FTPClient clienteFTP) {
		try {
			clienteFTP.logout();
			clienteFTP.disconnect();
			
			System.out.println("Se ha desconectado");
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void listarFicheros(FTPClient clienteFTP) {
		try {
			FTPFile[] files = clienteFTP.listFiles();
			for (FTPFile file : files) {
	            String name = file.getName();	           	 
	            System.out.println(name);
	        }		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void guardarFicheros(FTPClient clienteFTP) {
		try {
			String texto = "hola, hola";
			InputStream is = new ByteArrayInputStream(texto.getBytes(StandardCharsets.UTF_8));
			if(clienteFTP.storeFile("guardoDesdeRemoto.txt", is)) {
				System.out.println("Fichero guardado.");
			}else {
				System.out.println("Problemas al guardar el fichero.");
			}		
			is.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void descargarFicheros(FTPClient clienteFTP) {
		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream("C:/Users/Alef/Documents/FTPFile/anastasia/guardoDesdeRemoto.txt"));
            if(clienteFTP.retrieveFile("guardoDesdeRemoto.txt", os)) {
            	System.out.println("Fichero descargado.");
            };
            os.close();		
        }catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void eliminarFichero(FTPClient clienteFTP) {
		try {
			if(clienteFTP.deleteFile("guardoDesdeRemoto.txt")) {
				System.out.println("Fichero eliminado.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void eliminarDirectorio(FTPClient clienteFTP) {
		try {
			if(clienteFTP.removeDirectory("/borrar")) {
				System.out.println("Directorio eliminado.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void crearDirectorio(FTPClient clienteFTP) {
		try {
			if(clienteFTP.makeDirectory("/borrar")) {
				System.out.println("Directorio creado.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void cambiarDirectorioActual(FTPClient clienteFTP) {
		try {
			if(clienteFTP.changeWorkingDirectory("/anastasia")) {
				System.out.println("Directorio cambiado.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
