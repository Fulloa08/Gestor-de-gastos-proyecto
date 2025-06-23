package Controlador;

import Modelo.Gasto;

import java.util.ArrayList;
import java.util.List;

public class GestorBuscador {
    private List<Gasto> gastos;

    public GestorBuscador(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    public List<Gasto> buscarPorCategoria(String categoria) {
        List<Gasto> resultados = new ArrayList<>();
        for (Gasto gasto : gastos) {
            if (gasto.getCategoria().equalsIgnoreCase(categoria)) {
                resultados.add(gasto);
            }
        }
        return resultados;
    }

    public List<Gasto> buscarPorFecha(String fecha) {
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
            System.out.println("No se encontraron resultad

