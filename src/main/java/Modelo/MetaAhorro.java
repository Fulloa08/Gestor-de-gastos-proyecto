package Modelo;

public class MetaAhorro {
    private double montoMeta;
    private String mes;

    public MetaAhorro(double montoMeta, String mes) {
        this.montoMeta = montoMeta;
        this.mes = mes;
    }

    public double getMontoMeta() {
        return montoMeta;
    }

    public String getMes() {
        return mes;
    }
}
