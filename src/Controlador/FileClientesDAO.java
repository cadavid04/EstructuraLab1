/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author carloscarrascal
 */


import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Modelo.Clientes;
import Controlador.ClientesDAO;

public class FileClientesDAO implements ClientesDAO {

	private static final String REGISTRO_ELIMINADO_TEXT = "||||||||||";
	private static final String NOMBRE_ARCHIVO = "Clientes";
	private static final Path file= Paths.get(NOMBRE_ARCHIVO);
	private static final int LONGITUD_REGISTRO = 236;
	private static final int ID_CLIENTE_LONGITUD = 10;
	private static final int NOMBRES_LONGITUD = 25;
        private static final int APELLIDOS_LONGITUD = 25;
        private static final int CORREO_LONGITUD = 50;
        private static final int CELULAR_LONGITUD = 10;
        private static final int FECHA_NACIMIENTO_LONGITUD = 10;
	private static final int DIRECCION_LONGITUD = 100;
        private static final int NUEVO_LONGITUD = 5;
	
	
	private static final Map<String, Clientes> CACHE_CLIENTES = new HashMap<String, Clientes>();
	

	@Override
	public boolean saveCliente(Clientes cliente) {
		
		String registro=parseClienteString(cliente);

		byte data[] = registro.getBytes();
		ByteBuffer out = ByteBuffer.wrap(data);	
		try (FileChannel fc = (FileChannel.open(file, APPEND))) {
			fc.write(out);
			return true;
		} catch (IOException x) {
			System.out.println("I/O Exception: " + x);
		}
		return false;
	}
	
	
	@Override
	public Clientes getClientes(String id_cliente) {
		//se busca el objeto en memoria cach�
		Clientes cliente = CACHE_CLIENTES.get(id_cliente);
		
		if(cliente!=null){
			System.out.println("ocurrió un hit en caché de clientes");//contraejemplo, no hacer, no recomendable, usar Logger como log4J
			return cliente; //ocurri� un hit de cach�
		}
		System.out.println("ocurrió un miss en caché de clientes");
		//ocurrió un miss de caché
		try (SeekableByteChannel sbc = Files.newByteChannel(file)) {
		    ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);		    
		    // Se utiliza la propiedad del sistema que indica la codificaci�n de los archivos 
		    String encoding = System.getProperty("file.encoding");
		    while (sbc.read(buf) > 0) {
		        buf.rewind();
		        CharBuffer registro= Charset.forName(encoding).decode(buf);
		        String identificacion = registro.subSequence(0, ID_CLIENTE_LONGITUD).toString().trim();
		        if(identificacion.equals(id_cliente)){
		        	cliente = parseCliente(registro);
		        	CACHE_CLIENTES.put(id_cliente, cliente);
		        	return cliente;
		        }
		        buf.flip();		        		        
		    }
		} catch (IOException x) {
		    System.out.println("caught exception: " + x);
		}
		return null;		
	}

	

	
	@Override
	public List<Clientes> getAllClientes() {	
		List<Clientes> personas = new ArrayList<Clientes>();
		try (SeekableByteChannel sbc = Files.newByteChannel(file)) {
		    ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);  
		    
		    // Se utiliza la propiedad del sistema que indica la codificaci�n de los archivos 
		    String encoding = System.getProperty("file.encoding");
		    while (sbc.read(buf) > 0) {
		        buf.rewind();
		        Clientes cliente = parseCliente(Charset.forName(encoding).decode(buf));
		        buf.flip();
		        personas.add(cliente);		        
		    }
		} catch (IOException x) {
		    System.out.println("caught exception: " + x);
		}
		return personas;
	}
		


	
	private Clientes parseCliente(CharBuffer registro){		
		String id_cliente = registro.subSequence(0, ID_CLIENTE_LONGITUD ).toString();
		registro.position(ID_CLIENTE_LONGITUD);
		registro=registro.slice();
				
		String nombres = registro.subSequence(0, NOMBRES_LONGITUD).toString();
		registro.position(NOMBRES_LONGITUD);	
		registro=registro.slice();	
                
                String apellidos = registro.subSequence(0, APELLIDOS_LONGITUD).toString();
		registro.position(APELLIDOS_LONGITUD);	
		registro=registro.slice();
                
                String correo = registro.subSequence(0, CORREO_LONGITUD).toString();
		registro.position(CORREO_LONGITUD);	
		registro=registro.slice();
                
                String celular = registro.subSequence(0, CELULAR_LONGITUD).toString();
		registro.position(CELULAR_LONGITUD);	
		registro=registro.slice();
                
                String fecha_nacimiento = registro.subSequence(0, FECHA_NACIMIENTO_LONGITUD).toString();
		registro.position(FECHA_NACIMIENTO_LONGITUD);	
		registro=registro.slice();
		
		String direccion = registro.subSequence(0, DIRECCION_LONGITUD).toString();
		registro.position(DIRECCION_LONGITUD);	
		registro=registro.slice();
                
                String nuevo = registro.subSequence(0, NUEVO_LONGITUD).toString();
		registro.position(NUEVO_LONGITUD);	
		registro=registro.slice();
		
		
		Clientes c = new Clientes(id_cliente, nombres, apellidos, correo, celular, fecha_nacimiento, direccion, nuevo);
		return c;
	}
	
	private String parseClienteString(Clientes cliente){
		StringBuilder registro = new StringBuilder(LONGITUD_REGISTRO);
		registro.append(completarCampoConEspacios(cliente.getId_cliente(),ID_CLIENTE_LONGITUD));
		registro.append(completarCampoConEspacios(cliente.getNombres(), NOMBRES_LONGITUD));
                registro.append(completarCampoConEspacios(cliente.getApellidos(), NOMBRES_LONGITUD));
                registro.append(completarCampoConEspacios(cliente.getCorreo_e(), NOMBRES_LONGITUD));
                registro.append(completarCampoConEspacios(cliente.getCelular(), NOMBRES_LONGITUD));
                registro.append(completarCampoConEspacios(cliente.getDireccion(), NOMBRES_LONGITUD));
                registro.append(completarCampoConEspacios(cliente.getFecha_nacimiento(), NOMBRES_LONGITUD));
			
		return registro.toString();
	}
	
	/**
	 * Rellena con espacios a la derecha el String que se env�e como par�metro. Si el tama�o del String
	 * supera el valor del tama�o que se env�a como par�metro, se ajusta el contenido del String a dicho tama�o
	 * @param campo, tamanio
	 * @return String
	 */
	private String completarCampoConEspacios(String campo, int tamanio){
		if(campo.length()>tamanio){//Ser responsable y lanzar una excepci�n
			campo=campo.substring(0, tamanio);
			return campo;
		}
		return String.format("%1$-" + tamanio + "s", campo);
	}


}
/**
 *
 * @author carloscarrascal
 */