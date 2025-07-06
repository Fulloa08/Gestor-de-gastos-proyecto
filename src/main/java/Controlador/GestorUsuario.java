package Controlador;


import java.io.*;
import java.util.*;

public class GestorUsuario {
    private final String archivo = "usuarios.txt";

    public GestorUsuario() {
        File f = new File(archivo);
        try {
            if (!f.exists()) f.createNewFile();
        } catch (IOException e) {
            System.out.println("Error al crear archivo de usuarios.");
        }
    }

    // Verifica usuario con contraseña o lo registra si no existe
    public static boolean autenticarUsuario(String nombre, String contrasena) {
        File archivoUsuarios = new File("data/usuarios.txt");

        try {
            if (!archivoUsuarios.exists()) {
                archivoUsuarios.getParentFile().mkdirs();
                archivoUsuarios.createNewFile();
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(archivoUsuarios))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] partes = linea.split(";");
                    if (partes.length == 2) {
                        String nombreArchivo = partes[0].trim();
                        String claveArchivo = partes[1].trim();

                        if (nombreArchivo.equals(nombre.trim()) && claveArchivo.equals(contrasena.trim())) {
                            return true;
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error autenticando usuario: " + e.getMessage());
        }

        return false; // No se encontró coincidencia
    }


    // Crea archivos necesarios por usuario si no existen
    private static void crearArchivosUsuario(String nombreUsuario) {
        try {
            File dir = new File("data");
            if (!dir.exists()) dir.mkdirs();

            File gastos = new File("data/gastos_" + nombreUsuario + ".txt");
            if (!gastos.exists()) gastos.createNewFile();

            File meta = new File("data/meta_" + nombreUsuario + ".txt");
            if (!meta.exists()) meta.createNewFile();

        } catch (IOException e) {
            System.out.println("Error creando archivos del usuario: " + e.getMessage());
        }
    }

    public static boolean registrarUsuario(String nombre, String contrasena) {
        File archivoUsuarios = new File("data/usuarios.txt");

        try {
            // Verificar si ya existe el usuario
            try (BufferedReader reader = new BufferedReader(new FileReader(archivoUsuarios))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] partes = linea.split(";");
                    if (partes.length == 2 && partes[0].trim().equals(nombre.trim())) {
                        return false; // Usuario ya existe
                    }
                }
            }

            // Registrar el nuevo usuario
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoUsuarios, true))) {
                writer.write(nombre + ";" + contrasena);
                writer.newLine();
            }

            // Puedes agregar aquí: crearArchivosUsuario(nombre);
            return true;

        } catch (IOException e) {
            System.out.println("Error registrando usuario: " + e.getMessage());
            return false;
        }
    }
}
