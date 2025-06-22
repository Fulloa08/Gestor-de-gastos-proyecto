package controlador;

import modelo.*;
import vista.Menu;

import java.util.List;
import java.util.Scanner;

public class GestorApp {
    private Usuario usuarioActual;
    private GestorDatos gestorDatos;
    private AnalizadorFinanciero analizador;
    private Menu menu;
    private Scanner scanner;

    public GestorApp() {
        this.gestorDatos = new GestorDatos();
        this.analizador = new AnalizadorFinanciero();
        this.menu = new Menu();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        procesarLogin();
        mostrarMenuPrincipal();
    }

    public void procesarLogin() {
        System.out.print("Ingrese su nombre de usuario: ");
        String nombre = scanner.nextLine();
        this.usuarioActual = new Usuario(nombre);
        this.usuarioActual.iniciarSesion();
        gestorDatos.cargarUsuarios(); // carga todos los usuarios existentes
    }

    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            menu.mostrarMenu();
            opcion = menu.leerOpcion();
            menu.ejecutarOpcion(opcion);
        } while (opcion != 0);
    }

    public void registrarNuevoGasto() {
        System.out.print("Monto del gasto: ");
        double monto = Double.parseDouble(scanner.nextLine());
        System.out.print("Categoría del gasto: ");
        String categoria = scanner.nextLine();
        Gasto gasto = new Gasto(monto, categoria);
        gestorDatos.agregarGasto(gasto);
        gestorDatos.guardarGasto(usuarioActual.getNombre(), gasto);
    }

    public void consultarHistorial() {
        List<Gasto> historial = gestorDatos.obtenerHistorial();
        for (Gasto g : historial) {
            System.out.println(g.getFecha() + " - " + g.getCategoria() + ": $" + g.getMonto());
        }
    }

    public void generarReporteTotal() {
        double total = gestorDatos.getTotalGastado();
        System.out.println("Total gastado: $" + total);
    }

    public void buscarGasto() {
        System.out.print("Ingrese categoría a buscar: ");
        String categoria = scanner.nextLine();
        List<Gasto> resultados = gestorDatos.buscarPorCategoria(categoria);
        for (Gasto g : resultados) {
            System.out.println(g.getFecha() + " - " + g.getCategoria() + ": $" + g.getMonto());
        }
    }

    public void generarReportePorcentajes() {
        List<Gasto> gastos = gestorDatos.obtenerHistorial();
        var porcentajes = analizador.calcularPorcentajePorTipo(gastos);
        for (String cat : porcentajes.keySet()) {
            System.out.println(cat + ": " + porcentajes.get(cat) + "%");
        }
    }

    public void calcularPromedioGastos() {
        List<Gasto> gastos = gestorDatos.obtenerHistorial();
        double promedio = analizador.calcularPromedio(gastos);
        System.out.println("Promedio de gastos: $" + promedio);
    }

    public void gestionarMetas() {
        System.out.print("Monto de meta: ");
        double monto = Double.parseDouble(scanner.nextLine());
        System.out.print("Mes: ");
        String mes = scanner.nextLine();
        MetaAhorro meta = new MetaAhorro(monto, mes);
        gestorDatos.establecerMeta(meta);
        gestorDatos.guardarMeta(usuarioActual.getNombre(), meta);
    }

    public void gestionarTarjetas() {
        System.out.print("Número de tarjeta: ");
        String numero = scanner.nextLine();
        System.out.print("Saldo inicial: ");
        double saldo = Double.parseDouble(scanner.nextLine());
        Tarjeta tarjeta = new Tarjeta(numero, saldo);
        gestorDatos.guardarTarjeta(usuarioActual.getNombre(), tarjeta);
    }
}
