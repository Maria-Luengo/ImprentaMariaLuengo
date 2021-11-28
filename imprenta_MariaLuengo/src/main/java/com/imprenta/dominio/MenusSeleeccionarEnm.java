package com.imprenta.dominio;

import java.util.Scanner;

public class MenusSeleeccionarEnm {

    public static FormatoPapel SeleccionarTamanioPapel() {
        int opcion;
        Scanner menu = new Scanner(System.in);
        while (true) {
            System.out.println("((((((((((((((((((((((((((((((((((\n"
                    + "       SELECCIONE FORMATO"
                    + "\n))))))))))))))))))))))))))))))))))\n"
                    + "\t1. - A3\n"
                    + "\t2. - A4\n"
                    + "\t3. - A5\n"
                    + "\t4. - A6\n"
                    + "\t5. - Tarjeta visita (85x55)\n"
            );
            opcion = menu.nextInt();
            switch (opcion) {
                case 1:
                    return FormatoPapel.A3;
                case 2:
                    return FormatoPapel.A4;
                case 3:
                    return FormatoPapel.A5;
                case 4:
                    return FormatoPapel.A6;
                case 5:
                    return FormatoPapel.TARJETA;
                case 0:
                    System.out.println("Volver");
                    return null;
            }
        }
    }

    public static TipoPapel SeleccionarTipoPapel() {
        int opcion;
        Scanner menu = new Scanner(System.in);
        while (true) {
            System.out.println("((((((((((((((((((((((((((((((((((\n"
                    + "           TIPO DE PAPEL"
                    + "\n))))))))))))))))))))))))))))))))))\n"
                    + "\t1. - BRILLO\n"
                    + "\t2. - MATE\n"
                    + "\t3. - OFFSET\n"
                    + "\t4. - CARTULINA\n"
                    + "\t5. - VERJURADO\n"
                    + "\t6. - RECICLADO"
            );
            opcion = menu.nextInt();
            switch (opcion) {
                case 1:
                    return TipoPapel.BRILLO;
                case 2:
                    return TipoPapel.MATE;
                case 3:
                    return TipoPapel.OFFSET;
                case 4:
                    return TipoPapel.CARTULINA;
                case 5:
                    return TipoPapel.VERJURADO;
                case 6:
                    return TipoPapel.RECICLADO;
                case 0:
                    System.out.println("Volver");
                    return null;
            }
        }
    }

}
