// Archivo: Modelo/Tarjeta.java
package Modelo;

public class Tarjeta {
    private String numero;
    private double saldo;

    public Tarjeta(String numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }
    // corregido
}