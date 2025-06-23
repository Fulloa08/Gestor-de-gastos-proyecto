package Controlador;

import modelo.Gasto;

import java.util.*;

public class GestorAnalisisFinanciero {
    private List<Gasto> gastos;

    public GestorAnalisisFinanciero(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    public double calcularPromedio() {
        if (gastos.isEmpty()) return 0.0;
        double total = calcularTotal();
        return total / gastos.size();
    }

    public double calcularTotal() {
        double total = 0;
        for (Gasto g : gastos) {
            total += g.getMonto();
        }
        return total;
    }

    public Map<String, Double> calcularPorcentajePorCategoria() {
        Map<String, Double> sumaPorCategoria = new HashMap<>();
        double total = calcularTotal();

        for (Gasto g : gastos) {
            String categoria = g.getCategoria();
            sumaPorCategoria.put(categoria, sumaPorCategoria.getOrDefault(categoria, 0.0) + g.getMonto());
        }

        Map<String, Double> porcentajePorCategoria = new HashMap<>();
        for (Map.Entry<String, Double> entry : sumaPorCategoria.entrySet()) {
            porcentajePorCategoria.put(entry.getKey(), (entry.getValue() / total) * 100);
        }

        return porcentajePorCategoria;
    }

    public String categoriaMayorGasto() {
        Map<String, Double> sumaPorCategoria = new HashMap<>();

        for (Gasto g : gastos) {
            String categoria = g.getCategoria();
            sumaPorCategoria.put(categoria, sumaPorCategoria.getOrDefault(categoria, 0.0) + g.getMonto());
        }

        String categoriaMayor = "";
        double max = -1;

        for (Map.Entry<String, Double> entry : sumaPorCategoria.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                categoriaMayor = entry.getKey();
            }
        }

        return categoriaMayor;
    }

    public void mostrarResumen() {
        System.out.println("Resumen financiero:");
        System.out.println("- Total: $" + calcularTotal());
        System.out.println("- Promedio: $" + calcularPromedio());
        System.out.println("- Categoría de mayor gasto: " + categoriaMayorGasto());
        System.out.println("- Porcentaje por categoría:");
        Map<String, Double> porcentajes = calcularPorcentajePorCategoria();
        for (Map.Entry<String, Double> entry : porcentajes.entrySet()) {
            System.out.printf("  * %s: %.2f%%\n", entry.getKey(), entry.getValue());
        }
    }
}
