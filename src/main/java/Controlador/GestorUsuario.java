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

            List<String> lineas = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(archivoUsuarios))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    lineas.add(linea);
                    String[] partes = linea.split(";");
                    if (partes.length == 2 && partes[0].equals(nombre)) {
                        return partes[1].equals(contrasena);
                    }
                }
            }

            // Si no existe, registrarlo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoUsuarios, true))) {
                writer.write(nombre + ";" + contrasena);
                writer.newLine();
                System.out.println("Usuario registrado correctamente.");
            }

            // Crear archivos asociados
            crearArchivosUsuario(nombre);

            return true;

        } catch (IOException e) {
            System.out.println("Error autenticando usuario: " + e.getMessage());
        }
        return false;
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


}
