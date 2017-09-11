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
public class Ventas {
    
    private int consecutivo_venta;
    private int valor_bruto;
    private int descuentos;
    private int valor_neto;
    private String fecha;
    private String hora;
    private String medio_de_pago;
    
    private String id_cajero;
    private String id_cliente;
    
    private List <Detalle_ventas> detalle_ventas;

    public Ventas() {
    }

    public Ventas(int consecutivo_venta, String id_cliente,  String id_cajero, int valor_bruto, int descuentos, int valor_neto, String fecha, String hora, String medio_de_pago) {
        this.consecutivo_venta = consecutivo_venta;
        this.valor_bruto = valor_bruto;
        this.descuentos = descuentos;
        this.valor_neto = valor_neto;
        this.fecha = fecha;
        this.hora = hora;
        this.medio_de_pago = medio_de_pago;
        this.id_cajero = id_cajero;
        this.id_cliente = id_cliente;
    }

    public int getConsecutivo_venta() {
        return consecutivo_venta;
    }

    public void setConsecutivo_venta(int consecutivo_venta) {
        this.consecutivo_venta = consecutivo_venta;
    }

    public int getValor_bruto() {
        return valor_bruto;
    }

    public void setValor_bruto(int valor_bruto) {
        this.valor_bruto = valor_bruto;
    }

    public int getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(int descuentos) {
        this.descuentos = descuentos;
    }

    public int getValor_neto() {
        return valor_neto;
    }

    public void setValor_neto(int valor_neto) {
        this.valor_neto = valor_neto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMedio_de_pago() {
        return medio_de_pago;
    }

    public void setMedio_de_pago(String medio_de_pago) {
        this.medio_de_pago = medio_de_pago;
    }

    public String getId_cajero() {
        return id_cajero;
    }

    public void setId_cajero(String id_cajero) {
        this.id_cajero = id_cajero;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public List<Detalle_ventas> getDetalle_ventas() {
        return detalle_ventas;
    }

    public void setDetalle_ventas(List<Detalle_ventas> detalle_ventas) {
        this.detalle_ventas = detalle_ventas;
    }

    

}
