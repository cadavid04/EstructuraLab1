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

import Modelo.Almacenes;

public interface AlmacenesDAO {
	
	public Almacenes getAlmacenes(String id_producto);
	public boolean saveAlmacenes (Almacenes producto);
	public List<Almacenes> getAllAlmacenes();
	
}
