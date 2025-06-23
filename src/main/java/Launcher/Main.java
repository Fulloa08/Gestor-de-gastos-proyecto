// Archivo: Launcher/Main.java
package Launcher;

import Controlador.GestorApp;
import Controlador.GestorUsuario;
import Modelo.Usuario;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Bienvenido al Gestor de Gastos ===");
        System.out.print("Ingrese su nombre de usuario: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine().trim();

        boolean autenticado = GestorUsuario.autenticarUsuario(nombre, contrasena);

        if (autenticado) {
            Usuario usuario = new Usuario(nombre);
            GestorApp app = new GestorApp(usuario);
            app.iniciar();
        } else {
            System.out.println("Error de autenticación. Verifique sus datos.");
        }
    }
}
