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

import Modelo.Almacenes;
import Controlador.AlmacenesDAO;


public class FileAlmacenesDAO implements AlmacenesDAO {

	private static final String REGISTRO_ELIMINADO_TEXT = "||||||||||";
	private static final String NOMBRE_ARCHIVO = "Almacenes";
	private static final Path file= Paths.get(NOMBRE_ARCHIVO);
	private static final int LONGITUD_REGISTRO = 96;
	private static final int CODIGO_ALMACEN_LONGITUD = 20;
	private static final int CIUDAD_LONGITUD = 25;
        private static final int HORARIO_LONGITUD = 25;
        private static final int DIRECCION_LONGITUD = 25;
        
	
	
	private static final Map<String, Almacenes> CACHE_ALMACENES = new HashMap<String, Almacenes>();
	

	@Override
	public boolean saveAlmacenes(Almacenes almacen) {
		
		String registro=parseAlmacenesString(almacen);

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
	public Almacenes getAlmacenes(String c_almacen) {
		//se busca el objeto en memoria cach�
		Almacenes almacen = CACHE_ALMACENES.get(c_almacen);
		
		if(almacen!=null){
			System.out.println("ocurrió un hit en caché de almacens");//contraejemplo, no hacer, no recomendable, usar Logger como log4J
			return almacen; //ocurri� un hit de cach�
		}
		System.out.println("ocurrió un miss en caché de almacens");
		//ocurrió un miss de caché
		try (SeekableByteChannel sbc = Files.newByteChannel(file)) {
		    ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);		    
		    // Se utiliza la propiedad del sistema que indica la codificaci�n de los archivos 
		    String encoding = System.getProperty("file.encoding");
		    while (sbc.read(buf) > 0) {
		        buf.rewind();
		        CharBuffer registro= Charset.forName(encoding).decode(buf);
		        String identificacion = registro.subSequence(0, CODIGO_ALMACEN_LONGITUD).toString().trim();
		        if(identificacion.equals(c_almacen)){
		        	almacen = parseAlmacen(registro);
		        	CACHE_ALMACENES.put(c_almacen, almacen);
		        	return almacen;
		        }
		        buf.flip();		        		        
		    }
		} catch (IOException x) {
		    System.out.println("caught exception: " + x);
		}
		return null;		
	}

	

	
	@Override
	public List<Almacenes> getAllAlmacenes() {	
		List<Almacenes> almacens = new ArrayList<Almacenes>();
		try (SeekableByteChannel sbc = Files.newByteChannel(file)) {
		    ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);  
		    
		    // Se utiliza la propiedad del sistema que indica la codificaci�n de los archivos 
		    String encoding = System.getProperty("file.encoding");
		    while (sbc.read(buf) > 0) {
		        buf.rewind();
		        Almacenes almacen = parseAlmacen(Charset.forName(encoding).decode(buf));
		        buf.flip();
		        almacens.add(almacen);		        
		    }
		} catch (IOException x) {
		    System.out.println("caught exception: " + x);
		}
		return almacens;
	}
		


	
	private Almacenes parseAlmacen(CharBuffer registro){		
		String c_almacen = registro.subSequence(0, CODIGO_ALMACEN_LONGITUD ).toString();
		registro.position(CODIGO_ALMACEN_LONGITUD);
		registro=registro.slice();
				
		String ciudad = registro.subSequence(0, CIUDAD_LONGITUD).toString();
		registro.position(CIUDAD_LONGITUD);	
		registro=registro.slice();	
                
                String horario = registro.subSequence(0, HORARIO_LONGITUD).toString();
		registro.position(HORARIO_LONGITUD);	
		registro=registro.slice();
                
                String direccion = registro.subSequence(0, DIRECCION_LONGITUD).toString();
		registro.position(DIRECCION_LONGITUD);	
		registro=registro.slice();
              
		Almacenes a = new Almacenes(c_almacen, ciudad, direccion, horario);
		 
                return a;
	}
	
	private String parseAlmacenesString(Almacenes almacen){
		StringBuilder registro = new StringBuilder(LONGITUD_REGISTRO);
		registro.append(completarCampoConEspacios(almacen.getCodigo_almacen(),CODIGO_ALMACEN_LONGITUD));
		registro.append(completarCampoConEspacios(almacen.getDireccion(),DIRECCION_LONGITUD));
                registro.append(completarCampoConEspacios(almacen.getHorario(), HORARIO_LONGITUD));
                registro.append(completarCampoConEspacios(almacen.getCiudad(), CIUDAD_LONGITUD));
                
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