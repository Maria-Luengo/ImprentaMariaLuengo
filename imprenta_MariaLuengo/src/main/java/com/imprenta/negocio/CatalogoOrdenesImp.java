package com.imprenta.negocio;

import com.imprenta.datos.DatosOTImp;
import com.imprenta.datos.IDatosOT;
import com.imprenta.dominio.OrdenTrabajo;
import com.imprenta.excepciones.AccesoDatosEx;
import com.imprenta.excepciones.EscrituraDatosEx;
import com.imprenta.excepciones.LecturaDatosEx;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CatalogoOrdenesImp implements ICatalogoOrdenes {

    private final IDatosOT datosOt;//Puente de comunicacion

    public CatalogoOrdenesImp() {
        this.datosOt = new DatosOTImp();
    }

    @Override
    public void iniciarArchivo(String nombreArchivo) {
        try {
            if (datosOt.existe(nombreArchivo) == false) {
                datosOt.crear(nombreArchivo);
            } else {
                System.out.println("El archivo ya existe");
            }

        } catch (AccesoDatosEx e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void agregarOT(String nombreArchivo, int id_ot, int id_cliente, Date fecha, int cantidad, int id_producto, double pliegos) {
        try {
            OrdenTrabajo orden = new OrdenTrabajo(id_ot, id_cliente, fecha, cantidad, id_producto, pliegos);
            if (datosOt.existe(nombreArchivo) == true) {
                datosOt.escribirOrden(orden, nombreArchivo, true);
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
    public void listarOT(String nombreArchivo) {
        List<OrdenTrabajo> lista = new ArrayList<>();
        try {
            if (datosOt.existe(nombreArchivo) == true) {
                lista = datosOt.listarOT(nombreArchivo);
                lista.forEach(ot -> {
                    System.out.println("OT: "
                            + ot.getId_ot()
                            + " | id Cliente: " + ot.getId_cliente()
                            + " | Fecha: " + ot.getFecha()
                            + " | Cantidad: " + ot.getCantidad()
                            + " | id Producto: " + ot.getId_producto()
                            + " | Pliegos: " + ot.getPliegos());
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
    public int comprobarId(String nombreArchivo) {
        List<OrdenTrabajo> lista = new ArrayList<>();
        int id = 1;
        try {
            if (datosOt.existe(nombreArchivo) == true) {
                lista = datosOt.listarOT(nombreArchivo);
                for (int i = 0; i < lista.size(); i++) {
                    id++;
                }
            } else {
                System.out.println("No hay ordenes en el fichero");
            }

        } catch (LecturaDatosEx e) {
            System.out.println("Error leyendo el catalogo de ordenes de trabajo");
            e.printStackTrace(System.out);
        } catch (AccesoDatosEx ex) {
            ex.printStackTrace(System.out);
        }
        return id;
    }
}
