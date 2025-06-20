package Modelo;

public class Tarjeta {
    private String numero;
    private double saldo;

    public Tarjeta(String numero, double saldoInicial) {
        this.numero = numero;
        this.saldo = saldoInicial;
    }

    // Recarga la tarjeta con un monto específico
    public void recargar(double monto) {
        if (monto > 0) saldo += monto; // Solo recarga si el monto es positivo
    }

    // Descuenta un monto de la tarjeta
    public void descontar(double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto; // Solo descuenta si hay suficiente saldo
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumero() {
        return numero;
    }
}
