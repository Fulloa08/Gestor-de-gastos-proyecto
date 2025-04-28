package org.example;

import java.util.Scanner;

public class Main {
    static String[][] matrizMetas=new String[5][5];
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
            System.out.println("Revisar meta");
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

    private static void meta() {
        System.out.print("Qué monto meta desea proponer ");
        String montoMeta = scanner.nextLine();
        System.out.println("Para que mes desea mantener ese monto? ");
        String mes = scanner.nextLine();
        matrizMetas[0][0]=mes;
        matrizMetas[0][1]=montoMeta;
    }

    private static void visualizarMetas(String[][] matrizMetas) {
        for (int i=0; i< matrizMetas.length; i++) {
            for (int j=0; j< matrizMetas[i].length; j++) {
                System.out.println(matrizMetas[i][j]);
            }
        }
    }
}