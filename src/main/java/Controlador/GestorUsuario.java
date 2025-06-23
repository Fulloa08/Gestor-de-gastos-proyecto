package Controlador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorUsuario {
    private Usuario user;
    private Scanner scanner=new Scanner(System.in);
    private static final String archivo="Usuarios.txt";
    List<Usuario> usuarios=new ArrayList<>();

    public void crearUsuario(){
        System.out.println("Ponga el nombre de usuario deseado ");
        String usuario= scanner.nextLine();
        System.out.println("Ponga una contraseña ");
        String clave=scanner.nextLine();
        if (usuarioExiste(usuario)){
            System.out.println("Error: El usuario ya existe");
        } else {
            agregarUsuario(usuario, clave);
            System.out.println("Usuario registrado con exito");
            user=new Usuario(usuario,clave);
            usuarios.add(user);
        }
    }

    public boolean usuarioExiste(String usuario){
        try (BufferedReader br= new BufferedReader(new FileReader(archivo))){
            String linea;
            while ((linea=br.readLine())!=null){
                String[] partes=linea.split(";");
                if (partes.length >0 && partes[0].equals(usuario)){
                    return true;
                }
            }
        } catch (FileNotFoundException e){
            return false;
        } catch (IOException e){
            System.out.println("Error al leer el archivo");
        }
        return false;
    }

    public void agregarUsuario(String usuario, String clave){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(usuario + ";" + clave + ";");
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
        }
    }

    public void eliminarUsuario(String usuario, String clave) {
        File archivoOriginal = new File(archivo);
        File archivoTemporal = new File("temp_usuarios.txt");

        boolean encontrado = false;

        try (
                BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))
        ) {
            String linea;
            String objetivo = usuario.toLowerCase() + ";" + clave.toLowerCase() + ";";

            while ((linea = br.readLine()) != null) {
                if (linea.equalsIgnoreCase(objetivo)) {
                    encontrado = true;
                    continue;
                }
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo.");
            return;
        }
        if (archivoOriginal.delete()) {
            if (!archivoTemporal.renameTo(archivoOriginal)) {
                System.out.println("Error al renombrar el archivo temporal.");
            } else if (encontrado) {
                System.out.println("Usuario eliminado correctamente.");
            } else {
                System.out.println("No se encontró el usuario con esa clave.");
            }
        } else {
            System.out.println("No se pudo eliminar el archivo original.");
        }
        usuarios.remove(new Usuario(usuario,clave));
    }

    public boolean comprobarCredenciales(String name, String clave){

        String entrada = name.toLowerCase() + ";" + clave.toLowerCase() + ";";

        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.equals(entrada)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
        return false;
    }

    public void sessionActiva(String name,String clave){
        if (comprobarCredenciales(name,clave)){
            Usuario user=new Usuario(name,clave);
            user.iniciarSession();
        }
    }

    public void sessionCerrada(String name, String clave){
        Usuario user=new Usuario(name, clave);
        user.cerrarSession();
    }
}
