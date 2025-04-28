package org.example;

import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
    validacionUsuario();
    Object[][] matriz = {
            {1000.0, "Comida", "2023-04-10", "Almuerzo en restaurante"},
            {200.0, "Transporte", "2023-04-10", "Taxi al trabajo"},
            {150.0, "Comida", "2023-04-11", "Cena ligera"},
            {500.0, "Entretenimiento", "2023-04-12", "Entrada al cine"},
            {300.0, "Educación", "2023-04-12", "Compra de libros"}
    };


    busquedaPorFecha(matriz, "2023-04-10");

    }
    public static void validacionUsuario () {
        String[][] usuarios = {
                {"felipe", "1234"},
        };
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese nombre de usuario: ");
        String usuarioIngresado = sc.nextLine();
        System.out.print("Ingrese contraseña: ");
        String contrasenaIngresada = sc.nextLine();

        boolean accesoConcedido = false;

        // Recorrer la matriz
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i][0].equals(usuarioIngresado) && usuarios[i][1].equals(contrasenaIngresada)) {
                accesoConcedido = true;
                break;
            }
        }

        // Mostrar resultado
        if (accesoConcedido) {
            System.out.println("Acceso permitido.");
        } else {
            System.out.println("Acceso denegado.");
        }

        sc.close();
    }
    public static void busquedaPorFecha(Object[][] matriz, String fechaBuscada) {
        for (Object[] fila : matriz) {
            if (fila[2].equals(fechaBuscada)) {
                System.out.println("Monto: " + fila[0] + ", Tipo de gasto: " + fila[1] +
                        ", Fecha: " + fila[2] + ", Detalle: " + fila[3]);
            }
        }
    }
}




