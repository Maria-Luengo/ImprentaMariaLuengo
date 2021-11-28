package com.imprenta.datos;

import com.imprenta.dominio.Producto;
import com.imprenta.dominio.FormatoPapel;
import com.imprenta.dominio.TipoPapel;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatosProductoImp implements IDatosProducto {

    @Override
    public boolean existe(String nombreArchivo) throws AccesoDatosEx {
        File archivo = new File(nombreArchivo);
        return archivo.exists();
    }

     /** 
     * Traduce texto a objeto
     * 
     * 
     * 
     */
    public List<Producto> listarProductos(String nombreArchivo) throws LecturaDatosEx {
        File archivo = new File(nombreArchivo);
        List<Producto> listaProd = new ArrayList<Producto>();
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            var lectura = entrada.readLine();

            while (lectura != null) {
                String[] arrProducto = lectura.split(";");
                Producto producto = new Producto(
                        Integer.parseInt(arrProducto[0]),//id
                        arrProducto[1],//nombre
                        FormatoPapel.valueOf(arrProducto[2]),//medida
                        Boolean.parseBoolean(arrProducto[3]),//is2caras
                        TipoPapel.valueOf(arrProducto[4]),//papel
                        Integer.parseInt(arrProducto[5]),//gramaje
                        Boolean.parseBoolean(arrProducto[6]));//isColor
                        //Integer.parseInt(arrProducto[7]));//cantidad
                        
                listaProd.add(producto);
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
            throw new LecturaDatosEx("Error de lectura listando productos");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return listaProd;
    }
    
    /** 
     * 
     * 
     * 
     * Traduce objeto a texto
     */

    @Override
    public void escribirProductos(Producto producto, String nombreArchivo, boolean anexar) throws EscrituraDatosEx {
        File archivo = new File(nombreArchivo);
        String escribir = producto.getId_producto()
                + ";" + producto.getNombreProducto()
                + ";" + producto.getMedida()
                + ";" + producto.isIs2caras()
                + ";" + producto.getPapel()
                + ";" + producto.getGramaje()
                + ";" + producto.isIsColor();
                //+ ";" + producto.getCantidad();
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
     public List<Producto> listarBusqueda(String nombreArchivo, String buscar) throws LecturaDatosEx {
        File archivo = new File(nombreArchivo);
        List<Producto> listaProd = new ArrayList<Producto>();
        int linea=0;
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            var lectura = entrada.readLine();

            while (lectura != null) {
                String[] productos = lectura.split(";");
                linea++;
                if (productos[1].equalsIgnoreCase(buscar)){
                    Producto producto = new Producto(
                        Integer.parseInt(productos[0]),//id
                        productos[1],//nombre
                        FormatoPapel.valueOf(productos[2]),//medida
                        Boolean.parseBoolean(productos[3]),//is2caras
                        TipoPapel.valueOf(productos[4]),//papel
                        Integer.parseInt(productos[5]),//gramaje
                        Boolean.parseBoolean(productos[6]));//isColor
                    
                        listaProd.add(producto);
                }
           
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
            throw new LecturaDatosEx("Error de lectura listando productos buscados");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return listaProd;
    }

    @Override
    public String buscarUno(String nombreArchivo, String buscar) throws LecturaDatosEx {
        File archivo = new File(nombreArchivo);
        String mensaje = "";
        int linea = 0;
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            var lectura = entrada.readLine();
            while (lectura != null) {
                String[] productos = lectura.split(";");
                linea++;
                if (productos[1].equalsIgnoreCase(buscar)) {
                    mensaje = "Archivo: " + nombreArchivo + "\n" + "Producto: " + lectura + "\n" + "Linea: " + linea;
                    //continue; //OJO!! BUCLE INFINITO
                    break;
                }
                lectura = entrada.readLine();
            }
            if (lectura == null) {
                System.out.println("El producto no esta en el catálogo");
            }
            entrada.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
            System.out.println("Error al leerlo");
        } catch (IOException e) {
            e.printStackTrace(System.out);
            System.out.println("No se ha encontrado el archivo");
        }
        return mensaje;
    }
    @Override
    public String buscarUnId(String nombreArchivo, int buscar) throws LecturaDatosEx {
        File archivo = new File(nombreArchivo);
        String mensaje = "";
        int linea = 0;
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            var lectura = entrada.readLine();
            while (lectura != null) {
                String[] productos = lectura.split(";");
                linea++;
                if (Integer.parseInt(productos[0])==buscar) {
                   mensaje =productos[2];
                    //mensaje=FormatoPapel.valueOf(productos[2]);
                    break;
                }
                lectura = entrada.readLine();
            }
            if (lectura == null) {
                System.out.println("El producto no esta en el catálogo");
            }
            entrada.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
            System.out.println("Error al leerlo");
        } catch (IOException e) {
            e.printStackTrace(System.out);
            System.out.println("No se ha encontrado el archivo");
        }
        return mensaje;
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

    @Override
    public void borrarProducto(String nombreArchivo, String borrar) {
        File archivo = new File(nombreArchivo);
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            var lectura = entrada.readLine();
            PrintWriter salida = new PrintWriter(archivo);
            while (lectura != null) {
                String[] cadenaProducto = lectura.split(";");
                if (cadenaProducto[1].equalsIgnoreCase(borrar)) {
                    lectura = entrada.readLine();
                    continue;
                }
                salida = new PrintWriter(new FileWriter(archivo, true));
                salida.println(lectura);
                salida.close();
                lectura = entrada.readLine();
            }
            if (lectura == null) {
                System.out.println("Se han eliminado las lineas");
            }
            entrada.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
            System.out.println("Error al leerlo");
        } catch (IOException e) {
            e.printStackTrace(System.out);
            System.out.println("No se ha encontrado el archivo");
        }
    }
    
    @Override
    public String borrarFichero(String nombreArchivo) {
        File fichero = new File(nombreArchivo);
        String mensaje = "";

        try {
            if (existe(nombreArchivo)) {
                fichero.delete();
                mensaje = "Fichero borrado";
            } else {
                mensaje = "El fichero no existe";
            }
        } catch (AccesoDatosEx ex) {
            Logger.getLogger(DatosProductoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;
    }

}
