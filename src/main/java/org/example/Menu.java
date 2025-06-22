package org.example;

import java.util.Scanner;

public class Menu {
    Scanner scanner=new Scanner(System.in);
    GestorDatos gestor;
    GestorAhorro ahorro;
    GestorBuscador buscador;
    GestorAnalisisFinanciero financias;

    public void main(String[] args) {
        mostrarMenu();
    }

    public void mostrarMenu(){
        leerOpciones();
        ejecutarOpciones();
    }

    public void leerOpciones(){
        System.out.println("Bienvenido al gestor de gastos");
        System.out.println("1-Gestion de Gastos");
        System.out.println("2-Meta de Ahorro");
        System.out.println("3-Análisis financiero");
        System.out.println("4-Tarjeta Bancaria");
        System.out.println("0-Salir");
    }

    public void ejecutarOpciones(){
        int eleccion= scanner.nextInt();
        switch(eleccion){
            case 1:
                gestionDeGastosMenu();
            case 2:
                metaAhorroMenu();
            case 3:
                analisisFinancieroMenu();
            case 4:
                buscarGastosMenu();
            case 0:
                break;
            default:
                System.out.println("Error de input");
        }
    }

    public void gestionDeGastosMenu(){
        mostrarOpcionesGestionGastos();
        ejecutarOpcionesGestionGastos();
    }

    public void metaAhorroMenu(){
        mostrarOpcionesAhorro();
        ejecutarOpcionesAhorro();
    }

    public void buscarGastosMenu(){
        mostrarOpcionesGastos();
        ejecutarOpcionesGastos();
    }

    public void analisisFinancieroMenu(){
        mostrarOpcionesAnalisisFinanciero();
        ejecutarOpcionesAnalisisFinanciero();
    }

    public void mostrarOpcionesGestionGastos(){
        System.out.println("1-Registrar Gasto");
        System.out.println("2-Ver Historial");
        System.out.println("3-Volver");
    }

    public void mostrarOpcionesAhorro(){
        System.out.println("1-Establecer Meta");
        System.out.println("2-Ver Metas");
        System.out.println("3-Volver");
    }

    public void mostrarOpcionesGastos(){
        System.out.println("1-Buscar por fecha");
        System.out.println("2-Buscar por categoria");
        System.out.println("3-Volver");
    }

    public void mostrarOpcionesAnalisisFinanciero(){
        System.out.println("1-Calcular Promedio");
        System.out.println("2-Ver Porcentaje por Categoria");
        System.out.println("3-Volver");
    }

    public void ejecutarOpcionesGestionGastos(){
        int eleccion=scanner.nextInt();
        switch (eleccion){
            case 1:
                gestor.agregarGasto();
            case 2:
                gestor.obtenerHistorial();
            case 3:
                break;
            default:
                System.out.println("Error de input");
        }
    }

    public void ejecutarOpcionesAhorro(){
        int eleccion= scanner.nextInt();
        switch (eleccion){
            case 1:
                Ahorro.crearAhorro();
            case 2:
                Ahorro.verHistorial();
            case 3:
                break;
            default:
                System.out.println("Error de input");
        }
    }

    public void ejecutarOpcionesGastos(){
        int eleccion= scanner.nextInt();
        switch (eleccion){
            case 1:
                buscador.buscarFecha();
            case 2:
                buscador.buscarCategoria();
            case 3:
                break;
            default:
                System.out.println("Error de input");
        }
    }

    public void ejecutarOpcionesAnalisisFinanciero(){
        int eleccion= scanner.nextInt();
        switch (eleccion){
            case 1:
                financias.promedio();
            case 2:
                financias.porcentaje();
            case 3:
                break;
            default:
                System.out.println("Error de input");
        }
    }
}
