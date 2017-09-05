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
public class Cajeros {
    
    private int id_cajero;
    private String nombre;
    private String apellido;
    private String correo_e;
    private int celular;
    private String genero;
    private String fecha_ingreso;
    private String contraseña;
    private String estado;
    private List <Ventas> ventas;

    public int getId_cajero() {
        return id_cajero;
    }

    public Cajeros(int id_cajero, String nombre, String apellido, String correo_e, int celular, String genero, String fecha_ingreso, String contraseña, String estado) {
        this.id_cajero = id_cajero;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo_e = correo_e;
        this.celular = celular;
        this.genero = genero;
        this.fecha_ingreso = fecha_ingreso;
        this.contraseña = contraseña;
        this.estado = estado;
    }

    public void setId_cajero(int id_cajero) {
        this.id_cajero = id_cajero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo_e() {
        return correo_e;
    }

    public void setCorreo_e(String correo_e) {
        this.correo_e = correo_e;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List <Ventas> getVentas() {
        return ventas;
    }

    public void setVentas(List <Ventas> ventas) {
        this.ventas = ventas;
    }
    
}
