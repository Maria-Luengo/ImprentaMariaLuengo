package com.imprenta.negocio;

import java.util.Date;

public interface ICatalogoOrdenes {

    void iniciarArchivo(String nombreArchivo);

    public void agregarOT(String nombreArchivo, int id_ot, int id_cliente, Date fecha, int cantidad, int id_producto, double pliegos);

    void listarOT(String nombreArchivo);

    int comprobarId(String nombreArchivo);
}
