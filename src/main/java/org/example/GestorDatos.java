package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorDatos {
    Scanner scanner = new Scanner(System.in);
    String nombreArchivo = "Gastos.txt";
    List<String> historial=new ArrayList<>();
    File archivo = new File(nombreArchivo);

    public void agregarGasto() {
        LocalDate ahora = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha = ahora.format(formato);
        System.out.println("Proporcione un monto a registrar");
        double monto = scanner.nextFloat();
        while (true) {
            System.out.println("En que categoria quieres agregar este gasto: Comida, Estudio, Trasporte y Otros");
            String categoria = scanner.nextLine().toLowerCase();
            if (categoria.equals("comida") || categoria.equals("estudio") || categoria.equals("trasporte") || categoria.equals("otros")) {
                break;
            } else {
                System.out.println("Error de input");
            }
            System.out.println("Proporcione un una nota o detalle (opcional)");
            String detalle = scanner.nextLine();
            Gasto gasto = new Gasto(fecha, categoria, monto, detalle);
            crearRegistroGasto(fecha, categoria, monto, detalle);
        }
    }

    private void crearRegistroGasto(String fecha, String categoria, double monto, String detalle) {
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            FileWriter escritor = new FileWriter(archivo, true);
            escritor.write(fecha.replaceAll("\\s+", "") + ";");
            escritor.write(categoria.replaceAll("\\s+", "") + ";");
            escritor.write(String.valueOf(monto).replaceAll("\\s+", "") + ";");
            escritor.write(detalle.replaceAll("\\s+", "") + ";");
            escritor.write(System.lineSeparator());
            escritor.close();
        } catch (IOException e) {
            System.out.println("Error en la creacion del documento");
        }
    }

    public List obtenerHistorial() {
        if (!archivo.exists()) {
            System.out.println("Error: No existe historial");
            return historial;
        } else {
            try (Scanner lector =new Scanner(archivo)){
                while (lector.hasNextLine()){
                    String linea =lector.nextLine();
                    historial.add(linea);
                }
            } catch (IOException e){
                System.out.println("Error al leer el archivo: "+e.getMessage());
            }
            return historial;
        }
    }

    public void limpiarDatos(){
        if (!archivo.exists()){
            System.out.println("Error: El archivo no existe");
        } else {
            try {
                FileWriter escritor=new FileWriter(archivo, false);
                escritor.write("");
                escritor.close();
                System.out.println("Historial eliminado");
            } catch (IOException e) {
                System.out.println("Error al borrar el historial: "+ e.getMessage());
            }
        }
    }
}