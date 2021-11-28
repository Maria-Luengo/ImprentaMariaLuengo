package com.imprenta.datos;

import com.imprenta.dominio.OrdenTrabajo;
import com.imprenta.excepciones.AccesoDatosEx;
import com.imprenta.excepciones.EscrituraDatosEx;
import com.imprenta.excepciones.LecturaDatosEx;
import java.util.List;

public interface IDatosOT {

    boolean existe(String nombreArchivo) throws AccesoDatosEx;

    List<OrdenTrabajo> listarOT(String nombreArchivo) throws LecturaDatosEx;

    void escribirOrden(OrdenTrabajo ot, String nombreArchivo, boolean anexar) throws EscrituraDatosEx;

    void crear(String nombreArchivo) throws AccesoDatosEx;

    //void borrarOT(String nombreArchivo, String buscar);

    //void borrarFichero(String nombreArchivo);
    
}
