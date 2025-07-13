// Archivo: Controlador/GestorAhorro.java
package Controlador;

public class GestorAhorro {
    private final GestorDatos datos;

    public GestorAhorro(GestorDatos datos) {
        this.datos = datos;
    }

    public void definirMeta(double meta) {
        datos.guardarMeta(meta);
    }

    public double obtenerMetaActual() {
        return datos.cargarMeta();
    }
}
