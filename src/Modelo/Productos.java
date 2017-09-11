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
    
   private int id_productos;
   private String imagen;
   private int precio;
   private Colecciones desc_coleccion;
   private Tipo_productos referencia_tipo;
   
   
   private List <Detalle_ventas> detalle_ventas;
   private List <Inventario> Inventario;

    public Productos(int id_productos, int precio,  referencia_tipo) {
        this.id_productos = id_productos;
        this.precio = precio;
        this.referencia_tipo = referencia_tipo;
    }

    public int getId_productos() {
        return id_productos;
    }

    public void setId_productos(int id_productos) {
        this.id_productos = id_productos;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Colecciones getDesc_coleccion() {
        return desc_coleccion;
    }

    public void setDesc_coleccion(Colecciones desc_coleccion) {
        this.desc_coleccion = desc_coleccion;
    }

    public Tipo_productos getReferencia_tipo() {
        return referencia_tipo;
    }

    public void setReferencia_tipo(Tipo_productos referencia_tipo) {
        this.referencia_tipo = referencia_tipo;
    }

    public List <Detalle_ventas> getDetalle_ventas() {
        return detalle_ventas;
    }

    public void setDetalle_ventas(List <Detalle_ventas> detalle_ventas) {
        this.detalle_ventas = detalle_ventas;
    }

    public List <Inventario> getInventario() {
        return Inventario;
    }

    public void setInventario(List <Inventario> Inventario) {
        this.Inventario = Inventario;
    }
   
    
    
}
