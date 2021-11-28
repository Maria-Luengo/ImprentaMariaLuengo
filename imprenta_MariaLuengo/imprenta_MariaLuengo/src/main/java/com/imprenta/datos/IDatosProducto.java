package com.imprenta.datos;

import com.imprenta.dominio.ClienteDTO;
import com.imprenta.dominio.Producto;
import com.imprenta.excepciones.AccesoDatosEx;
import com.imprenta.excepciones.EscrituraDatosEx;
import com.imprenta.excepciones.LecturaDatosEx;
import java.util.List;

public interface IDatosProducto {

    boolean existe(String nombreArchivo) throws AccesoDatosEx;

    List<Producto> listarProductos(String nombreArchivo) throws LecturaDatosEx;

    void escribirProductos(Producto producto, String nombreArchivo, boolean anexar) throws EscrituraDatosEx;

    List<Producto> listarBusqueda(String nombreArchivo, String buscar) throws LecturaDatosEx;

    String buscarUno(String nombreArchivo, String buscar) throws LecturaDatosEx;

    String buscarUnId(String nombreArchivo, int buscar) throws LecturaDatosEx;

    void crear(String nombreArchivo) throws AccesoDatosEx;

    void borrarProducto(String nombreArchivo, String buscar);

    String borrarFichero(String nombreArchivo);

}
