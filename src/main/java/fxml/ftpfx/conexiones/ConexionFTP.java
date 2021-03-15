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

import fxml.ftpfx.utils.Leer;

public class ConexionFTP {
	//FTPClient clienteFTP = new FTPClient();
	
	public static void conectar(FTPClient clienteFTP) {
		try {
			clienteFTP.connect("localhost");
//			clienteFTP.login("user1", "user1")
			System.out.println("Escriba su nombre de usuario.");
			String nombreUser = Leer.pedirCadena();
			System.out.println("Escriba su contraseña.");
			String passUser = Leer.pedirCadena();
			
			if(clienteFTP.login(nombreUser, passUser) == true) {
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
//			"guardoDesdeRemoto.txt"
			System.out.println("Escriba el nombre del fichero que quiere descargar.");
			String nombreFichero = Leer.pedirCadena();
			
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream("C:/Users/Alef/Documents/FTPFile/descargas/" + nombreFichero));
            if(clienteFTP.retrieveFile(nombreFichero, os)) {
            	System.out.println("Fichero descargado.");
            }else {
            	System.out.println("Ha habido un problema con la descarga.");
            }
            os.close();		
        }catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void eliminarFichero(FTPClient clienteFTP) {
		try {
			System.out.println("Escriba el nombre del fichero que quiere eliminar.");
			String nombreFichero = Leer.pedirCadena();
			
			if(clienteFTP.deleteFile(nombreFichero)) {
				System.out.println("Fichero eliminado.");
			}else {
				System.out.println("Ha ocurrido un problema.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void eliminarDirectorio(FTPClient clienteFTP) {
		try {
			System.out.println("Escriba el nombre del directorio que quiere eliminar.");
			String nombreDirectorio = Leer.pedirCadena();
			
			if(clienteFTP.removeDirectory(nombreDirectorio)) {
				System.out.println("Directorio eliminado.");
			}else {
				System.out.println("Ha ocurrido un problema.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void crearDirectorio(FTPClient clienteFTP) {
		try {
			System.out.println("Escriba el nombre del directorio que quiere crear.");
			String nombreDirectorio = Leer.pedirCadena();
			
			if(clienteFTP.makeDirectory("/" + nombreDirectorio)) {
				System.out.println("Directorio creado.");
			} else {
				System.out.println("Ha ocurrido un problema.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void cambiarDirectorioActual(FTPClient clienteFTP) {
		try {
			System.out.println("Escriba el nombre del directorio que quiere ir."
					+ " Si escribe .. volverá al directorio anterior.");
			String nombreDirectorio = Leer.pedirCadena();
			
			if(nombreDirectorio == "..") {
				if(clienteFTP.changeWorkingDirectory(nombreDirectorio)) {
					System.out.println("Directorio cambiado.");
				}else {
					System.out.println("Ha ocurrido un problema.");
				}
			} else {
				if(clienteFTP.changeWorkingDirectory("/" + nombreDirectorio)) {
					System.out.println("Directorio cambiado.");
				}else {
					System.out.println("Ha ocurrido un problema.");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
