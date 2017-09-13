/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author carloscarrascal
 */
public class Inventario {
    
    private int cantidad;

    public Inventario(int cantidad, Productos id_producto, Almacenes codigo_almacen) {
        this.cantidad = cantidad;
        this.id_producto = id_producto;
        this.codigo_almacen = codigo_almacen;
    }
    private Productos id_producto;
    private Almacenes codigo_almacen;
}
