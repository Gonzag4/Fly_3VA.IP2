package com.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public abstract class Voo implements Calculavel, Reservavel {
    private String numeroVoo;
    private String origem;
    private String destino;
    private LocalDateTime dataHora;
    private double precoBase;
    private int assentosDisponiveis;
    private Aeronave aeronave;
    private Set<String> assentosOcupados;

    public Voo(String numeroVoo, String origem, String destino,
               LocalDateTime dataHora, double precoBase,
               int assentosDisponiveis, Aeronave aeronave) {
        this.numeroVoo = numeroVoo;
        this.origem = origem;
        this.destino = destino;
        this.dataHora = dataHora;
        this.precoBase = precoBase;
        this.assentosDisponiveis = assentosDisponiveis;
        this.aeronave = aeronave;
        this.assentosOcupados = new HashSet<>();
    }

    // Getters e Setters
    public String getNumeroVoo() { return numeroVoo; }
    public String getOrigem() { return origem; }
    public String getDestino() { return destino; }
    public LocalDateTime getDataHora() { return dataHora; }
    public double getPrecoBase() { return precoBase; }
    public int getAssentosDisponiveis() { return assentosDisponiveis; }
    public Aeronave getAeronave() { return aeronave; }

    // Implementação da interface Reservavel
    @Override
    public boolean reservar() {
        if (assentosDisponiveis > 0) {
            assentosDisponiveis--;
            return true;
        }
        return false;
    }

    @Override
    public boolean cancelarReserva() {
        assentosDisponiveis++;
        return true;
    }

    public boolean isAssentoDisponivel(String assento) {
        return !assentosOcupados.contains(assento) && assentosDisponiveis > 0;
    }
    public boolean reservarAssento(String assento) {
        if (assentosDisponiveis <= 0 || assentosOcupados.contains(assento)) {
            return false;
        }
        assentosOcupados.add(assento);
        assentosDisponiveis--;
        return true;
    }

    public boolean liberarAssento(String assento) {
        if (assentosOcupados.remove(assento)) {
            assentosDisponiveis++;
            return true;
        }
        return false;
    }

    // Método abstrato da interface Calculavel
    @Override
    public abstract double calcularPrecoFinal();
}