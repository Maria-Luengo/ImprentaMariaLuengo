package com.imprenta.dominio;

import com.imprenta.datos.ClienteDAOJDBCimp;
import com.imprenta.negocio.CatalogoOrdenesImp;
import com.imprenta.negocio.CatalogoProductosImp;
import com.imprenta.negocio.ICatalogoOrdenes;
import com.imprenta.negocio.ICatalogoProductos;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class IntroducirPorTeclado {

    public static void producto() {
        ICatalogoProductos datosProductos1 = new CatalogoProductosImp();
        String nombreFichero = "productos.txt", optionBoolean = "";
        int id, gramaje;
        String nombreProd;
        FormatoPapel formato;
        TipoPapel papel;

        boolean caras, color;
        Scanner sn = new Scanner(System.in);
        Scanner ent = new Scanner(System.in);

        System.out.println("Introduce datos del producto:");
        System.out.println(">>Nombre producto:");
        nombreProd = sn.next();

        System.out.println("Elija el formato:");
        formato = MenusSeleeccionarEnm.SeleccionarTamanioPapel();

        System.out.println(">>¿Impreso a doble cara? (s ó n)");
        optionBoolean = ent.nextLine();
        switch (optionBoolean) {
            case "s":
                caras = true;
                break;
            case "n":
                caras = false;
                break;
            default:
                System.out.println("No te he entendido");
                return;
        }
        System.out.println(">>Papel:");
        papel = MenusSeleeccionarEnm.SeleccionarTipoPapel();

        System.out.println(">>Gramaje:");
        gramaje = sn.nextInt();

        System.out.println("¿Es a color? (s o n)");
        optionBoolean = ent.nextLine();
        switch (optionBoolean) {
            case "s":
                color = true;
                break;
            case "n":
                color = false;
                break;
            default:
                System.out.println("No te he entendido");
                return;
        }
        id = datosProductos1.comprobarId(nombreFichero);
        datosProductos1.agregarProducto(nombreFichero, id, nombreProd, formato, caras, papel, gramaje, color);
    }

    public static void cliente() throws SQLException {
        Connection conexion = null;
        ClienteDAOJDBCimp clienteDao = new ClienteDAOJDBCimp(conexion);
        Scanner sn = new Scanner(System.in);
        Scanner ent = new Scanner(System.in);
        String nombre,
                cif,
                direccion,
                telefono;

        System.out.println("Introduce los datos del cliente :) ");
        System.out.println(">>Nombre Cliente:");
        nombre = sn.nextLine();

        System.out.println(">>CIF:");
        cif = ent.nextLine();

        System.out.println(">>Dirección:");
        direccion = sn.nextLine();

        System.out.println(">>Teléfono:");
        telefono = ent.next();

        clienteDao.insertar(new ClienteDTO(nombre, cif, direccion, telefono));
    }

    public static void menuActualizarCliente(int id) throws SQLException {
        Connection conexion = null;
        ClienteDAOJDBCimp clienteDao = new ClienteDAOJDBCimp(conexion);
        String nombre,
                cif,
                direccion,
                telefono;
        int opcion;
        Scanner sn = new Scanner(System.in);
        Scanner ent = new Scanner(System.in);
        System.out.println("\t¿Qué campo deseas modificar?\n"
                + "\t1.- Todos\n"
                + "\t2.- Solo nombre\n"
                + "\t3.- Solo cif\n"
                + "\t4.- Solo direccion\n"
                + "\t5.- Solo teléfono\n"
                + "Introduzca una opcion: ");
        opcion = sn.nextInt();
        switch (opcion) {
            case 1:
                System.out.println(">>Nuevo nombre:");
                nombre = ent.nextLine();
                System.out.println(">>Nuevo CIF:");
                cif = ent.next();
                System.out.println(">>Nueva dirección:");
                direccion = ent.next();
                System.out.println(">>Nuevo teléfono:");
                telefono = sn.next();
                clienteDao.actualizar(new ClienteDTO(id, nombre, cif, direccion, telefono));
                break;
            case 2:
                System.out.println(">>Nuevo nombre: ");
                nombre = sn.next();
                clienteDao.actualizarNombre(nombre, id);
                break;
            case 3:
                System.out.println(">>Nuevo CIF:");
                cif = sn.next();
                clienteDao.actualizarCif(cif, id);
                break;
            case 4:
                System.out.println(">>Nueva dirección:");
                direccion = ent.nextLine();
                clienteDao.actualizarDireccion(direccion, id);
                break;
            case 5:
                System.out.println(">>Nuevo teléfono:");
                telefono = sn.next();
                clienteDao.actualizarTelefono(telefono, id);
                break;
            default:
                System.out.println("Introduce un número de 1 a 5");
                return;
        }
    }

    public static void ordenTrabajo(String nombreArchivo) throws ParseException, SQLException {
        ICatalogoOrdenes datosOrdenes = new CatalogoOrdenesImp();
        ICatalogoProductos datosProductos = new CatalogoProductosImp();
        Connection conexion = null;
        ClienteDAOJDBCimp clienteDao = new ClienteDAOJDBCimp(conexion);

        Scanner sn = new Scanner(System.in);
        Scanner ent = new Scanner(System.in);
        int id_ot, id_cliente, id_prod, cantidad;
        String formato;
        double pliegos = 1;

        //Lista de clientes
        System.out.println("\t''''''Listado de clientes'''''' ");
        List<ClienteDTO> clientes = clienteDao.seleccionar();
        clientes.forEach(cliente -> {
            System.out.println(cliente);
        });
        System.out.println(">>Indica el id del cliente que va a realizar el pedido: ");
        id_cliente = sn.nextInt();

        System.out.println(">>Introduce la fecha (dd/mm/yyyy): ");
        String fechaString = ent.nextLine();
        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        Date fecha = df.parse(fechaString);

        System.out.println(">>Indica cantidad de ejemplares: ");
        cantidad = sn.nextInt();

        System.out.println("Listado de productos: ");
        datosProductos.listarProductos("productos.txt");
        System.out.println(">>Indica el id del producto: ");
        id_prod = sn.nextInt();

        formato = datosProductos.buscarUnId("productos.txt", id_prod);

        switch (formato) {
            case "A3":
                pliegos = cantidad;
                break;
            case "A4":
                pliegos = cantidad / 2;
                break;
            case "A5":
                pliegos = cantidad / 4;
                break;
            case "A6":
                pliegos = cantidad / 8;
                break;
            case "TARJETA":
                pliegos = cantidad / 20;
                break;
        }

        id_ot = datosOrdenes.comprobarId(nombreArchivo);
        datosOrdenes.agregarOT(nombreArchivo, id_ot, id_cliente, fecha, cantidad, id_prod, pliegos);
    }
}
