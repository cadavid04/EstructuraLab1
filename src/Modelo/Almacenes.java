/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.List;

/*
 *
 * @author carloscarrascal
 */
public class Almacenes {
    
     private String codigo_almacen;
     private String ciudad;

    public Almacenes(String codigo_almacen, String ciudad, String direccion, String horario) {
        this.codigo_almacen = codigo_almacen;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.horario = horario;
    }
     private String direccion;
     private String horario;
     
     private List <Inventario> Inventario;

    public String getCodigo_almacen() {
        return codigo_almacen;
    }

    public void setCodigo_almacen(String codigo_almacen) {
        this.codigo_almacen = codigo_almacen;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public List <Inventario> getInventario() {
        return Inventario;
    }

    public void setInventario(List <Inventario> Inventario) {
        this.Inventario = Inventario;
    }
     
}
