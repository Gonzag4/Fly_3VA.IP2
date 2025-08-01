package com.model;

public class EmpresaAerea {
    private static int contador = 1;
    private int id;
    private String nome;
    private String cnpj;
    private String login;
    private String senha;

    public EmpresaAerea(String nome, String cnpj, String login, String senha) {
        this.id = contador++;
        this.nome = nome;
        this.cnpj = cnpj;
        this.login = login;
        this.senha = senha;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}