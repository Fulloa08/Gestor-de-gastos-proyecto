// Archivo: Vista/Menu.java
package Vista;

import Controlador.GestorApp;
import Modelo.Usuario;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final GestorApp app;

    public Menu(GestorApp app) {
        this.app = app;
    }

    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestión de Gastos");
            System.out.println("2. Meta Mensual");
            System.out.println("3. Análisis Financiero");
            System.out.println("4. Buscar Gastos");
            System.out.println("5. Tarjeta Bancaria");
            System.out.println("0. Salir");
            System.out.print("Seleccione opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> menuGestionGastos();
                case 2 -> menuMetaMensual();
                case 3 -> menuAnalisisFinanciero();
                case 4 -> menuBuscarGastos();
                case 5 -> menuTarjeta();
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    public void menuGestionGastos() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE GASTOS ---");
            System.out.println("1. Registrar Gasto");
            System.out.println("2. Ver Historial");
            System.out.println("0. Volver");
            System.out.print("Seleccione opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> app.registrarGasto();
                case 2 -> app.mostrarHistorial();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    public void menuMetaMensual() {
        int opcion;
        do {
            System.out.println("\n--- META MENSUAL ---");
            System.out.println("1. Definir o Actualizar Meta");
            System.out.println("2. Ver Progreso de la Meta");
            System.out.println("0. Volver");
            System.out.print("Seleccione opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> app.definirMeta();
                case 2 -> app.verProgresoMeta();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    public void menuAnalisisFinanciero() {
        int opcion;
        do {
            System.out.println("\n--- ANÁLISIS FINANCIERO ---");
            System.out.println("1. Calcular Promedio de Gastos");
            System.out.println("2. Ver Porcentaje por Categoría");
            System.out.println("0. Volver");
            System.out.print("Seleccione opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> app.calcularPromedio();
                case 2 -> app.verPorcentajeCategorias();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    public void menuBuscarGastos() {
        int opcion;
        do {
            System.out.println("\n--- BUSCAR GASTOS ---");
            System.out.println("1. Buscar por Categoría");
            System.out.println("2. Buscar por Fecha");
            System.out.println("0. Volver");
            System.out.print("Seleccione opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> app.buscarPorCategoria();
                case 2 -> app.buscarPorFecha();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    public void menuTarjeta() {
        int opcion;
        do {
            System.out.println("\n--- TARJETA BANCARIA ---");
            System.out.println("1. Registrar Tarjeta");
            System.out.println("2. Ver Saldo");
            System.out.println("3. Recargar Tarjeta");
            System.out.println("0. Volver");
            System.out.print("Seleccione opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> app.registrarTarjeta();
                case 2 -> app.verSaldoTarjeta();
                case 3 -> app.recargarTarjeta();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }
    }
}