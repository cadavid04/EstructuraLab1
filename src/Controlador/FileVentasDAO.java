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

import Modelo.Ventas;
import Controlador.VentasDAO;

public class FileVentasDAO implements VentasDAO {

	private static final String REGISTRO_ELIMINADO_TEXT = "||||||||||";
	private static final String NOMBRE_ARCHIVO = "Clientes";
	private static final Path file= Paths.get(NOMBRE_ARCHIVO);
	private static final int LONGITUD_REGISTRO = 80;
	private static final int CONSECUTIVO_VENTA_LONGITUD = 6;
	private static final int ID_CLIENTE_LONGITUD = 10;
        private static final int ID_CAJERO_LONGITUD = 10;
        private static final int VALOR_BRUTO_LONGITUD = 6;
        private static final int DESCUENTOS_LONGITUD = 6;
        private static final int VALOR_NETO_LONGITUD = 6;
	private static final int FECHA_LONGITUD = 10;
        private static final int HORA_LONGITUD = 5;
        private static final int MEDIO_PAGO_LONGITUD = 20;
	
	
	private static final Map<String, Ventas> CACHE_VENTAS = new HashMap<String, Ventas>();
	

	@Override
	public boolean saveVenta(Ventas ventas) {
		
		String registro=parseVentaString(ventas);

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
	public Ventas getVentas(String consecutivo_venta) {
		//se busca el objeto en memoria cach�
		Ventas venta = CACHE_VENTAS.get(consecutivo_venta);
		
		if(venta!=null){
			System.out.println("ocurrió un hit en caché de clientes");//contraejemplo, no hacer, no recomendable, usar Logger como log4J
			return venta; //ocurri� un hit de cach�
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
		        if(identificacion.equals(consecutivo_venta)){
		        	venta = parseVenta(registro);
		        	CACHE_VENTAS.put(consecutivo_venta, venta);
		        	return venta;
		        }
		        buf.flip();		        		        
		    }
		} catch (IOException x) {
		    System.out.println("caught exception: " + x);
		}
		return null;		
	}

	

	
	@Override
	public List<Ventas> getAllVentas() {	
		List<Ventas> personas = new ArrayList<Ventas>();
		try (SeekableByteChannel sbc = Files.newByteChannel(file)) {
		    ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);  
		    
		    // Se utiliza la propiedad del sistema que indica la codificaci�n de los archivos 
		    String encoding = System.getProperty("file.encoding");
		    while (sbc.read(buf) > 0) {
		        buf.rewind();
		        Ventas venta = parseVenta(Charset.forName(encoding).decode(buf));
		        buf.flip();
		        personas.add(venta);		        
		    }
		} catch (IOException x) {
		    System.out.println("caught exception: " + x);
		}
		return personas;
	}
		


	
	private Ventas parseVenta(CharBuffer registro){					
		int consecutivo_venta = Integer.parseInt(registro.subSequence(0, CONSECUTIVO_VENTA_LONGITUD).toString());
		registro.position(CONSECUTIVO_VENTA_LONGITUD);	
		registro=registro.slice();	
                
                String id_cliente = registro.subSequence(0, ID_CLIENTE_LONGITUD).toString();
		registro.position(ID_CLIENTE_LONGITUD);	
		registro=registro.slice();
                
                String id_cajero = registro.subSequence(0, ID_CAJERO_LONGITUD).toString();
		registro.position(ID_CAJERO_LONGITUD);	
		registro=registro.slice();
                
                int valor_bruto = Integer.parseInt(registro.subSequence(0, VALOR_BRUTO_LONGITUD).toString());
		registro.position(VALOR_BRUTO_LONGITUD);	
		registro=registro.slice();
                
                int descuentos = Integer.parseInt(registro.subSequence(0, DESCUENTOS_LONGITUD).toString());
		registro.position(DESCUENTOS_LONGITUD);	
		registro=registro.slice();
		
		int valor_neto = Integer.parseInt(registro.subSequence(0, VALOR_NETO_LONGITUD).toString());
		registro.position(VALOR_NETO_LONGITUD);	
		registro=registro.slice();
                
                String fecha = registro.subSequence(0, FECHA_LONGITUD).toString();
		registro.position(FECHA_LONGITUD);	
		registro=registro.slice();
		
                String hora = registro.subSequence(0, HORA_LONGITUD).toString();
		registro.position(HORA_LONGITUD);	
		registro=registro.slice();
                
                String medio_pago = registro.subSequence(0, MEDIO_PAGO_LONGITUD).toString();
		registro.position(MEDIO_PAGO_LONGITUD);	
		registro=registro.slice();
		
		Ventas v = new Ventas(consecutivo_venta, id_cliente, id_cajero, valor_bruto, descuentos, valor_neto, fecha, hora,medio_pago);
		return v;
	}
	
	private String parseVentaString(Ventas venta){
		StringBuilder registro = new StringBuilder(LONGITUD_REGISTRO);
		registro.append(completarCampoConEspacios(String.valueOf(venta.getConsecutivo_venta()),ID_CLIENTE_LONGITUD));
		registro.append(completarCampoConEspacios(venta.getId_cliente(), ID_CLIENTE_LONGITUD));
                registro.append(completarCampoConEspacios(venta.getId_cajero(), ID_CAJERO_LONGITUD));
                registro.append(completarCampoConEspacios(String.valueOf(venta.getValor_bruto()), VALOR_BRUTO_LONGITUD));
                registro.append(completarCampoConEspacios(String.valueOf(venta.getDescuentos()), DESCUENTOS_LONGITUD));
                registro.append(completarCampoConEspacios(String.valueOf(venta.getValor_neto()), VALOR_NETO_LONGITUD));
                registro.append(completarCampoConEspacios(venta.getFecha(), FECHA_LONGITUD));
                registro.append(completarCampoConEspacios(venta.getHora(), HORA_LONGITUD));
                registro.append(completarCampoConEspacios(venta.getMedio_de_pago(), MEDIO_PAGO_LONGITUD));
                
			
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