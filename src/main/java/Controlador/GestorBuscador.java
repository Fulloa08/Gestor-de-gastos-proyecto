package Controlador;

import Modelo.Gasto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorBuscador {
    private List<Gasto> gastos;
    Scanner scanner=new Scanner(System.in);

    public GestorBuscador(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    public List<Gasto> buscarPorCategoria() {
        System.out.println("Seleccione una de las categorias: Comida, Estudio, Trasporte y Otros");
        String categoria=scanner.nextLine();
        List<Gasto> resultados = new ArrayList<>();
        for (Gasto gasto : gastos) {
            if (gasto.getCategoria().equalsIgnoreCase(categoria)) {
                resultados.add(gasto);
            }
        }
        return resultados;
    }

    public List<Gasto> buscarPorFecha() {
        System.out.println("Diga el dia");
        String dia=scanner.nextLine();
        System.out.println("Diga el mes");
        String mes =scanner.nextLine();
        System.out.println("Diga el año");
        String año= scanner.nextLine();
        String fecha=dia+"/"+mes+"/"+año;
        List<Gasto> resultados = new ArrayList<>();
        for (Gasto gasto : gastos) {
            if (gasto.getFecha().equalsIgnoreCase(fecha)) {
                resultados.add(gasto);
            }
        }
        return resultados;
    }

    public List<Gasto> buscarPorRango(double minimo, double maximo) {
        List<Gasto> resultados = new ArrayList<>();
        for (Gasto gasto : gastos) {
            if (gasto.getMonto() >= minimo && gasto.getMonto() <= maximo) {
                resultados.add(gasto);
            }
        }
        return resultados;
    }

    public void mostrarResultados(List<Gasto> resultados) {
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron resultados");
        }
    }
}

