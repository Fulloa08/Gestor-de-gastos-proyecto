package org.example;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static final int TOTALGASTOS = 100;
    public static int cantidadGastos = 0;
    public static String[][] gasto = new String[TOTALGASTOS][4];

    // Variables para meta de ahorro
    public static double meta = 0;
    public static double saldoActual = 0;

    public static void main(String[] args) {
        if (validacionUsuario()) {
            menu();
        } else {
            System.out.println("Acceso denegado.");
        }
    }

    public static boolean validacionUsuario() {
        String[][] usuarios = {
                {"felipe", "1234"}
        };

        System.out.print("Ingrese nombre de usuario: ");
        String usuarioIngresado = scanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        String contrasenaIngresada = scanner.nextLine();

        for (String[] usuario : usuarios) {
            if (usuario[0].equals(usuarioIngresado) && usuario[1].equals(contrasenaIngresada)) {
                System.out.println("Acceso permitido.");
                return true;
            }
        }
        return false;
    }

    public static void menu() {
        int opcion;
        do {
            mostrarOpciones();
            opcion = obtenerOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != 0);
    }

    private static void mostrarOpciones() {
        System.out.println("\n=============================");
        System.out.println("      Gestión de Gastos      ");
        System.out.println("=============================");
        System.out.println("[1] Agregar Gasto");
        System.out.println("[2] Mostrar Gastos");
        System.out.println("[3] Porcentaje por Categoría");
        System.out.println("[4] Buscar por Fecha");
        System.out.println("[5] Mostrar Tarjeta Bancaria");
        System.out.println("[6] Establecer Meta de Ahorro");
        System.out.println("[7] Verificar Meta de Ahorro");
        System.out.println("[0] Salir");
        System.out.println("=============================");
        System.out.print("Opción: ");
    }

    private static int obtenerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un número.");
            return -1;
        }
    }

    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> agregarGasto();
            case 2 -> mostrarGastos();
            case 3 -> mostrarPorcentajePorCategoria();
            case 4 -> buscarPorFecha();
            case 5 -> mostrarTarjeta();
            case 6 -> establecerMeta();
            case 7 -> verificarMeta();
            case 0 -> System.out.println("Saliendo...");
            default -> System.out.println("Opción inválida.");
        }
    }

    public static void agregarGasto() {
        if (cantidadGastos >= TOTALGASTOS) {
            System.out.println("Límite de gastos alcanzado.");
            return;
        }

        System.out.println("=== Registrar un nuevo gasto ===");

        System.out.print("Ingrese el monto: ");
        String montoStr = scanner.nextLine();
        double monto = Double.parseDouble(montoStr);

        System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();

        System.out.print("Ingrese la categoría: ");
        String categoria = scanner.nextLine();

        System.out.print("Ingrese información adicional: ");
        String infoAdicional = scanner.nextLine();

        gasto[cantidadGastos][0] = montoStr;
        gasto[cantidadGastos][1] = fecha;
        gasto[cantidadGastos][2] = categoria;
        gasto[cantidadGastos][3] = infoAdicional;
        cantidadGastos++;

        saldoActual -= monto;

        System.out.println("Gasto registrado exitosamente.");
    }

    public static void mostrarGastos() {
        if (cantidadGastos == 0) {
            System.out.println("No has realizado ningún gasto.");
            return;
        }

        System.out.println("\n=== Gastos Registrados ===");
        for (int i = 0; i < cantidadGastos; i++) {
            System.out.printf("MONTO: %s, FECHA: %s, CATEGORÍA: %s, INFORMACIÓN: %s%n",
                    gasto[i][0], gasto[i][1], gasto[i][2], gasto[i][3]);
        }
    }

    public static void mostrarPorcentajePorCategoria() {
        if (cantidadGastos == 0) {
            System.out.println("No hay gastos registrados para calcular porcentajes.");
            return;
        }

        HashMap<String, Integer> contadorCategorias = new HashMap<>();

        for (int i = 0; i < cantidadGastos; i++) {
            String categoria = gasto[i][2];
            contadorCategorias.put(categoria, contadorCategorias.getOrDefault(categoria, 0) + 1);
        }

        System.out.println("\n=== Porcentaje de Gastos por Categoría ===");
        for (String categoria : contadorCategorias.keySet()) {
            int cantidad = contadorCategorias.get(categoria);
            double porcentaje = (cantidad * 100.0) / cantidadGastos;
            System.out.printf("Categoría: %s -> %.2f%% (%d gastos)%n", categoria, porcentaje, cantidad);
        }
    }

    public static void buscarPorFecha() {
        System.out.print("Ingrese la fecha a buscar (YYYY-MM-DD): ");
        String fechaBuscada = scanner.nextLine();

        boolean encontrado = false;
        for (int i = 0; i < cantidadGastos; i++) {
            if (gasto[i][1].equals(fechaBuscada)) {
                System.out.printf("MONTO: %s, CATEGORÍA: %s, INFORMACIÓN: %s%n",
                        gasto[i][0], gasto[i][2], gasto[i][3]);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron gastos para esa fecha.");
        }
    }

    public static void mostrarTarjeta() {
        System.out.println("\n╔════════════════════════════╗");
        System.out.println("║        TARJETA BANCARIA    ║");
        System.out.println("║  Nombre: Felipe Ulloa      ║");
        System.out.printf ("║  Saldo disponible: $%.2f   ║%n", saldoActual);
        System.out.println("║  Banco: Banco de Chile     ║");
        System.out.println("╚════════════════════════════╝");
    }

    public static void establecerMeta() {
        System.out.print("Ingrese su meta de ahorro: $");
        try {
            meta = Double.parseDouble(scanner.nextLine());
            System.out.print("Ingrese su saldo actual: $");
            saldoActual = Double.parseDouble(scanner.nextLine());
            System.out.println("Meta de ahorro configurada correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número válido.");
        }
    }

    public static void verificarMeta() {
        if (meta == 0) {
            System.out.println("Primero debes establecer una meta.");
            return;
        }

        System.out.printf("Meta: $%.2f | Saldo actual: $%.2f%n", meta, saldoActual);
        if (saldoActual >= meta) {
            System.out.println("¡Felicidades! Has alcanzado tu meta de ahorro. 🎉");
        } else {
            double faltante = meta - saldoActual;
            System.out.printf("Aún te faltan $%.2f para alcanzar tu meta.%n", faltante);
        }
    }
}
