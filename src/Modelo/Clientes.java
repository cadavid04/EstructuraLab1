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
public class Clientes {
    
    private int id_cliente;
    private String nombres;
    private String apellidos;
    private String correo_e;
    private int celular;
    private String fecha_nacimiento;
    private String direccion;
    private List <Ventas> ventas;

    public Clientes() {
    }

    public Clientes(int id_cliente, String nombres, String apellidos, String correo_e, int celular, String fecha_nacimiento, String direccion) {
        this.id_cliente = id_cliente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo_e = correo_e;
        this.celular = celular;
        this.fecha_nacimiento = fecha_nacimiento;
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public List <Ventas> getVentas() {
        return ventas;
    }

    public void setVentas(List <Ventas> ventas) {
        this.ventas = ventas;
    }
    
}
