package org.example;

public class Gasto {
    private String fecha;
    private String categoria;
    private double monto;
    private String detalle;

    public Gasto(String fecha, String categoria, double monto, String detalle){
        this.fecha=fecha;
        this.categoria=categoria;
        this.monto=monto;
        this.detalle=detalle;
    }

    public String getFecha(){
        return fecha;
    }

    public String getCategoria(){
        return categoria;
    }

    public double getMonto(){
        return monto;
    }

    public String getDetalle(){
        return detalle;
    }
}