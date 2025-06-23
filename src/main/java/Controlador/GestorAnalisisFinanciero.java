// Archivo: Controlador/GestorAnalisisFinanciero.java
package Controlador;

import Modelo.Gasto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorAnalisisFinanciero {
    private final GestorDatos gestorDatos;

    public GestorAnalisisFinanciero(GestorDatos gestorDatos) {
        this.gestorDatos = gestorDatos;
    }

    public double calcularPromedio() {
        List<Gasto> gastos = gestorDatos.obtenerHistorial();
        if (gastos.isEmpty()) return 0;
        return gastos.stream().mapToDouble(Gasto::getMonto).average().orElse(0);
    }

    public void mostrarPorcentajes() {
        List<Gasto> gastos = gestorDatos.obtenerHistorial();
        double total = gastos.stream().mapToDouble(Gasto::getMonto).sum();

        if (total == 0) {
            System.out.println("No hay gastos registrados para analizar.");
            return;
        }

        Map<String, Double> porCategoria = new HashMap<>();

        for (Gasto g : gastos) {
            porCategoria.put(g.getCategoria(), porCategoria.getOrDefault(g.getCategoria(), 0.0) + g.getMonto());
        }

        System.out.println("\n--- Porcentaje por Categoría ---");
        for (Map.Entry<String, Double> entry : porCategoria.entrySet()) {
            double porcentaje = (entry.getValue() / total) * 100;
            System.out.printf("%s: %.2f%%\n", entry.getKey(), porcentaje);
        }
    }
}
