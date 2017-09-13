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

import Modelo.Colecciones;
import Controlador.ColeccionesDAO;

public class FileColeccionesDAO implements ColeccionesDAO {

	private static final String REGISTRO_ELIMINADO_TEXT = "||||||||||";
	private static final String NOMBRE_ARCHIVO = "Colecciones";
	private static final Path file= Paths.get(NOMBRE_ARCHIVO);
	private static final int LONGITUD_REGISTRO = 51;
	private static final int DESC_COLECCION_LONGITUD = 20;
	private static final int ANO_TRIMESTRE_LONGITUD = 20;
        private static final int EXCLUSIVIDAD_LONGITUD = 20;
		
	private static final Map<String, Colecciones> CACHE_TIPO_COLECCIONES = new HashMap<String, Colecciones>();

	@Override
	public boolean saveColecciones(Colecciones c_colecciones) {
		
		String registro=parseTipoProductoString(c_colecciones);

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
	public Colecciones getColecciones(String ref_tipo) {
		//se busca el objeto en memoria cach�
		Colecciones tipo_productos = CACHE_TIPO_COLECCIONES.get(ref_tipo);
		
		if(tipo_productos!=null){
			System.out.println("ocurri� un hit en cach� de personas");//contraejemplo, no hacer, no recomendable, usar Logger como log4J
			return tipo_productos; //ocurri� un hit de cach�
		}
		System.out.println("ocurri� un miss en cach� de personas");
		//ocurri� un miss de cach�
		try (SeekableByteChannel sbc = Files.newByteChannel(file)) {
		    ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);		    
		    // Se utiliza la propiedad del sistema que indica la codificaci�n de los archivos 
		    String encoding = System.getProperty("file.encoding");
		    while (sbc.read(buf) > 0) {
		        buf.rewind();
		        CharBuffer registro= Charset.forName(encoding).decode(buf);
		        String identificacion = registro.subSequence(0, DESC_COLECCION_LONGITUD).toString().trim();
		        if(identificacion.equals(ref_tipo)){
		        	tipo_productos = parseTipoProductos(registro);
		        	CACHE_TIPO_COLECCIONES.put(ref_tipo, tipo_productos);
		        	return tipo_productos;
		        }
		        buf.flip();		        		        
		    }
		} catch (IOException x) {
		    System.out.println("caught exception: " + x);
		}
		return null;		
	}

	@Override
	public List<Colecciones> getAllColecciones() {	
		List<Colecciones> productos= new ArrayList<Colecciones>();
		try (SeekableByteChannel sbc = Files.newByteChannel(file)) {
		    ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);  
		    
		    // Se utiliza la propiedad del sistema que indica la codificaci�n de los archivos 
		    String encoding = System.getProperty("file.encoding");
		    while (sbc.read(buf) > 0) {
		        buf.rewind();
		        Colecciones persona = parseTipoProductos(Charset.forName(encoding).decode(buf));
		        buf.flip();
		        productos.add(persona);		        
		    }
		} catch (IOException x) {
		    System.out.println("caught exception: " + x);
		}
		return productos;
	}

	private Colecciones parseTipoProductos(CharBuffer registro){		
		String desc_coleccion = registro.subSequence(0, DESC_COLECCION_LONGITUD ).toString();
		registro.position(DESC_COLECCION_LONGITUD);
		registro=registro.slice();
				
		String trimestre = registro.subSequence(0, ANO_TRIMESTRE_LONGITUD).toString();
		registro.position(ANO_TRIMESTRE_LONGITUD);	
		registro=registro.slice();
                
                String exclusividad = registro.subSequence(0, EXCLUSIVIDAD_LONGITUD).toString();
		registro.position(EXCLUSIVIDAD_LONGITUD);	
		registro=registro.slice();
		
				
		
		Colecciones c=new Colecciones(desc_coleccion, trimestre,exclusividad);
		return c;
	}
	
	private String parseTipoProductoString(Colecciones c_colecciones){
		StringBuilder registro = new StringBuilder(LONGITUD_REGISTRO);
		registro.append(completarCampoConEspacios(c_colecciones.getDesc_coleccion(),DESC_COLECCION_LONGITUD));
		registro.append(completarCampoConEspacios(c_colecciones.getAno_trimestre(), ANO_TRIMESTRE_LONGITUD));
                registro.append(completarCampoConEspacios(c_colecciones.getExclusividad(), EXCLUSIVIDAD_LONGITUD));
			
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