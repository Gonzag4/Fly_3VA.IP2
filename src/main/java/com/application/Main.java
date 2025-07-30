package com.application;


import com.controller.PassageiroController;
import com.controller.VooController;
import com.exceptions.*;
import com.model.Passageiro;
import com.model.Voo;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Obtém instâncias Singleton dos controllers
        PassageiroController passageiroController = PassageiroController.getInstance();
        VooController vooController = VooController.getInstance();

        System.out.println("===== TESTE DO SISTEMA DE PASSAGENS AÉREAS =====");
        System.out.println("=== TESTES DE PASSAGEIRO ===");

        try {
            // Teste 1: Cadastro bem-sucedido de passageiros
            System.out.println("\nTeste 1: Cadastrando passageiros válidos...");
            passageiroController.cadastrarPassageiro("João Silva", "111.222.333-44", "11999999999");
            passageiroController.cadastrarPassageiro("Maria Souza", "555.666.777-88", "21888888888");
            passageiroController.cadastrarPassageiro("Carlos Oliveira", "999.888.777-66", "31777777777");
            System.out.println("Sucesso: 3 passageiros cadastrados!");

            // Teste 2: Tentativa de cadastro com CPF duplicado
            System.out.println("\nTeste 2: Tentando cadastrar com CPF duplicado...");
            try {
                passageiroController.cadastrarPassageiro("João Silva Clone", "111.222.333-44", "11999999999");
                System.out.println("Falha: Exceção não foi lançada para CPF duplicado!");
            } catch (PassageiroJaCadastradoException e) {
                System.out.println("Sucesso: Exceção capturada - " + e.getMessage());
            }

            // Teste 3: Busca de passageiro existente
            System.out.println("\nTeste 3: Buscando passageiro por ID existente...");
            Passageiro joao = passageiroController.buscarPassageiroPorId(1);
            System.out.println("Sucesso: Passageiro encontrado - " + joao.getNome());

            // Teste 4: Busca de passageiro inexistente
            System.out.println("\nTeste 4: Buscando passageiro por ID inexistente...");
            try {
                passageiroController.buscarPassageiroPorId(999);
                System.out.println("Falha: Exceção não foi lançada para ID inexistente!");
            } catch (PassageiroNaoEncontradoException e) {
                System.out.println("Sucesso: Exceção capturada - " + e.getMessage());
            }

            // Teste 5: Atualização de passageiro
            System.out.println("\nTeste 5: Atualizando dados do passageiro...");
            joao.setTelefone("11999999900");
            passageiroController.atualizarPassageiro(joao);
            Passageiro joaoAtualizado = passageiroController.buscarPassageiroPorId(1);
            System.out.println("Sucesso: Telefone atualizado para " + joaoAtualizado.getTelefone());

            // Teste 6: Remoção de passageiro
            System.out.println("\nTeste 6: Removendo passageiro...");
            passageiroController.removerPassageiro(3);
            System.out.println("Sucesso: Passageiro removido. Tentando buscar...");
            try {
                passageiroController.buscarPassageiroPorId(3);
                System.out.println("Falha: Exceção não foi lançada para passageiro removido!");
            } catch (PassageiroNaoEncontradoException e) {
                System.out.println("Sucesso: Exceção capturada - " + e.getMessage());
            }

            // Teste 7: Listagem de todos os passageiros
            System.out.println("\nTeste 7: Listando todos os passageiros...");
            List<Passageiro> todosPassageiros = passageiroController.listarTodosPassageiros();
            for (Passageiro p : todosPassageiros) {
                System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() +
                        " | CPF: " + p.getCpf() + " | Tel: " + p.getTelefone());
            }

            System.out.println("\n=== TESTES DE VOO ===");

            // Teste 8: Cadastro de voo nacional
            System.out.println("\nTeste 8: Cadastrando voo nacional...");
            vooController.cadastrarVooNacional("V123", "São Paulo", "Rio de Janeiro",
                    LocalDateTime.of(2023, 12, 15, 10, 30), 500.0, 100, 50.0);
            System.out.println("Sucesso: Voo nacional cadastrado!");

            // Teste 9: Cadastro de voo internacional
            System.out.println("\nTeste 9: Cadastrando voo internacional...");
            vooController.cadastrarVooInternacional("V456", "Rio de Janeiro", "Lisboa",
                    LocalDateTime.of(2023, 12, 20, 22, 15), 2000.0, 150, 100.0, 80.0);
            System.out.println("Sucesso: Voo internacional cadastrado!");

            // Teste 10: Tentativa de cadastro com número de voo duplicado
            System.out.println("\nTeste 10: Tentando cadastrar voo com número duplicado...");
            try {
                vooController.cadastrarVooNacional("V123", "Brasília", "Curitiba",
                        LocalDateTime.of(2023, 12, 18, 8, 0), 400.0, 80, 40.0);
                System.out.println("Falha: Exceção não foi lançada para voo duplicado!");
            } catch (VooJaCadastradoException e) {
                System.out.println("Sucesso: Exceção capturada - " + e.getMessage());
            }

            // Teste 11: Busca de voo existente
            System.out.println("\nTeste 11: Buscando voo existente...");
            Voo vooSP = vooController.buscarVoo("V123");
            System.out.println("Sucesso: Voo encontrado - " + vooSP.getOrigem() + " para " + vooSP.getDestino());

            // Teste 12: Busca de voo inexistente
            System.out.println("\nTeste 12: Buscando voo inexistente...");
            try {
                vooController.buscarVoo("V999");
                System.out.println("Falha: Exceção não foi lançada para voo inexistente!");
            } catch (VooNaoEncontradoException e) {
                System.out.println("Sucesso: Exceção capturada - " + e.getMessage());
            }

            // Teste 13: Cálculo de preço final (polimorfismo)
            System.out.println("\nTeste 13: Calculando preços finais (polimorfismo)...");
            System.out.println("Preço voo nacional V123: R$" + vooController.calcularPrecoFinalVoo("V123"));
            System.out.println("Preço voo internacional V456: R$" + vooController.calcularPrecoFinalVoo("V456"));

            // Teste 14: Atualização de voo
            System.out.println("\nTeste 14: Atualizando voo...");
            vooSP = vooController.buscarVoo("V123");
            vooSP.setOrigem("São Paulo (GRU)");
            vooController.atualizar(vooSP);
            Voo vooAtualizado = vooController.buscarVoo("V123");
            System.out.println("Sucesso: Origem atualizada para " + vooAtualizado.getOrigem());

            // Teste 15: Remoção de voo
            System.out.println("\nTeste 15: Removendo voo...");
            vooController.removerVoo("V456");
            System.out.println("Sucesso: Voo removido. Tentando buscar...");
            try {
                vooController.buscarVoo("V456");
                System.out.println("Falha: Exceção não foi lançada para voo removido!");
            } catch (VooNaoEncontradoException e) {
                System.out.println("Sucesso: Exceção capturada - " + e.getMessage());
            }

            // Teste 16: Listagem de todos os voos
            System.out.println("\nTeste 16: Listando todos os voos...");
            List<Voo> todosVoos = vooController.listarTodosVoos();
            for (Voo v : todosVoos) {
                System.out.println("Voo: " + v.getNumeroVoo() + " | " + v.getOrigem() +
                        " -> " + v.getDestino() + " | " + v.getDataHora() +
                        " | R$" + v.calcularPrecoFinal());
            }

            System.out.println("\n===== TODOS OS TESTES FORAM CONCLUÍDOS COM SUCESSO! =====");

        } catch (Exception e) {
            System.err.println("\n===== ERRO INESPERADO =====");
            e.printStackTrace();
        }
    }
}