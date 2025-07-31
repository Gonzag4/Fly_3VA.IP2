package com.model;

/**
 * Classe que representa um passageiro do sistema
 * Requisito 1: Classe com atributos privados, métodos públicos e construtores adequados
 * Requisito 9: Uso de atributo estático para contador automático de IDs
 */
public class Passageiro {
    private static int contador = 1; // Requisito 9: Atributo estático

    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String login;
    private String senha;

    public Passageiro(String nome, String cpf, String telefone, String login, String senha) {
        this.id = contador++;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.login = login;
        this.senha = senha;
    }

    // Getters e Setters (Requisito 1: encapsulamento)
    public int getId() {
        return id;
    }

    public String getNome() {

        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;

    }

    public String getCpf() {
        return cpf;

    }
    public void setCpf(String cpf) {
        this.cpf = cpf;

    }
    public String getTelefone() {
        return telefone;

    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}