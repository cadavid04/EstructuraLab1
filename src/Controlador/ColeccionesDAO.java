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

import Modelo.Colecciones;

public interface ColeccionesDAO {
	
	public Colecciones getColecciones(String ref_colecciones);
	public boolean saveColecciones (Colecciones c_colecciones);
	public List<Colecciones> getAllColecciones();
	
}
