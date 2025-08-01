package com.model;

public class Passagem implements Reservavel {
    private static int contador = 1;
    private int id;
    private Passageiro passageiro;
    private Voo voo;
    private double precoPago;
    private String assento;

    public Passagem(Passageiro passageiro, Voo voo, String assento) {
        this.id = contador++;
        this.passageiro = passageiro;
        this.voo = voo;
        this.assento = assento;
        this.precoPago = voo.calcularPrecoFinal();
    }

    // Getters e Setters
    public int getId() { return id; }
    public Passageiro getPassageiro() { return passageiro; }
    public Voo getVoo() { return voo; }
    public double getPrecoPago() { return precoPago; }
    public void setPrecoPago(double precoPago) { this.precoPago = precoPago; }
    public String getAssento() { return assento; }

    @Override
    public boolean reservar() {
        return voo.reservar();
    }

    @Override
    public boolean cancelarReserva() {
        return voo.cancelarReserva();
    }
}