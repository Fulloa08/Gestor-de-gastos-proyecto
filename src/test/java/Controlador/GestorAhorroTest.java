// Archivo: test/Controlador/GestorAhorroTest.java
package Controlador;

import Modelo.Usuario;
import org.junit.jupiter.api.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class GestorAhorroTest {
    private GestorDatos gestorDatos;
    private GestorAhorro gestorAhorro;
    private final String usuarioPrueba = "testuser";
    private final String clavePrueba="13";
    private File metaFile;

    @BeforeEach
    void setUp() {
        gestorDatos = new GestorDatos(new Usuario(usuarioPrueba,clavePrueba));
        gestorAhorro = new GestorAhorro(gestorDatos);
        metaFile = new File("data/meta_" + usuarioPrueba + ".txt");
        metaFile.delete();
    }

    @Test
    void testDefinirYObtenerMeta() {
        gestorAhorro.definirMeta(75000.0);
        double meta = gestorAhorro.obtenerMetaActual();
        assertEquals(75000.0, meta);
    }

    @AfterEach
    void tearDown() {
        metaFile.delete();
    }

    // Archivo: test/Controlador/GestorAhorroTest.java (extensión)
    @Test
    void testDefinirMetaCero() {
        gestorAhorro.definirMeta(0);
        assertEquals(0, gestorAhorro.obtenerMetaActual(), "La meta debe ser 0 si así se definió");
    }

    @Test
    void testDefinirMetaNegativa() {
        gestorAhorro.definirMeta(-5000);
        assertEquals(-5000, gestorAhorro.obtenerMetaActual(), "La meta negativa debe ser almacenada tal cual");
    }

}
