/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.List;

/**
 *
 * @author carloscarrascal
 */
public class Productos {
    
   int id_productos;
   String imagen;
   int precio;
   private Colecciones desc_coleccion;
   private Tipo_productos referencia_tipo;
   
   
   private List <Detalle_ventas> detalle_ventas;
   private List <Inventario> Inventario;
   
    
    
}
