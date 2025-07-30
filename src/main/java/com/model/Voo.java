package com.model;

import java.time.LocalDateTime;


/**
 * Classe abstrata que representa um voo genérico
 * Requisito 1: Classe com atributos privados, métodos públicos e construtor
 * Requisito 2: Classe base para herança (VooNacional e VooInternacional)
 * Requisito 3: Implementa as interfaces Calculavel e Reservavel
 */
public abstract class Voo implements Calculavel, Reservavel {

    // Requisito 3: Implementa duas interfaces
    private String numeroVoo;
    private String origem;
    private String destino;
    private LocalDateTime dataHora;
    private double precoBase;
    private int assentosDisponiveis;
    private boolean reservado;

    public Voo(String numeroVoo, String origem, String destino,
               LocalDateTime dataHora, double precoBase, int assentosDisponiveis) {
        this.numeroVoo = numeroVoo;
        this.origem = origem;
        this.destino = destino;
        this.dataHora = dataHora;
        this.precoBase = precoBase;
        this.assentosDisponiveis = assentosDisponiveis;
        this.reservado = false;
    }

    // Getters e Setters (Requisito 1: encapsulamento)
    public String getNumeroVoo() { return numeroVoo; }
    public String getOrigem() { return origem; }
    public String getDestino() { return destino; }
    public LocalDateTime getDataHora() { return dataHora; }
    public double getPrecoBase() { return precoBase; }
    public int getAssentosDisponiveis() {

        return assentosDisponiveis;

    }

    public void setAssentosDisponiveis(int assentosDisponiveis) {

        this.assentosDisponiveis = assentosDisponiveis;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    // Requisito 2: Polimorfismo - método abstrato
    @Override
    public abstract double calcularPrecoFinal();

    // Requisito 3: Implementação da interface Reservavel
    @Override
    public boolean reservar() {
        if (assentosDisponiveis > 0 && !reservado) {
            assentosDisponiveis--;
            reservado = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean cancelarReserva() {
        if (reservado) {
            assentosDisponiveis++;
            reservado = false;
            return true;
        }
        return false;
    }

}
