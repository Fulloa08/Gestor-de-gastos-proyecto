// Archivo: Controlador/GestorDatos.java
package Controlador;

import Modelo.Gasto;
import Modelo.Tarjeta;
import Modelo.Usuario;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GestorDatos {
    private final String BASE_PATH = "data/";
    private final String gastosFile;
    private final String tarjetaFile;
    private final String metaFile;
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final Scanner scanner = new Scanner(System.in);
    private final List<String> categorias = Arrays.asList("Estudios", "Alimentación", "Transporte", "Ocio", "Varios");

    public GestorDatos(Usuario usuario) {
        String nombre = usuario.getNombre();
        this.gastosFile = BASE_PATH + "gastos_" + nombre + ".txt";
        this.tarjetaFile = BASE_PATH + "tarjeta_" + nombre + ".txt";
        this.metaFile = BASE_PATH + "meta_" + nombre + ".txt";
        verificarOCrearArchivos();
    }

    private void verificarOCrearArchivos() {
        try {
            new File(BASE_PATH).mkdirs();

            new File(gastosFile).createNewFile();
            new File(metaFile).createNewFile();

            File archivoTarjeta = new File(tarjetaFile);
            if (archivoTarjeta.createNewFile()) {
                // Si el archivo no existía y fue creado, escribe datos iniciales
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTarjeta))) {
                    String numero = generarNumeroTarjeta();
                    double saldoInicial = 100000; // saldo simulado
                    writer.write(numero + ";" + saldoInicial);
                }
            }
        } catch (IOException e) {
            System.out.println("Error creando archivos: " + e.getMessage());
        }
    }


    public void registrarGasto() {
        System.out.println("Categorías disponibles: " + categorias);
        System.out.print("Ingrese categoría: ");
        String categoria = scanner.nextLine();
        if (!categorias.contains(categoria)) {
            System.out.println("Categoría inválida.");
            return;
        }
        System.out.print("Monto: $");
        double monto = Double.parseDouble(scanner.nextLine());
        System.out.print("Detalle: ");
        String detalle = scanner.nextLine();

        Gasto gasto = new Gasto(LocalDate.now().format(FORMATTER), categoria, monto, detalle);
        guardarGasto(gasto);
        System.out.println("Gasto registrado correctamente.");
    }

    public void guardarGasto(Gasto gasto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(gastosFile, true))) {
            writer.write(gasto.getFecha() + ";" + gasto.getCategoria() + ";" + gasto.getMonto() + ";" + gasto.getDetalle());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error guardando gasto: " + e.getMessage());
        }
    }

    public List<Gasto> obtenerHistorial() {
        List<Gasto> gastos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(gastosFile))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    gastos.add(new Gasto(partes[0], partes[1], Double.parseDouble(partes[2]), partes[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo historial: " + e.getMessage());
        }
        return gastos;
    }

    public double obtenerTotalGastado() {
        return obtenerHistorial().stream().mapToDouble(Gasto::getMonto).sum();
    }

    public List<Gasto> buscarPorCategoria(String categoria) {
        List<Gasto> resultados = new ArrayList<>();
        for (Gasto g : obtenerHistorial()) {
            if (g.getCategoria().equalsIgnoreCase(categoria)) {
                resultados.add(g);
            }
        }
        return resultados;
    }

    public List<Gasto> buscarPorFecha(String fecha) {
        List<Gasto> resultados = new ArrayList<>();
        for (Gasto g : obtenerHistorial()) {
            if (g.getFecha().equals(fecha)) {
                resultados.add(g);
            }
        }
        return resultados;
    }

    public void guardarMeta(double meta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(metaFile))) {
            writer.write(String.valueOf(meta));
        } catch (IOException e) {
            System.out.println("Error guardando meta: " + e.getMessage());
        }
    }

    public double cargarMeta() {
        try (BufferedReader reader = new BufferedReader(new FileReader(metaFile))) {
            String linea = reader.readLine();
            if (linea != null) {
                return Double.parseDouble(linea);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error leyendo meta: " + e.getMessage());
        }
        return 0;
    }


    private String generarNumeroTarjeta() {
        Random rand = new Random();
        return String.format("%04d-%04d-%04d-%04d",
                rand.nextInt(10000), rand.nextInt(10000),
                rand.nextInt(10000), rand.nextInt(10000));
    }



    public Tarjeta getTarjeta() {
        try (BufferedReader reader = new BufferedReader(new FileReader(tarjetaFile))) {
            String linea = reader.readLine();
            if (linea != null) {
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                    return new Tarjeta(partes[0], Double.parseDouble(partes[1]));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error leyendo tarjeta: " + e.getMessage());
        }
        return null;
    }
}
