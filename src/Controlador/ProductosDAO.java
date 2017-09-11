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
import java.util.List;

import Modelo.Productos;

public interface ProductosDAO {
	
	public Productos getProductos(String id_producto);
	public boolean saveProductos (Productos producto);
	public List<Productos> getAllProductos();
	
}
