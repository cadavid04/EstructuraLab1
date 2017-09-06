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

import Modelo.Tipo_productos;

public interface Tipo_productosDAO {
	
	public Tipo_productos getTipo_productos(String ref_tipo);
	public boolean saveTipo_productos (Tipo_productos t_productos);
	public List<Tipo_productos> getAllTipo_producto();
	
}
