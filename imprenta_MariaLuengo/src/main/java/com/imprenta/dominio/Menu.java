package com.imprenta.dominio;

import com.imprenta.datos.ClienteDAOJDBCimp;
import com.imprenta.datos.Conexion;
import com.imprenta.negocio.CatalogoProductosImp;
import com.imprenta.negocio.ICatalogoProductos;
import static com.imprenta.principal.Principal.menuPrincipal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import com.imprenta.negocio.CatalogoOrdenesImp;
import com.imprenta.negocio.ICatalogoOrdenes;
import java.text.ParseException;

public class Menu {

    public static void menuClientes() throws SQLException, ParseException {
        Connection conexion = null;
        ClienteDAOJDBCimp clienteDao = new ClienteDAOJDBCimp(conexion);
        String nombre;
        Scanner sn = new Scanner(System.in);
        int opcion = -1, id;
        while (opcion != 0) {
            System.out.println("\t**********************************\n"
                    + "\t           MENU CLIENTES"
                    + "\n\t**********************************\n"
                    + "\t1. Agregar cliente\n"
                    + "\t2. Listar clientes\n"
                    + "\t3. Modificar datos cliente\n"
                    + "\t4. Ordenar clientes por nombre\n"
                    + "\t5. Eliminar cliente\n"
                    + "\t6. Buscar cliente\n"
                    + "\t0. Volver al MENÚ PRINCIPAL\n"
                    + "Introduzca una opcion: ");

            opcion = sn.nextInt();
            switch (opcion) {
                case 1:
                    IntroducirPorTeclado.cliente();
                    break;
                case 2:
                    try {
                    conexion = Conexion.getConnection();
                    if (conexion.getAutoCommit()) {
                        conexion.setAutoCommit(false);
                    }
                    List<ClienteDTO> clientes = clienteDao.seleccionar();
                    clientes.forEach(cliente -> {
                        System.out.println(cliente);
                    });
                    conexion.commit();
                } catch (SQLException ex) {
                    ex.printStackTrace(System.out);
                    //System.out.println("Entramos en el rollback");
                    try {
                        conexion.rollback();
                    } catch (SQLException e) {
                        e.printStackTrace(System.out);
                    }
                }
                break;
                case 3:
                    System.out.println(">>Introuce id del cliente a modificar: ");
                    id = sn.nextInt();
                    try {
                        conexion = Conexion.getConnection();
                        if (conexion.getAutoCommit()) {
                            conexion.setAutoCommit(false);
                        }
                        List<ClienteDTO> clientes = clienteDao.seleccionarID(id);
                        clientes.forEach(cliente -> {
                            System.out.println(cliente);
                        });
                        conexion.commit();
                    } catch (SQLException ex) {
                        ex.printStackTrace(System.out);
                        //System.out.println("Entramos en el rollback");
                        try {
                            conexion.rollback();
                        } catch (SQLException e) {
                            e.printStackTrace(System.out);
                        }
                    }
                    IntroducirPorTeclado.menuActualizarCliente(id);
                    break;
                case 4:
                     try {
                    conexion = Conexion.getConnection();
                    if (conexion.getAutoCommit()) {
                        conexion.setAutoCommit(false);
                    }
                    List<ClienteDTO> clientes = clienteDao.ordenarASC();
                    clientes.forEach(cliente -> {
                        System.out.println(cliente);
                    });
                    conexion.commit();
                } catch (SQLException ex) {
                    ex.printStackTrace(System.out);
                    //System.out.println("Entramos en el rollback");
                    try {
                        conexion.rollback();
                    } catch (SQLException e) {
                        e.printStackTrace(System.out);
                    }
                }
                break;
                case 5:
                    System.out.println(">>Introuce el id del cliente a eliminar: ");
                    id = sn.nextInt();
                    clienteDao.borrar(id);
                    System.out.println(">>Cliente eliminado con éxito");
                    break;
                case 6:
                    System.out.println(">>Introuce el nombre a buscar: ");
                    nombre = sn.next();
                    try {
                        conexion = Conexion.getConnection();
                        if (conexion.getAutoCommit()) {
                            conexion.setAutoCommit(false);
                        }
                        List<ClienteDTO> clientes = clienteDao.buscar(nombre);
                        clientes.forEach(cliente -> {
                            System.out.println(cliente);
                        });
                        conexion.commit();
                    } catch (SQLException ex) {
                        ex.printStackTrace(System.out);
                        //System.out.println("Entramos en el rollback");
                        try {
                            conexion.rollback();
                        } catch (SQLException e) {
                            e.printStackTrace(System.out);
                        }
                    }
                    /*System.out.println("Introuce el nombre a buscar: ");
                    nombre = sn.next();
                    clienteDao.buscar(nombre);*/
                    break;
                case 0:
                    menuPrincipal();
                    return;
                default:
                    System.out.println("Valor erroneo");

            }
        }
    }

    public static void menuProductos() throws SQLException, ParseException {
        ICatalogoProductos datosProductos = new CatalogoProductosImp();
        int opcion = -1, busquedaID;
        Scanner sn = new Scanner(System.in);
        String nombreFichero = "productos.txt", busqueda;
        while (opcion != 0) {
            System.out.println("\t//////////////////////////////////\n"
                    + "\t            MENU PRODUCTOS"
                    + " \n\t//////////////////////////////////\n"
                    + "\t 1. Crear Archivo de productos\n"
                    + "\t 2. Agregar productos auto\n"
                    + "\t 3. Mostar productos\n"
                    + "\t 4. Buscar un producto\n"
                    + "\t 5. Eliminar prodcucto\n"
                    + "\t 6. Agregar producto por teclado\n"
                    + "\t 7. Ordenar por gramaje\n"
                    + "\t 8. Ordenar por nombre\n"
                    + "\t 9. Buscar todos los productos de..\n"
                    + "\t 10. Eliminar fichero\n"
                    //+ "\t 11. Buscar un producto por ID\n"
                    + "\t 0. Volver al MENÚ PRINCIPAL\n"
                    + "Introduzca una opcion: ");
            opcion = sn.nextInt();
            switch (opcion) {
                case 1:
                    datosProductos.iniciarArchivo(nombreFichero);
                    break;
                case 2:
                    datosProductos.agregarProducto(nombreFichero, 1, "carteles", FormatoPapel.A3, false, TipoPapel.OFFSET, 90, true);
                    datosProductos.agregarProducto(nombreFichero, 2, "folletos", FormatoPapel.A3, true, TipoPapel.MATE, 150, true);
                    datosProductos.agregarProducto(nombreFichero, 3, "triptico", FormatoPapel.A4, true, TipoPapel.BRILLO, 170, true);
                    datosProductos.agregarProducto(nombreFichero, 4, "tarjetas", FormatoPapel.TARJETA, false, TipoPapel.MATE, 350, true);
                    datosProductos.agregarProducto(nombreFichero, 5, "carpetas", FormatoPapel.A5, false, TipoPapel.MATE, 300, true);
                    datosProductos.agregarProducto(nombreFichero, 6, "flyer", FormatoPapel.A6, false, TipoPapel.RECICLADO, 100, false);
                    datosProductos.agregarProducto(nombreFichero, 7, "tarjetas", FormatoPapel.TARJETA, false, TipoPapel.VERJURADO, 280, true);
                    break;
                case 3:
                    datosProductos.listarProductos(nombreFichero);
                    break;
                case 4:
                    System.out.println(">>Introduce el nombre del producto a buscar:");
                    busqueda = sn.next();
                    datosProductos.buscarUno(nombreFichero, busqueda);
                    break;
                case 5:
                    System.out.println(">>Introduce el nombre del producto a eliminar:");
                    String borrar = sn.next();
                    datosProductos.borrarProducto(nombreFichero, borrar);
                    break;
                case 6:
                    IntroducirPorTeclado.producto();
                    break;
                case 7:
                    datosProductos.ordenarProductosPorGramaje(nombreFichero);
                    break;
                case 8:
                    datosProductos.ordenarProductosPorNombre(nombreFichero);
                    break;
                case 9:
                    System.out.println(">>Introduce el nombre del producto a buscar:");
                    busqueda = sn.next();
                    datosProductos.listarBusqueda(nombreFichero, busqueda);
                    break;
                case 10:
                    datosProductos.eliminarFichero(nombreFichero);
                    break;
                /*case 11:
                    System.out.println(">>Introduce el ID del producto a buscar:");
                    busquedaID = sn.nextInt();
                    datosProductos.buscarUnId(nombreFichero, busquedaID);
                    break;*/
                case 0:
                    menuPrincipal();
                    return;
                default:
                    System.out.println("Valor erroneo");
            }
        }
    }

    public static void menuOT() throws ParseException, SQLException {
        ICatalogoOrdenes datosOrdenes = new CatalogoOrdenesImp();
        int opcion = -1;
        Scanner sn = new Scanner(System.in);
        String nombreArchivo = "ordenes.txt";
        while (opcion != 0) {
            System.out.println("\t||||||||||||||||||||||||||||||||||\n"
                    + "\t      MENU ORDENES TRABAJO"
                    + " \n\t||||||||||||||||||||||||||||||||||\n"
                    + "\t 1. Iniciar Archivo\n"
                    + "\t 2. Crear OT\n"
                    + "\t 3. Listar OT\n"
                    + "\t 0. Volver al MENÚ PRINCIPAL\n"
                    + "Introduzca una opcion: ");
            opcion = sn.nextInt();
            switch (opcion) {
                case 1:
                    datosOrdenes.iniciarArchivo(nombreArchivo);
                    break;
                case 2:
                    System.out.println("Has seleccionado crear orden");
                    IntroducirPorTeclado.ordenTrabajo(nombreArchivo);
                    break;
                case 3:
                    datosOrdenes.listarOT(nombreArchivo);
                    break;
                case 0:
                    menuPrincipal();
                    return;
                default:
                    System.out.println("Valor erroneo");
            }
        }
    }
}
