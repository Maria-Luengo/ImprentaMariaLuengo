package com.imprenta.negocio;

import com.imprenta.datos.DatosProductoImp;
import com.imprenta.datos.IDatosProducto;
import com.imprenta.dominio.Producto;
import com.imprenta.dominio.FormatoPapel;
import com.imprenta.dominio.TipoPapel;
import com.imprenta.excepciones.AccesoDatosEx;
import com.imprenta.excepciones.EscrituraDatosEx;
import com.imprenta.excepciones.LecturaDatosEx;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CatalogoProductosImp implements ICatalogoProductos {

    private final IDatosProducto datos;//Puente de comunicacion

    public CatalogoProductosImp() {
        this.datos = new DatosProductoImp();
    }

    @Override
    public void agregarProducto(String nombreArchivo, int id_producto, String nombreProducto, FormatoPapel medida, boolean is2caras, TipoPapel papel, int gramaje, boolean isColor) {
        try {
            Producto productos = new Producto(id_producto, nombreProducto, medida, is2caras, papel, gramaje, isColor);
            if (datos.existe(nombreArchivo) == true) {
                datos.escribirProductos(productos, nombreArchivo, true);
            } else {
                System.out.println("El archivo no existe");
            }
        } catch (EscrituraDatosEx ex) {
            ex.printStackTrace(System.out);
        } catch (AccesoDatosEx ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void listarProductos(String nombreArchivo) {
        List<Producto> lista = new ArrayList<>();
        try {
            if (datos.existe(nombreArchivo) == true) {
                lista = datos.listarProductos(nombreArchivo);
                lista.forEach(producto -> {
                    System.out.println("Producto: "
                            + producto.getId_producto()
                            + " | " + producto.getNombreProducto()
                            + " | " + producto.getMedida() 
                            + " | " + producto.isIs2caras()
                            + " | " + producto.getPapel()
                            + " | " + producto.getGramaje()
                            + " | " + producto.isIsColor());
                });
            } else {
                System.out.println("El archivo no existe");
            }

        } catch (LecturaDatosEx e) {
            System.out.println("Error leyendo el catalogo");
            e.printStackTrace(System.out);
        } catch (AccesoDatosEx ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void listarBusqueda(String nombreArchivo, String buscar) {
        List<Producto> lista = new ArrayList<>();
        try {
            if (datos.existe(nombreArchivo) == true) {
                lista = datos.listarBusqueda(nombreArchivo, buscar);
                lista.forEach(producto -> {
                    System.out.println("Producto: "
                            + producto.getId_producto()
                            + " | " + producto.getNombreProducto()
                            + " | " + producto.getMedida() 
                            + " | " + producto.isIs2caras()
                            + " | " + producto.getPapel()
                            + " | " + producto.getGramaje()
                            + " | " + producto.isIsColor());
                });
            } else {
                System.out.println("El archivo no existe");
            }

        } catch (LecturaDatosEx e) {
            System.out.println("Error leyendo el catalogo");
            e.printStackTrace(System.out);
        } catch (AccesoDatosEx ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void buscarUno(String nombreArchivo, String buscar) {
        try {
            System.out.println(datos.buscarUno(nombreArchivo, buscar));
        } catch (LecturaDatosEx e) {
            e.printStackTrace(System.out);
        }
    }
    @Override
    public String buscarUnId(String nombreArchivo, int buscar) {
        try {
            return datos.buscarUnId(nombreArchivo, buscar);
        } catch (LecturaDatosEx ex) {
            Logger.getLogger(CatalogoProductosImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void iniciarArchivo(String nombreArchivo) {
        try {
            if (datos.existe(nombreArchivo) == false) {
                datos.crear(nombreArchivo);
            } else {
                System.out.println("El archivo ya existe");
            }

        } catch (AccesoDatosEx e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void borrarProducto(String nombreArchivo, String borrar) {
        datos.borrarProducto(nombreArchivo, borrar);
    }

    @Override
    public void eliminarFichero(String nombreArchivo) {
        datos.borrarFichero(nombreArchivo);
        System.out.println("Fichero eliminado con exito");
    }

    @Override
    public void ordenarProductosPorGramaje(String nombreArchivo) {
        List<Producto> lista = new ArrayList<>();
        try {
            lista = datos.listarProductos(nombreArchivo);
            lista.sort((Producto nombre_1, Producto nombre_2) -> {
                return nombre_1.getGramaje() - nombre_2.getGramaje();
            });
            lista.forEach(producto -> {
                System.out.println("Producto: "
                        + producto.getId_producto()
                        + " | " + producto.getNombreProducto()
                        + " | " + producto.getMedida()
                        + " | " + producto.isIs2caras()
                        + " | " + producto.getPapel()
                        + " | " + producto.getGramaje()
                        + " | " + producto.isIsColor());
            });

        } catch (LecturaDatosEx e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void ordenarProductosPorNombre(String nombreArchivo) {
        List<Producto> lista = new ArrayList<>();
        try {
            lista = datos.listarProductos(nombreArchivo);
            lista.sort((Producto p1, Producto p2) -> {
                return p1.getNombreProducto().compareToIgnoreCase(p2.getNombreProducto());
            });
            lista.forEach(producto -> {
                System.out.println("Producto: "
                        + producto.getId_producto()
                        + " | " + producto.getNombreProducto()
                        + " | " + producto.getMedida()
                        + " | " + producto.isIs2caras()
                        + " | " + producto.getPapel()
                        + " | " + producto.getGramaje()
                        + " | " + producto.isIsColor());
            });

        } catch (LecturaDatosEx e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public int comprobarId(String nombreArchivo) {
        List<Producto> lista = new ArrayList<>();
        int id = 1;
        try {
            if (datos.existe(nombreArchivo) == true) {
                lista = datos.listarProductos(nombreArchivo);
                for (int i = 0; i < lista.size(); i++) {
                    id++;
                }
            } else {
                System.out.println("No hay productos en el fichero");
            }

        } catch (LecturaDatosEx e) {
            System.out.println("Error leyendo el catalogo");
            e.printStackTrace(System.out);
        } catch (AccesoDatosEx ex) {
            ex.printStackTrace(System.out);
        }
        return id;
    }

}
