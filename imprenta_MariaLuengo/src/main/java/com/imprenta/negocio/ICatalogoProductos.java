package com.imprenta.negocio;

import com.imprenta.dominio.FormatoPapel;
import com.imprenta.dominio.TipoPapel;

public interface ICatalogoProductos {

    void agregarProducto(String nombreArchivo, int id_producto, String nombreProducto, FormatoPapel medida, boolean is2caras, TipoPapel papel, int gramaje, boolean isColor);

    void listarProductos(String nombreArchivo);

    void listarBusqueda(String nombreArchivo, String buscar);

    void buscarUno(String nombreArchivo, String buscar);

   // void buscarUnId(String nombreArchivo, int buscar);
    String buscarUnId(String nombreArchivo, int buscar);

    void iniciarArchivo(String nombreArchivo);

    void borrarProducto(String nombreArchivo, String buscar);

    void eliminarFichero(String nombreArchivo);

    void ordenarProductosPorGramaje(String nombreArchivo);

    void ordenarProductosPorNombre(String nombreArchivo);

    int comprobarId(String nombreArchivo);

}
