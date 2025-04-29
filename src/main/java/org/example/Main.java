package org.example;

import java.util.Scanner;
import java.util.ArrayList;

public class Main{
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<String[]> gastos = new ArrayList<>();
    public static final int TOTALGASTOS = 100;
    public static int cantidadGastos = 0;
    public static String[][] gasto = new String[TOTALGASTOS][4];

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        int opcion;
        do {
            mostrarOpciones();
            opcion = obtenerOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != 2);
    }

    private static void mostrarOpciones() {
        System.out.println("\n=============================" );
        System.out.println("      Gestión de Gastos      " );
        System.out.println("============================= " );
        System.out.println("    [1] Agregar Gasto       " );
        System.out.println("    [2] Mostrar Gastos      " );
        System.out.println("    [3] Porcentaje por Categoría" );
        System.out.println("============================= " );
        System.out.print("      Opcion: ");
    }

    private static int obtenerOpcion() {
        return Integer.parseInt(scanner.nextLine());
    }

    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> agregarGasto();
            case 2 -> mostrasGastos();
            case 3 -> mostrarPorcentajePorCategoria();
            default -> System.out.println(" Opcion invalida...");
        }
    }

    public static void agregarGasto() {

        System.out.println("=== Registrar un nuevo gasto ===");

        System.out.print("Ingrese el monto: ");
        String monto = scanner.nextLine();

        System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();

        System.out.print("Ingrese la categoría: ");
        String categoria = scanner.nextLine();

        System.out.print("Ingrese información adicional: ");
        String infoAdicional = scanner.nextLine();

        gasto[cantidadGastos][0] = monto;
        gasto[cantidadGastos][1] = fecha;
        gasto[cantidadGastos][2] = categoria;
        gasto[cantidadGastos][3] = infoAdicional;
        cantidadGastos++;

        System.out.println("Gasto registrado exitosamente...");
    }

    public static void mostrasGastos() {
        if (cantidadGastos == 0) {
            System.out.println("No has realizado ningun gasto");
            return;
        }
        System.out.println("\n=== Gastos Registrados ===");
        for (int i = 0; i < cantidadGastos; i++) {
            System.out.printf("MONTO: %s, FECHA: %s, CATEGORIA: %s, INFORMACION ADICIONAL: %s", gasto[i][0], gasto[i][1], gasto[i][2], gasto[i][3]);
            return;
        }
        System.out.println();
        return;
    }
    public static void mostrarPorcentajePorCategoria() {
        if (cantidadGastos == 0) {
            System.out.println("No hay gastos registrados para calcular porcentajes.");
            return;
        }

        java.util.HashMap<String, Integer> contadorCategorias = new java.util.HashMap<>();

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

}

