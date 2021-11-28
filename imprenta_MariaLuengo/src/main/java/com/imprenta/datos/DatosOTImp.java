package com.imprenta.datos;

import com.imprenta.dominio.OrdenTrabajo;
import com.imprenta.excepciones.AccesoDatosEx;
import com.imprenta.excepciones.EscrituraDatosEx;
import com.imprenta.excepciones.LecturaDatosEx;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DatosOTImp implements IDatosOT {

    @Override
    public boolean existe(String nombreArchivo) throws AccesoDatosEx {
        File archivo = new File(nombreArchivo);
        return archivo.exists();
    }

    /**
     *
     *
     *
     * Traduce texto a objeto
     *
     */
    @Override
    public List<OrdenTrabajo> listarOT(String nombreArchivo) throws LecturaDatosEx {

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        File archivo = new File(nombreArchivo);
        List<OrdenTrabajo> listaOrdenes = new ArrayList<OrdenTrabajo>();
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            var lectura = entrada.readLine();

            while (lectura != null) {
                String[] ordenes = lectura.split(";");
                OrdenTrabajo ordenesarr = new OrdenTrabajo( //crear el array con el constructor
                        Integer.parseInt(ordenes[0]),
                        Integer.parseInt(ordenes[1]),
                        formatoFecha.parse(ordenes[2]),
                        Integer.parseInt(ordenes[3]),
                        Integer.parseInt(ordenes[4]),
                        Double.parseDouble(ordenes[5]));//pliegos
                listaOrdenes.add(ordenesarr);
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
            throw new LecturaDatosEx("Error de lectura listando ordenes");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        } catch (ParseException ex) {
            ex.printStackTrace(System.out);
        }
        return listaOrdenes;
    }

    @Override
    public void escribirOrden(OrdenTrabajo ot, String nombreArchivo, boolean anexar) throws EscrituraDatosEx {
        File archivo = new File(nombreArchivo);
        String escribir = ot.getId_ot()
                + ";" + ot.getId_cliente()
                + ";" + ot.getFecha()
                + ";" + ot.getCantidad()
                + ";" + ot.getId_producto()
                + ";" + ot.getPliegos();
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
            salida.println(escribir);
            salida.close();
            System.out.println("Escrito el contenido con exito\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
            System.out.println("El archivo no existe");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void crear(String nombreArchivo) throws AccesoDatosEx {
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
            System.out.println("Se ha creado con exito el archivo");
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        }
    }

    /* @Override
    public void borrarOT(String nombreArchivo, String buscar) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

 /*  @Override
    public void borrarFichero(String nombreArchivo) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
