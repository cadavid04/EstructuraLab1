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
public class Tipo_productos {
    
    private String referencia_tipo;
    private String nombre;

    public Tipo_productos(String referencia_tipo, String nombre) {
        this.referencia_tipo = referencia_tipo;
        this.nombre = nombre;
    }

    private List <Productos> productos;
    
    public Tipo_productos() {
    }

    public String getReferencia_tipo() {
        return referencia_tipo;
    }

    public void setReferencia_tipo(String referencia_tipo) {
        this.referencia_tipo = referencia_tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List <Productos> getProductos() {
        return productos;
    }

    public void setProductos(List <Productos> productos) {
        this.productos = productos;
    }
}
