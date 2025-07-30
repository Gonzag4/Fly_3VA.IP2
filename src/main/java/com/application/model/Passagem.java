package com.application.model;

public class Passagem implements Reservavel { // Requisito 3: Implementa interface
    private static int contador = 1; // Requisito 9: Atributo estático

    private int id;
    private Passageiro passageiro;
    private Voo voo;
    private double precoPago;
    private String assento;
    private boolean reservada;

    public Passagem(Passageiro passageiro, Voo voo, String assento) {
        this.id = contador++;
        this.passageiro = passageiro;
        this.voo = voo;
        this.assento = assento;
        this.precoPago = voo.calcularPrecoFinal();
        this.reservada = false;
    }

    // Getters
    public int getId() { return id; }
    public Passageiro getPassageiro() { return passageiro; }
    public Voo getVoo() { return voo; }
    public double getPrecoPago() { return precoPago; }
    public String getAssento() { return assento; }

    // Requisito 3: Implementação da interface Reservavel
    @Override
    public boolean reservar() {
        if (!reservada && voo.reservar()) {
            reservada = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean cancelarReserva() {
        if (reservada && voo.cancelarReserva()) {
            reservada = false;
            return true;
        }
        return false;
    }
}