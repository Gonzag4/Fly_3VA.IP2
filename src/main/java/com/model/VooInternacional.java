package com.model;

import java.time.LocalDateTime;

public class VooInternacional extends Voo {
    private double taxaEmbarque;
    private double taxaAlfandega;

    public VooInternacional(String numeroVoo, String origem, String destino,
                            LocalDateTime dataHora, double precoBase,
                            int assentosDisponiveis, double taxaEmbarque,
                            double taxaAlfandega) {
        super(numeroVoo, origem, destino, dataHora, precoBase, assentosDisponiveis);
        this.taxaEmbarque = taxaEmbarque;
        this.taxaAlfandega = taxaAlfandega;
    }

    @Override
    public double calcularPrecoFinal() {
        return getPrecoBase() + taxaEmbarque + taxaAlfandega;
    }

    public double getTaxaEmbarque() { return taxaEmbarque; }
    public double getTaxaAlfandega() { return taxaAlfandega; }
}