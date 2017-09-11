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
    
   private String id_productos;
   private String imagen;
   private int precio;
   private String desc_coleccion;
   private String referencia_tipo;

    public Productos(String id_productos, String imagen, int precio, String desc_coleccion, String referencia_tipo) {
        this.id_productos = id_productos;
        this.imagen = imagen;
        this.precio = precio;
        this.desc_coleccion = desc_coleccion;
        this.referencia_tipo = referencia_tipo;
    }
   
   
   private List <Detalle_ventas> detalle_ventas;
   private List <Inventario> Inventario;

    public String getId_productos() {
        return id_productos;
    }

    public void setId_productos(String id_productos) {
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

    public String getDesc_coleccion() {
        return desc_coleccion;
    }

    public void setDesc_coleccion(String desc_coleccion) {
        this.desc_coleccion = desc_coleccion;
    }

    public String getReferencia_tipo() {
        return referencia_tipo;
    }

    public void setReferencia_tipo(String referencia_tipo) {
        this.referencia_tipo = referencia_tipo;
    }

    public List<Detalle_ventas> getDetalle_ventas() {
        return detalle_ventas;
    }

    public void setDetalle_ventas(List<Detalle_ventas> detalle_ventas) {
        this.detalle_ventas = detalle_ventas;
    }

    public List<Inventario> getInventario() {
        return Inventario;
    }

    public void setInventario(List<Inventario> Inventario) {
        this.Inventario = Inventario;
    }

    
    
    
}
