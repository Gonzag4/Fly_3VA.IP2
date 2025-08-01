package com.model;

/**
 * Classe que representa uma aeronave
 */
public class Aeronave {
    private static int contador = 1;

    private int id;
    private String modelo;
    private int fileiras;
    private int assentosPorFileira;
    private int idEmpresa;

    public Aeronave(String modelo, int fileiras, int assentosPorFileira) {
        this.id = contador++;
        this.modelo = modelo;
        this.fileiras = fileiras;
        this.assentosPorFileira = assentosPorFileira;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getModelo() { return modelo; }
    public int getFileiras() { return fileiras; }
    public int getAssentosPorFileira() { return assentosPorFileira; }
    public int getTotalAssentos() { return fileiras * assentosPorFileira; }
    public int getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(int idEmpresa) { this.idEmpresa = idEmpresa; }
}