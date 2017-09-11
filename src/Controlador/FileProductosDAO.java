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

import Modelo.Productos;
import Controlador.ProductosDAO;
import Modelo.Productos;

public class FileProductosDAO implements ProductosDAO {

	private static final String REGISTRO_ELIMINADO_TEXT = "||||||||||";
	private static final String NOMBRE_ARCHIVO = "Productos";
	private static final Path file= Paths.get(NOMBRE_ARCHIVO);
	private static final int LONGITUD_REGISTRO = 141;
	private static final int ID_PRODUCTO_LONGITUD = 10;
	private static final int NOMBRES_LONGITUD = 25;
        private static final int REFERENCIA_TIPO = 25;
        private static final int DESC_COLECCION = 50;
        private static final int IMAGE = 20;
        private static final int PRECIO = 10;
     
	
	
	private static final Map<String, Productos> CACHE_PRODUCTOS = new HashMap<String, Productos>();
	

	@Override
	public boolean saveProductos(Productos producto) {
		
		String registro=parseClienteString(producto);

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
	public Productos getProductos(String id_producto) {
		//se busca el objeto en memoria cach�
		Productos producto = CACHE_PRODUCTOS.get(id_producto);
		
		if(producto!=null){
			System.out.println("ocurrió un hit en caché de productos");//contraejemplo, no hacer, no recomendable, usar Logger como log4J
			return producto; //ocurri� un hit de cach�
		}
		System.out.println("ocurrió un miss en caché de productos");
		//ocurrió un miss de caché
		try (SeekableByteChannel sbc = Files.newByteChannel(file)) {
		    ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);		    
		    // Se utiliza la propiedad del sistema que indica la codificaci�n de los archivos 
		    String encoding = System.getProperty("file.encoding");
		    while (sbc.read(buf) > 0) {
		        buf.rewind();
		        CharBuffer registro= Charset.forName(encoding).decode(buf);
		        String identificacion = registro.subSequence(0, ID_PRODUCTO_LONGITUD).toString().trim();
		        if(identificacion.equals(id_producto)){
		        	producto = parseCliente(registro);
		        	CACHE_PRODUCTOS.put(id_producto, producto);
		        	return producto;
		        }
		        buf.flip();		        		        
		    }
		} catch (IOException x) {
		    System.out.println("caught exception: " + x);
		}
		return null;		
	}

	

	
	@Override
	public List<Productos> getAllProductos() {	
		List<Productos> productos = new ArrayList<Productos>();
		try (SeekableByteChannel sbc = Files.newByteChannel(file)) {
		    ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);  
		    
		    // Se utiliza la propiedad del sistema que indica la codificaci�n de los archivos 
		    String encoding = System.getProperty("file.encoding");
		    while (sbc.read(buf) > 0) {
		        buf.rewind();
		        Productos producto = parseProducto(Charset.forName(encoding).decode(buf));
		        buf.flip();
		        productos.add(producto);		        
		    }
		} catch (IOException x) {
		    System.out.println("caught exception: " + x);
		}
		return productos;
	}
		


	
	private Productos parseCliente(CharBuffer registro){		
		String id_producto = registro.subSequence(0, ID_PRODUCTO_LONGITUD ).toString();
		registro.position(ID_PRODUCTO_LONGITUD);
		registro=registro.slice();
				
		String nombres = registro.subSequence(0, NOMBRES_LONGITUD).toString();
		registro.position(NOMBRES_LONGITUD);	
		registro=registro.slice();	
                
                String referencia_tipo = registro.subSequence(0, REFERENCIA_TIPO).toString();
		registro.position(REFERENCIA_TIPO);	
		registro=registro.slice();
                
                String desc_coleccion = registro.subSequence(0, DESC_COLECCION).toString();
		registro.position(DESC_COLECCION);	
		registro=registro.slice();
                
                String precio = registro.subSequence(0, PRECIO).toString();
		registro.position(PRECIO);	
		registro=registro.slice();
              
		String image = registro.subSequence(0, IMAGE).toString();
		registro.position(IMAGE);	
		registro=registro.slice();
               
		Productos c = new Productos(id_producto, precio, desc_coleccion, precio, image);
		
                
                return c;
	}
	
	private String parseClienteString(Productos producto){
		StringBuilder registro = new StringBuilder(LONGITUD_REGISTRO);
		registro.append(completarCampoConEspacios(producto.getId_producto(),ID_PRODUCTO_LONGITUD));
		registro.append(completarCampoConEspacios(producto.getNombres(), NOMBRES_LONGITUD));
                registro.append(completarCampoConEspacios(producto.getApellidos(), NOMBRES_LONGITUD));
                registro.append(completarCampoConEspacios(producto.getCorreo_e(), NOMBRES_LONGITUD));
                registro.append(completarCampoConEspacios(producto.getCelular(), NOMBRES_LONGITUD));
                registro.append(completarCampoConEspacios(producto.getDireccion(), NOMBRES_LONGITUD));
                registro.append(completarCampoConEspacios(producto.getFecha_nacimiento(), NOMBRES_LONGITUD));
			
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

    @Override
    public Productos getProductos(String id_producto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveProductos(Productos producto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Productos> getAllProductos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
/**
 *
 * @author carloscarrascal
 */