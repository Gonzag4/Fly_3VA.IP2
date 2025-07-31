package com.application;

import com.controller.PassageiroController;
import com.controller.VooController;
import com.controller.PassagemController;
import com.exceptions.*;
import com.model.Passageiro;
import com.model.Voo;
import com.model.Passagem;
import java.time.LocalDateTime;
import java.util.List;

public class Testes {
    public static void main(String[] args) {
        // Obtém instâncias Singleton dos controllers
        PassageiroController passageiroController = PassageiroController.getInstance();
        VooController vooController = VooController.getInstance();
        PassagemController passagemController = PassagemController.getInstance();

        System.out.println("===== TESTE COMPLETO DO SISTEMA =====");
        System.out.println("=== TESTES DE PASSAGEIRO ===");

        try {
            // ---- CADASTRO DE PASSAGEIROS ----
            System.out.println("\n1. Cadastrando passageiros válidos...");
            passageiroController.cadastrarPassageiro("João Silva", "111.222.333-44", "11999999999", "joao", "senha123");
            System.out.println("-> Sucesso: 1 passageiro cadastrado");
            passageiroController.cadastrarPassageiro("Maria Souza", "555.666.777-88", "21888888888", "maria", "senha123");
            System.out.println("-> Sucesso: 2 passageiros cadastrados");

            // Teste CPF duplicado
            System.out.println("\n2. Tentando cadastrar CPF duplicado...");
            try {
                passageiroController.cadastrarPassageiro("João Silva Clone", "111.222.333-44", "11999999999", "joao_clone", "senha456");
                System.out.println("-> Falha: Não lançou exceção para CPF duplicado");
            } catch (PassageiroJaCadastradoException e) {
                System.out.println("-> Sucesso: " + e.getMessage());
            }

            // ---- BUSCA DE PASSAGEIROS ----
            System.out.println("\n3. Buscando passageiro existente por ID...");
            Passageiro joao = passageiroController.buscarPassageiroPorId(1);
            System.out.println("-> Encontrado: " + joao.getNome());

            System.out.println("\n4. Buscando passageiro inexistente por ID...");
            try {
                passageiroController.buscarPassageiroPorId(999);
                System.out.println("-> Falha: Não lançou exceção para ID inexistente");
            } catch (PassageiroNaoEncontradoException e) {
                System.out.println("-> Sucesso: " + e.getMessage());
            }

            // ---- ATUALIZAÇÃO E REMOÇÃO ----
            System.out.println("\n5. Atualizando telefone do passageiro...");
            joao.setTelefone("11999990000");
            passageiroController.atualizarPassageiro(joao);
            System.out.println("-> Telefone atualizado para: " +
                    passageiroController.buscarPassageiroPorId(1).getTelefone());

            System.out.println("\n6. Removendo passageiro...");
            passageiroController.removerPassageiro(2);
            try {
                passageiroController.buscarPassageiroPorId(2);
                System.out.println("-> Falha: Não lançou exceção após remoção");
            } catch (PassageiroNaoEncontradoException e) {
                System.out.println("-> Sucesso: " + e.getMessage());
            }

            // ---- LISTAGEM ----
            System.out.println("\n7. Listando todos passageiros:");
            List<Passageiro> passageiros = passageiroController.listarTodosPassageiros();
            passageiros.forEach(p -> System.out.println(
                    "-> ID: " + p.getId() + " | Nome: " + p.getNome() + " | CPF: " + p.getCpf()));

            System.out.println("\n=== TESTES DE VOO ===");

            // ---- CADASTRO DE VOOS ----
            System.out.println("\n8. Cadastrando voo nacional...");
            vooController.cadastrarVooNacional("V123", "São Paulo", "Rio de Janeiro",
                    LocalDateTime.now().plusDays(1), 500.0, 2, 50.0); // Apenas 2 assentos para testar lotação
            System.out.println("-> Voo nacional cadastrado");

            System.out.println("\n9. Cadastrando voo internacional...");
            vooController.cadastrarVooInternacional("V456", "Rio de Janeiro", "Lisboa",
                    LocalDateTime.now().plusDays(2), 2000.0, 150, 100.0, 80.0);
            System.out.println("-> Voo internacional cadastrado");

            // Teste voo duplicado
            System.out.println("\n10. Tentando cadastrar voo duplicado...");
            try {
                vooController.cadastrarVooNacional("V123", "Recife", "Fortaleza",
                        LocalDateTime.now().plusDays(3), 600.0, 100, 60.0);
                System.out.println("-> Falha: Não lançou exceção para voo duplicado");
            } catch (VooJaCadastradoException e) {
                System.out.println("-> Sucesso: " + e.getMessage());
            }

            // ---- BUSCA DE VOOS ----
            System.out.println("\n11. Buscando voo existente...");
            Voo vooSp = vooController.buscarVoo("V123");
            System.out.println("-> Encontrado: " + vooSp.getOrigem() + "-" + vooSp.getDestino());

            System.out.println("\n12. Buscando voo inexistente...");
            try {
                vooController.buscarVoo("V999");
                System.out.println("-> Falha: Não lançou exceção para voo inexistente");
            } catch (VooNaoEncontradoException e) {
                System.out.println("-> Sucesso: " + e.getMessage());
            }

            // ---- CÁLCULO DE PREÇO ----
            System.out.println("\n13. Calculando preço final (polimorfismo):");
            System.out.println("-> Voo V123 (Nacional): R$" + vooController.calcularPrecoFinalVoo("V123"));
            System.out.println("-> Voo V456 (Internacional): R$" + vooController.calcularPrecoFinalVoo("V456"));

            // ---- REMOÇÃO ----
            System.out.println("\n14. Removendo voo internacional...");
            vooController.removerVoo("V456");
            try {
                vooController.buscarVoo("V456");
                System.out.println("-> Falha: Não lançou exceção após remoção");
            } catch (VooNaoEncontradoException e) {
                System.out.println("-> Sucesso: " + e.getMessage());
            }

            System.out.println("\n=== TESTES DE PASSAGEM ===");

            // ---- COMPRA DE PASSAGENS ----
            Passageiro passageiro = passageiroController.buscarPassageiroPorId(1);
            Voo voo = vooController.buscarVoo("V123");

            System.out.println("\n15. Comprando passagem válida...");
            Passagem passagem1 = new Passagem(passageiro, voo, "A1");
            try {
                passagemController.comprarPassagem(passagem1);
                System.out.println("-> Passagem comprada com sucesso");
            } catch (Exception e) {
                System.out.println("-> Falha: " + e.getMessage());
            }

            System.out.println("\n16. Tentando comprar para assento já ocupado...");
            Passagem passagem2 = new Passagem(passageiro, voo, "A1"); // Mesmo assento
            try {
                passagemController.comprarPassagem(passagem2);
                System.out.println("-> Falha: Permitiu comprar passagem para assento ocupado");
            } catch (AssentoOcupadoException e) {
                System.out.println("-> Sucesso: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("-> Falha: Exceção inesperada: " + e.getClass().getSimpleName());
            }

            System.out.println("\n17. Tentando comprar para voo lotado...");
                // Primeiro compramos o segundo assento (A2)
            Passagem passagem3 = new Passagem(passageiro, voo, "A2");
            try {
                passagemController.comprarPassagem(passagem3);
                System.out.println("-> Passagem 3 comprada com sucesso (A2)");

                // AGORA tentamos comprar a terceira passagem (voo já está lotado)
                Passagem passagem4 = new Passagem(passageiro, voo, "A3");
                try {
                    passagemController.comprarPassagem(passagem4);
                    System.out.println("-> Falha: Permitiu comprar em voo lotado");
                } catch (VooLotadoException e) {
                    System.out.println("-> Sucesso (esperado): " + e.getMessage());
                }
            } catch (Exception e) {
                System.out.println("-> Falha inesperada ao comprar passagem 3: " + e.getMessage());
            }

            // Agora tentamos comprar a terceira passagem (voo só tem 2 assentos)
            Passagem passagem4 = new Passagem(passageiro, voo, "A3");
            try {
                passagemController.comprarPassagem(passagem4);
                System.out.println("-> Falha: Permitiu comprar em voo lotado");
            } catch (VooLotadoException e) {
                System.out.println("-> Sucesso (esperado): " + e.getMessage());
            } catch (Exception e) {
                System.out.println("-> Falha: Lançou exceção inesperada: " + e.getClass().getSimpleName());
            }

            // ---- BUSCA DE PASSAGENS ----
            System.out.println("\n18. Buscando passagem existente...");
            try {
                Passagem p = passagemController.buscarPassagemPorId(passagem1.getId());
                System.out.println("-> Encontrada: Assento " + p.getAssento());
            } catch (PassagemNaoEncontradaException e) {
                System.out.println("-> Falha: " + e.getMessage());
            }

            System.out.println("\n19. Buscando passagem inexistente...");
            try {
                passagemController.buscarPassagemPorId(9999);
                System.out.println("-> Falha: Não lançou exceção para passagem inexistente");
            } catch (PassagemNaoEncontradaException e) {
                System.out.println("-> Sucesso: " + e.getMessage());
            }

            // ---- LISTAGENS ----
            System.out.println("\n20. Listando todas passagens:");
            List<Passagem> passagens = passagemController.listarTodasPassagens();
            passagens.forEach(p -> System.out.println(
                    "-> ID: " + p.getId() + " | Assento: " + p.getAssento() + " | Voo: " + p.getVoo().getNumeroVoo()));

            System.out.println("\n21. Listando passagens por passageiro:");
            List<Passagem> passagensJoao = passagemController.buscarPassagensPorPassageiro(1);
            passagensJoao.forEach(p -> System.out.println("-> Assento: " + p.getAssento()));

            // ---- REMOÇÃO ----
            System.out.println("\n22. Removendo passagem...");
            try {
                passagemController.removerPassagem(passagem1.getId());
                System.out.println("-> Passagem removida com sucesso");
            } catch (PassagemNaoEncontradaException e) {
                System.out.println("-> Falha: " + e.getMessage());
            }

            // TESTE DE LOGIN
            System.out.println("\n=== TESTE DE LOGIN DE PASSAGEIRO ===");
            boolean loginValido = passageiroController.testarLogin("joao", "senha123");
            System.out.println("Login válido (joao/senha123)? " + (loginValido ? "Sim" : "Não"));

            boolean loginInvalido = passageiroController.testarLogin("maria", "senhaErrada");
            System.out.println("Login válido (maria/senhaErrada)? " + (loginInvalido ? "Sim" : "Não"));

            System.out.println("\n===== TODOS OS TESTES CONCLUÍDOS! =====");

        } catch (Exception e) {
            System.err.println("\n===== ERRO INESPERADO =====");
            e.printStackTrace();
        }
    }
}