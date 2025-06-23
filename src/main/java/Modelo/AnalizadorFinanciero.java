package Modelo;

import java.util.*;

public class AnalizadorFinanciero {

    public double calcularPromedio(List<Gasto> gastos) {
        if (gastos == null || gastos.isEmpty()) return 0;
        double total = 0;
        for (Gasto g : gastos) {
            total += g.getMonto();
        }
        return total / gastos.size();
    }

    public Map<String, Double> calcularPorcentajePorTipo(List<Gasto> gastos) {
        Map<String, Double> porcentajes = new HashMap<>();
        double total = gastos.stream().mapToDouble(Gasto::getMonto).sum();
        if (total == 0) return porcentajes;

        Map<String, Double> sumaPorCategoria = new HashMap<>();
        for (Gasto g : gastos) {
            sumaPorCategoria.put(g.getCategoria(),
                    sumaPorCategoria.getOrDefault(g.getCategoria(), 0.0) + g.getMonto());
        }

        for (String categoria : sumaPorCategoria.keySet()) {
            double porcentaje = (sumaPorCategoria.get(categoria) * 100) / total;
            porcentajes.put(categoria, porcentaje);
        }

        return porcentajes;
    }
}
