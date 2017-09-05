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
    private int id_detalle;
    private String fecha;
    private String hora;
    private String medio_de_pago;
    
    private Cajeros id_cajero;
    private Clientes id_cliente;
    
    private List <Detalle_ventas> detalle_ventas;


}
