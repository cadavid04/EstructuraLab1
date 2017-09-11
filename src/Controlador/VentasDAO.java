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

import Modelo.Ventas;

public interface VentasDAO {
	
	public Ventas getVentas(String consecutivo_venta);
	public boolean saveVenta (Ventas ventas);
	public List<Ventas> getAllVentas();
	
}
