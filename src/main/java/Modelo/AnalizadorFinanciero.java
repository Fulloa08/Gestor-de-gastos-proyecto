package Modelo;

import java.util.*;

public class AnalizadorFinanciero {

    // Calcula el promedio de todos los montos de gasto recibidos.
    public double calcularPromedio(List<Gasto> gastos) {
        if (gastos == null || gastos.isEmpty()) {
            return 0;
        }

        double total = 0;

        for (Gasto g : gastos) {
            total += g.getMonto(); // Suma el monto de cada gasto
        }

        // Retorna el promedio dividiendo el total entre la cantidad de gastos
        return total / gastos.size();
    }

    // Calcula el porcentaje de cada tipo de gasto dentro del total utilizando un ArrayList
    public List<String> calcularPorcentajePorTipo(List<Gasto> gastos) {
        double totalGeneral = 0;
        List<String> resultados = new ArrayList<>();

        // Primero sumamos el total general
        for (Gasto g : gastos) {
            totalGeneral += g.getMonto();
        }

        // Ahora calculamos el porcentaje por cada categoría
        List<String> categorias = new ArrayList<>(); // Para evitar repeticiones
        for (Gasto g : gastos) {
            if (!categorias.contains(g.getCategoria())) {
                categorias.add(g.getCategoria());
            }
        }

        // Para cada categoría, calculamos su porcentaje del total
        for (String categoria : categorias) {
            double totalCategoria = 0;
            for (Gasto g : gastos) {
                if (g.getCategoria().equals(categoria)) {
                    totalCategoria += g.getMonto();
                }
            }
            double porcentaje = (totalCategoria * 100) / totalGeneral;
            resultados.add(categoria + ": " + porcentaje + "%");
        }

        return resultados;
    }
}
