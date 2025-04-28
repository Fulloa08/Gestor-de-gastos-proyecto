package org.example;

import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
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
}

