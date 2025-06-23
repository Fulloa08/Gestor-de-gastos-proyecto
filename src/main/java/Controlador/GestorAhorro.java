package Controlador;

import Modelo.MetaAhorro;

import java.util.ArrayList;
import java.util.List;

public class GestorAhorro {
    private List<MetaAhorro> metas;

    public GestorAhorro() {
        this.metas = new ArrayList<>();
    }

    public void agregarMeta(MetaAhorro meta) {
        metas.add(meta);
        System.out.println("Meta agregada para el mes: " + meta.getMes());
    }

    public List<MetaAhorro> obtenerMetas() {
        return metas;
    }

    public void eliminarMeta(String mes) {
        metas.removeIf(meta -> meta.getMes().equalsIgnoreCase(mes));
        System.out.println("Meta para el mes " + mes + " eliminada.");
    }

    public MetaAhorro buscarMeta(String mes) {
        for (MetaAhorro meta : metas) {
            if (meta.getMes().equalsIgnoreCase(mes)) {
                return meta;
            }
        }
        return null;
    }

    public void mostrarMetas() {
        if (metas.isEmpty()) {
            System.out.println("No hay metas de ahorro registradas.");
        } else {
            System.out.println("Metas de ahorro:");
            for (MetaAhorro meta : metas) {
                System.out.println("Mes: " + meta.getMes() + " - Monto: $" + meta.getMontoMeta());
            }
        }
    }
}
