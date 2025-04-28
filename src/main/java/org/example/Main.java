package org.example;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner= new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Bienvenido al sistema de control de gastos estudiantiles");
        System.out.println("Igrese el nombre de usuario");
        String nombre= scanner.nextLine();
        menu();
    }

    public static void menu() {
        int opcion;
        do {
            opcion=scanner.nextInt();
            System.out.println("Menu Principal");
            System.out.println("Registrar gastos");
            System.out.println("Historial");
            System.out.println("Calcular Total");
            System.out.println("Búsqueda por categoria");
            System.out.println("Calcular promedio");
            System.out.println("Establecer meta");
            System.out.println("Salir");
        } while (opcion!=0);
    }

    private static void total(double[][] matriz) {
        for(int i=0; i<=matriz.length; i++) {
            int suma=0;
            int numero=Double.parseDouble(matriz[i][0]);
            suma+=numero;
            System.out.println("El total es de "+ suma);
            // Esta funcion toma la matriz donde se guardan los datos recorre la primeca columna cambia cada dato a double y los suma
        }
    }

    private static void promedio(double[][] matriz) {
        for(int i=0; i<= matriz.length; i++) {
            double total=Double.parseDouble(matriz[i][0]);
            double resultado=total/i;
            System.out.println("El promedio sería de"+resultado);
        }
    }
}