package org.example;

import java.util.Scanner;
// Para la clase main es INDISPENSABLE que la matriz base sea string
public class Main {
    static String[][] matrizMetas=new String[5][5];
    private static final Scanner scanner= new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Bienvenido al sistema de control de gastos estudiantiles");
        System.out.println("Igrese el nombre de usuario");
        String nombre= scanner.nextLine();
        if (nombre.trim().isEmpty()) {
            System.out.println("Error: proporcione usuario");
        }
        menu();
    }

    public static void menu() {
        int opcion;
        do {
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
            } else {
                System.out.println("Error. debe introducir un numero ");
                scanner.next();
                opcion = -1;
            }
            mostrarOpciones();
        } while (opcion != 0);
    }

    private static void mostrarOpciones() {
        System.out.println("Menu Principal");
        System.out.println("Registrar gastos");
        System.out.println("Historial");
        System.out.println("Calcular Total");
        System.out.println("Búsqueda por categoria");
        System.out.println("Calcular promedio");
        System.out.println("Establecer meta");
        System.out.println("Revisar meta");
        System.out.println("Salir");
    }

    static double total(String[][] matriz) {
        double suma=0;
        for(int i=0; i<matriz.length; i++) {
            double numero=Double.parseDouble(matriz[i][0]);
            suma+= numero;
            System.out.println("El total es de "+ suma);
            // Esta funcion toma la matriz donde se guardan los datos recorre la primeca columna cambia cada dato a double y los suma
        } return(suma);
    }

    static double promedio(String[][] matriz) {
        double suma = 0;
        for (int i = 0; i < matriz.length; i++) {
            double total = Double.parseDouble(matriz[i][0]);
            suma+=total;
        }
            double resultado = suma / matriz.length;
            System.out.println("El promedio sería de" + resultado);
            return (resultado);
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
        for (int i=0; i<= matrizMetas.length; i++) {
            for (int j=0; j<= matrizMetas[i].length; j++) {
                System.out.println(matrizMetas[i][j]);
            }
        }
    }
}
