// Archivo: Controlador/GestorBuscador.java
package Controlador;

import Modelo.Gasto;

import java.util.List;

public class GestorBuscador {
    private final GestorDatos gestorDatos;

    public GestorBuscador(GestorDatos gestorDatos) {
        this.gestorDatos = gestorDatos;
    }

    public List<Gasto> buscarPorCategoria(String categoria) {
        return gestorDatos.buscarPorCategoria(categoria);
    }

    public List<Gasto> buscarPorFecha(String fecha) {
        return gestorDatos.buscarPorFecha(fecha);
    }


}