package Controlador;

import Modelo.Gasto;
import Modelo.Usuario;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestorDatosTest {

    @Test
    void testRegistrarGastoWithValidData() throws IOException {
        // Arrange
        Usuario usuario = new Usuario("testUser","23");
        GestorDatos gestorDatos = new GestorDatos(usuario);
        String gastosFile = "data/gastos_testUser.txt";
        Files.deleteIfExists(Paths.get(gastosFile));

        // Mock user input
        InputStream in = new ByteArrayInputStream("Alimentación\n50.0\nCena en restaurante\n".getBytes());
        System.setIn(in);

        // Act
        gestorDatos.registrarGasto();

        // Assert
        assertTrue(Files.exists(Paths.get(gastosFile)));

        List<String> lines = Files.readAllLines(Paths.get(gastosFile));
        assertEquals(1, lines.size());
        assertTrue(lines.get(0).contains("Alimentación"));
        assertTrue(lines.get(0).contains("50.0"));
        assertTrue(lines.get(0).contains("Cena en restaurante"));

        // Cleanup
        Files.deleteIfExists(Paths.get(gastosFile));
    }

    @Test
    void testRegistrarGastoWithInvalidCategory() throws IOException {
        // Arrange
        Usuario usuario = new Usuario("testUser", "12");
        GestorDatos gestorDatos = new GestorDatos(usuario);
        String gastosFile = "data/gastos_testUser.txt";
        Files.deleteIfExists(Paths.get(gastosFile));

        // Mock user input
        InputStream in = new ByteArrayInputStream("InvalidCategory\n".getBytes());
        System.setIn(in);

        // Act
        gestorDatos.registrarGasto();

        // Assert
        assertFalse(Files.exists(Paths.get(gastosFile)));
    }

    @Test
    void testRegistrarGastoWithLargeAmount() throws IOException {
        // Arrange
        Usuario usuario = new Usuario("testUser","12");
        GestorDatos gestorDatos = new GestorDatos(usuario);
        String gastosFile = "data/gastos_testUser.txt";
        Files.deleteIfExists(Paths.get(gastosFile));

        // Mock user input
        InputStream in = new ByteArrayInputStream("Transporte\n100000.5\nViaje largo\n".getBytes());
        System.setIn(in);

        // Act
        gestorDatos.registrarGasto();

        // Assert
        assertTrue(Files.exists(Paths.get(gastosFile)));

        List<String> lines = Files.readAllLines(Paths.get(gastosFile));
        assertEquals(1, lines.size());
        assertTrue(lines.get(0).contains("Transporte"));
        assertTrue(lines.get(0).contains("100000.5"));
        assertTrue(lines.get(0).contains("Viaje largo"));

        // Cleanup
        Files.deleteIfExists(Paths.get(gastosFile));
    }

    @Test
    void testRegistrarGastoEmptyCategory() throws IOException {
        // Arrange
        Usuario usuario = new Usuario("testUser","12");
        GestorDatos gestorDatos = new GestorDatos(usuario);
        String gastosFile = "data/gastos_testUser.txt";
        Files.deleteIfExists(Paths.get(gastosFile));

        // Mock user input
        InputStream in = new ByteArrayInputStream("\n".getBytes());
        System.setIn(in);

        // Act
        gestorDatos.registrarGasto();

        // Assert
        assertFalse(Files.exists(Paths.get(gastosFile)));
    }
}