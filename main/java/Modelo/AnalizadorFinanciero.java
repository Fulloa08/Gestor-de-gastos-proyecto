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

    public Map<String,
}
