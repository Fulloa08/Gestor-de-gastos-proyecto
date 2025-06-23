package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void testTotal() {
        String[][] matriz ={
                {"100.0"},
                {"200.0"},
                {"300.0"},
        };
        double sumaEsperada=600.0;
        double sumaCalculada=Main.total(matriz);
        assertEquals(sumaEsperada, sumaCalculada);
    }

    @Test
    void testPromedio() {
        String[][] matriz = {
                {"10.0"},
                {"20.0"},
                {"30.0"}
        };
        double promedioEsperado = 20.0;
        double promedioCalculado = Main.promedio(matriz);

        assertEquals(promedioEsperado, promedioCalculado);
}

    @Test
    void testVisualizarMetasNoNull() {
        String[][] metas = {
                {"Enero", "500"},
                {"Febrero", "400"},
                {"Marzo", "600"},
                {"Abril", "700"},
                {"Mayo", "300"}
        };

        assertNotNull(metas[0][0]);
        assertEquals("Enero", metas[0][0]);
        assertEquals("500", metas[0][1]);
    }
}


