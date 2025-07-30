package com.model;

import java.time.LocalDateTime;


/**
 * Classe que representa um voo nacional
 * Requisito 2: Herda de Voo e implementa polimorfismo em calcularPrecoFinal()
 */
public class VooNacional extends Voo {
    private double taxaEmbarque;

    public VooNacional(String numeroVoo, String origem, String destino,
                       LocalDateTime dataHora, double precoBase,
                       int assentosDisponiveis, double taxaEmbarque) {
        super(numeroVoo, origem, destino, dataHora, precoBase, assentosDisponiveis);
        this.taxaEmbarque = taxaEmbarque;
    }

    // Requisito 2: Polimorfismo - implementação específica para voo nacional
    @Override
    public double calcularPrecoFinal() {
        return getPrecoBase() + taxaEmbarque;
    }

    public double getTaxaEmbarque() { return taxaEmbarque; }
}