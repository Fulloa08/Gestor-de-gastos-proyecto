package org.example;

import java.util.Scanner;

public class Main {
    static String[][] matrizMetas = new String[5][5];
    static Object[][] matrizGastos = {
            {1000.0, "Comida", "2023-04-10", "Almuerzo en restaurante"},
            {200.0, "Transporte", "2023-04-10", "Taxi al trabajo"},
            {150.0, "Comida", "2023-04-11", "Cena ligera"},
            {500.0, "Entretenimiento", "2023-04-12", "Entrada al cine"},
            {300.0, "Educación", "2023-04-12", "Compra de libros"}
    };
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Bienvenido al sistema de control de gastos estudiantiles");
        validacionUsuario();
        menu();
    }

    public static void validacionUsuario() {
        String[][] usuarios = {
                {"felipe", "1234"},
        };

        System.out.print("Ingrese nombre de usuario: ");
        String usuarioIngresado = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String contrasenaIngresada = scanner.nextLine();

        boolean accesoConcedido = false;

        for (String[] usuario : usuarios) {
            if (usuario[0].equals(usuarioIngresado) && usuario[1].equals(contrasenaIngresada)) {
                accesoConcedido = true;
                break;
            }
        }

        if (accesoConcedido) {
            System.out.println("Acceso permitido.");
        } else {
            System.out.println("Acceso denegado.");
            System.exit(0);
        }
    }

    public static void menu() {
        int opcion;
        do {
            mostrarOpciones();
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpia buffer
            } else {
                System.out.println("Error. Debe introducir un número.");
                scanner.next(); // Limpiar input inválido
                opcion = -1;
                continue;
            }

            switch (opcion) {
                case 1:
                    busquedaPorFecha(matrizGastos, "2023-04-10");
                    break;
                case 2:
                    busquedaPorCategoriaGasto(matrizGastos, "Comida");
                    break;
                case 3:
                    calcularTotal();
                    break;
                case 4:
                    calcularPromedio();
                    break;
                case 5:
                    establecerMeta();
                    break;
                case 6:
                    visualizarMetas(matrizMetas);
                    break;
                case 7:
                    mostrarTarjeta("Banco Estado", 25000);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static void mostrarOpciones() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Buscar gastos por fecha");
        System.out.println("2. Buscar por categoría de gasto");
        System.out.println("3. Calcular Total de gastos");
        System.out.println("4. Calcular Promedio de gastos");
        System.out.println("5. Establecer meta");
        System.out.println("6. Revisar metas");
        System.out.println("7. Mostrar tarjeta");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static void busquedaPorFecha(Object[][] matriz, String fechaBuscada) {
        for (Object[] fila : matriz) {
            if (fila[2].equals(fechaBuscada)) {
                System.out.println("Monto: " + fila[0] + ", Tipo de gasto: " + fila[1] +
                        ", Fecha: " + fila[2] + ", Detalle: " + fila[3]);
            }
        }
    }

    public static void busquedaPorCategoriaGasto(Object[][] matriz, String categoria) {
        for (Object[] fila : matriz) {
            if (fila[1].equals(categoria)) {
                System.out.println("Monto: " + fila[0] + ", Tipo de gasto: " + fila[1] +
                        ", Fecha: " + fila[2] + ", Detalle: " + fila[3]);
            }
        }
    }

    public static void calcularTotal() {
        double suma = 0;
        for (Object[] fila : matrizGastos) {
            suma += (Double) fila[0];
        }
        System.out.println("El total es: $" + suma);
    }

    public static void calcularPromedio() {
        double suma = 0;
        for (Object[] fila : matrizGastos) {
            suma += (Double) fila[0];
        }
        double promedio = suma / matrizGastos.length;
        System.out.println("El promedio es: $" + promedio);
    }

    private static void establecerMeta() {
        System.out.print("¿Qué monto meta desea proponer? ");
        String montoMeta = scanner.nextLine();
        System.out.print("¿Para qué mes desea mantener ese monto? ");
        String mes = scanner.nextLine();
        matrizMetas[0][0] = mes;
        matrizMetas[0][1] = montoMeta;
        System.out.println("Meta establecida correctamente.");
    }

    private static void visualizarMetas(String[][] matriz) {
        System.out.println("\n--- Metas Registradas ---");
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][0] != null && matriz[i][1] != null) {
                System.out.println("Mes: " + matriz[i][0] + " | Monto Meta: " + matriz[i][1]);
            }
        }
    }

    public static void mostrarTarjeta(String nombre, double saldo) {
        System.out.println("****************************");
        System.out.println("*        TARJETA           *");
        System.out.println("*                          *");
        System.out.println("*  Nombre: " + nombre);
        System.out.printf("*  Saldo: $%.2f         *\n", saldo);
        System.out.println("*                          *");
        System.out.println("****************************");
    }
}



