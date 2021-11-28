package com.imprenta.dominio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrdenTrabajo {

    private int id_ot;
    private int id_cliente;
    private Date fecha;
    private int cantidad;
    private int id_producto;
    private double pliegos;

    public OrdenTrabajo(int id_ot, int id_cliente, Date fecha, int cantidad, int id_producto, double pliegos) {
        this.id_ot = id_ot;
        this.id_cliente = id_cliente;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.id_producto = id_producto;
        this.pliegos = pliegos;
    }
    
    public int getId_ot() {
        return id_ot;
    }

    public void setId_ot(int id_ot) {
        this.id_ot = id_ot;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getFecha() {
        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        return df.format(fecha);
    }    

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public double getPliegos() {
        return pliegos;
    }

    public void setPliegos(double pliegos) {
        this.pliegos = pliegos;
    }

}
