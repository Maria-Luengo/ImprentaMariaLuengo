package com.imprenta.principal;

import com.imprenta.dominio.Menu;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) throws SQLException, ParseException {
        menuPrincipal();
    }

    public static void menuPrincipal() throws SQLException, ParseException {
        int opcion;
        System.out.println("\t''''''''''''''''''''''''''''''''''\n"
                + "\t           MENU PRINCIPAL"
                + "\n\t''''''''''''''''''''''''''''''''''\n"
                + "\t1. Menú CLIENTES\n"
                + "\t2. Menú PRODUCTOS\n"
                + "\t3. Menú ORDENES DE TRABAJO\n"
                + "\t0. Salir\n"
                + "Introduzca una opcion: "
        );
        do {
            Scanner sn = new Scanner(System.in);
            opcion = sn.nextInt();
            switch (opcion) {
                case 1:
                    Menu.menuClientes();
                    break;
                case 2:
                    Menu.menuProductos();
                    break;
                case 3:
                    Menu.menuOT();
                    break;
                case 0:
                    System.out.println("Hasta pronto!");
                    return;
                default:
                    System.out.println("Valor erroneo");
            }
        } while (opcion != 0);
    }

}
