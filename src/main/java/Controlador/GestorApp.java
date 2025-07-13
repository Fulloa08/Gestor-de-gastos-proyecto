// Archivo: Controlador/GestorApp.java
package Controlador;

import Modelo.Gasto;
import Modelo.Tarjeta;
import Modelo.Usuario;

import java.util.List;
import java.util.Scanner;

public class GestorApp {
    private final Usuario usuario;
    private GestorDatos gestorDatos;
    private GestorAnalisisFinanciero analisis;
    private GestorAhorro gestorAhorro;
    private GestorBuscador buscador;

    private final Scanner scanner;

    public GestorApp(Usuario usuario) {
        this.usuario = usuario;
        this.gestorDatos = new GestorDatos(usuario);
        this.analisis = new GestorAnalisisFinanciero(gestorDatos);
        this.gestorAhorro = new GestorAhorro(gestorDatos);
        this.buscador = new GestorBuscador(gestorDatos);
        this.scanner = new Scanner(System.in);
    }

    public void registrarGasto() {
        gestorDatos.registrarGasto();
    }

    public void mostrarHistorial() {
        List<Gasto> historial = gestorDatos.obtenerHistorial();
        if (historial.isEmpty()) {
            System.out.println("No hay gastos registrados.");
        } else {
            for (Gasto g : historial) {
                System.out.printf("%s - %s - $%.2f - %s\n",
                        g.getFecha(), g.getCategoria(), g.getMonto(), g.getDetalle());
            }
        }
    }

    public void definirMeta() {
        System.out.print("Ingrese monto de la meta mensual: $");
        double monto = Double.parseDouble(scanner.nextLine());
        gestorAhorro.definirMeta(monto);
        System.out.println("Meta guardada correctamente.");
    }



    public void calcularPromedio() {
        double promedio = analisis.calcularPromedio();
        System.out.println("Promedio de gastos: $" + promedio);
    }



    public void buscarPorCategoria() {
        System.out.print("Ingrese categoría (Estudios, Alimentación, Transporte, Ocio, Varios): ");
        String categoria = scanner.nextLine();
        List<Gasto> resultados = buscador.buscarPorCategoria(categoria);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron resultados para esa categoría.");
        } else {
            resultados.forEach(g -> System.out.printf("%s - $%.2f - %s\n",
                    g.getFecha(), g.getMonto(), g.getDetalle()));
        }
    }

    public void buscarPorFecha() {
        System.out.print("Ingrese fecha (dd/MM/yyyy): ");
        String fecha = scanner.nextLine();
        List<Gasto> resultados = buscador.buscarPorFecha(fecha);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron gastos en esa fecha.");
        } else {
            resultados.forEach(g -> System.out.printf("%s - $%.2f - %s\n",
                    g.getCategoria(), g.getMonto(), g.getDetalle()));
        }
    }
    // corregid
}
