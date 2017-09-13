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
public class Colecciones {
    private String desc_coleccion;
    private String ano_trimestre;
    private String exclusividad;

    public Colecciones(String desc_coleccion, String ano_trimestre, String exclusividad) {
        this.desc_coleccion = desc_coleccion;
        this.ano_trimestre = ano_trimestre;
        this.exclusividad = exclusividad;
    }
    
    private List <Productos> productos;

    public String getDesc_coleccion() {
        return desc_coleccion;
    }

    public void setDesc_coleccion(String desc_coleccion) {
        this.desc_coleccion = desc_coleccion;
    }

    public String getAno_trimestre() {
        return ano_trimestre;
    }

    public void setAno_trimestre(String ano_trimestre) {
        this.ano_trimestre = ano_trimestre;
    }

    public String getExclusividad() {
        return exclusividad;
    }

    public void setExclusividad(String exclusividad) {
        this.exclusividad = exclusividad;
    }

    public List <Productos> getProductos() {
        return productos;
    }

    public void setProductos(List <Productos> productos) {
        this.productos = productos;
    }
    
}
