package com.application;

import com.controller.*;
import com.exceptions.*;
import com.model.*;
import java.time.LocalDateTime;
import java.util.List;

public class Testes {
    public static void main(String[] args) {
        // Obtém instâncias Singleton dos controllers
        AeronaveController aeronaveController = AeronaveController.getInstance();
        EmpresaAereaController empresaController = EmpresaAereaController.getInstance();
        PassageiroController passageiroController = PassageiroController.getInstance();
        VooController vooController = VooController.getInstance();
        PassagemController passagemController = PassagemController.getInstance();

        System.out.println("===== TESTES COMPLETOS DO SISTEMA =====");

        // ===== TESTES DE EMPRESA AÉREA =====
        System.out.println("\n=== TESTES DE EMPRESA AÉREA ===");

        try {
            // Cadastro de empresas
            System.out.println("\n1. Cadastrando empresas válidas...");
            empresaController.cadastrarEmpresa("Latam Airlines", "12345678901234", "latam", "senha123");
            empresaController.cadastrarEmpresa("Gol Linhas Aéreas", "98765432109876", "gol", "senha456");
            System.out.println("-> Sucesso: 2 empresas cadastradas");

            // Teste CNPJ duplicado
            System.out.println("\n2. Tentando cadastrar CNPJ duplicado...");
            try {
                empresaController.cadastrarEmpresa("Latam Clone", "12345678901234", "latam2", "senha789");
                System.out.println("-> Falha: Não lançou exceção para CNPJ duplicado");
            } catch (EmpresaAereaJaCadastradaException e) {
                System.out.println("-> Sucesso: " + e.getMessage());
            }

            // Busca de empresas
            System.out.println("\n3. Buscando empresa existente por ID...");
            EmpresaAerea latam = empresaController.buscarEmpresaPorId(1);
            System.out.println("-> Encontrada: " + latam.getNome());

            System.out.println("\n4. Buscando empresa por CNPJ...");
            EmpresaAerea gol = empresaController.buscarEmpresaPorCnpj("98765432109876");
            System.out.println("-> Encontrada: " + gol.getNome());

            System.out.println("\n5. Buscando empresa inexistente por ID...");
            try {
                empresaController.buscarEmpresaPorId(999);
                System.out.println("-> Falha: Não lançou exceção para ID inexistente");
            } catch (EmpresaAereaNaoEncontradaException e) {
                System.out.println("-> Sucesso: " + e.getMessage());
            }

            // Teste de login
            System.out.println("\n6. Testando login válido...");
            boolean loginValido = empresaController.testarLogin("latam", "senha123");
            System.out.println("-> Login válido (latam/senha123)? " + (loginValido ? "Sim" : "Não"));

            System.out.println("\n7. Testando login inválido...");
            boolean loginInvalido = empresaController.testarLogin("latam", "senhaErrada");
            System.out.println("-> Login válido (latam/senhaErrada)? " + (loginInvalido ? "Sim" : "Não"));

            // Listagem
            System.out.println("\n8. Listando todas empresas:");
            List<EmpresaAerea> empresas = empresaController.listarTodasEmpresas();
            empresas.forEach(e -> System.out.println("-> ID: " + e.getId() + " | Nome: " + e.getNome()));

            // Remoção
            System.out.println("\n9. Removendo empresa...");
            empresaController.removerEmpresa(2);
            try {
                empresaController.buscarEmpresaPorId(2);
                System.out.println("-> Falha: Não lançou exceção após remoção");
            } catch (EmpresaAereaNaoEncontradaException e) {
                System.out.println("-> Sucesso: Empresa removida com sucesso");
            }

            // ===== TESTES DE AERONAVE =====
            System.out.println("\n=== TESTES DE AERONAVE ===");

            // Cadastro de aeronaves
            System.out.println("\n10. Cadastrando aeronaves válidas...");
            int idEmpresa = 1;
            aeronaveController.cadastrarAeronave("Boeing 737", 30, 6, idEmpresa); // 180 assentos
            aeronaveController.cadastrarAeronave("Airbus A320", 25, 4, idEmpresa); // 100 assentos
            System.out.println("-> Sucesso: 2 aeronaves cadastradas");

            // Associar aeronave à empresa
            Aeronave boeing = aeronaveController.buscarAeronavePorId(1);
            boeing.setIdEmpresa(1); // Associando à Latam

            // Busca de aeronaves
            System.out.println("\n11. Buscando aeronave existente por ID...");
            Aeronave airbus = aeronaveController.buscarAeronavePorId(2);
            System.out.println("-> Encontrada: " + airbus.getModelo() + " com " + airbus.getTotalAssentos() + " assentos");

            System.out.println("\n12. Buscando aeronave inexistente por ID...");
            try {
                aeronaveController.buscarAeronavePorId(999);
                System.out.println("-> Falha: Não lançou exceção para ID inexistente");
            } catch (AeronaveNaoEncontradaException e) {
                System.out.println("-> Sucesso: " + e.getMessage());
            }

            // Listagem
            System.out.println("\n13. Listando todas aeronaves:");
            List<Aeronave> aeronaves = aeronaveController.listarTodasAeronaves();
            aeronaves.forEach(a -> System.out.println(
                    "-> ID: " + a.getId() + " | Modelo: " + a.getModelo() +
                            " | Assentos: " + a.getTotalAssentos() +
                            " | Empresa: " + a.getIdEmpresa()));

            // Remoção
            System.out.println("\n14. Removendo aeronave...");
            aeronaveController.removerAeronave(2);
            try {
                aeronaveController.buscarAeronavePorId(2);
                System.out.println("-> Falha: Não lançou exceção após remoção");
            } catch (AeronaveNaoEncontradaException e) {
                System.out.println("-> Sucesso: Aeronave removida com sucesso");
            }

            // ===== TESTES DE PASSAGEIRO =====
            System.out.println("\n=== TESTES DE PASSAGEIRO ===");

            // Cadastro de passageiros
            System.out.println("\n15. Cadastrando passageiros válidos...");
            passageiroController.cadastrarPassageiro("João Silva", "111.222.333-44", "11999999999", "joao", "senha123");
            passageiroController.cadastrarPassageiro("Maria Souza", "555.666.777-88", "21888888888", "maria", "senha456");
            System.out.println("-> Sucesso: 2 passageiros cadastrados");

            // Teste CPF duplicado
            System.out.println("\n16. Tentando cadastrar CPF duplicado...");
            try {
                passageiroController.cadastrarPassageiro("João Silva Clone", "111.222.333-44", "11999999999", "joao_clone", "senha789");
                System.out.println("-> Falha: Não lançou exceção para CPF duplicado");
            } catch (PassageiroJaCadastradoException e) {
                System.out.println("-> Sucesso: " + e.getMessage());
            }

            // Busca de passageiros
            System.out.println("\n17. Buscando passageiro existente por ID...");
            Passageiro joao = passageiroController.buscarPassageiroPorId(1);
            System.out.println("-> Encontrado: " + joao.getNome());

            System.out.println("\n18. Buscando passageiro por CPF...");
            Passageiro maria = passageiroController.buscarPassageiroPorCpf("555.666.777-88");
            System.out.println("-> Encontrada: " + maria.getNome());

            System.out.println("\n19. Buscando passageiro por login...");
            Passageiro joaoLogin = passageiroController.buscarPorLogin("joao");
            System.out.println("-> Encontrado: " + joaoLogin.getNome());

            System.out.println("\n20. Buscando passageiro inexistente por ID...");
            try {
                passageiroController.buscarPassageiroPorId(999);
                System.out.println("-> Falha: Não lançou exceção para ID inexistente");
            } catch (PassageiroNaoEncontradoException e) {
                System.out.println("-> Sucesso: " + e.getMessage());
            }

            // Teste de login
            System.out.println("\n21. Testando login válido...");
            boolean loginPassageiroValido = passageiroController.testarLogin("joao", "senha123");
            System.out.println("-> Login válido (joao/senha123)? " + (loginPassageiroValido ? "Sim" : "Não"));

            System.out.println("\n22. Testando login inválido...");
            boolean loginPassageiroInvalido = passageiroController.testarLogin("joao", "senhaErrada");
            System.out.println("-> Login válido (joao/senhaErrada)? " + (loginPassageiroInvalido ? "Sim" : "Não"));

            // Atualização
            System.out.println("\n23. Atualizando telefone do passageiro...");
            joao.setTelefone("11999990000");
            passageiroController.atualizarPassageiro(joao);
            System.out.println("-> Telefone atualizado para: " +
                    passageiroController.buscarPassageiroPorId(1).getTelefone());

            // Listagem
            System.out.println("\n24. Listando todos passageiros:");
            List<Passageiro> passageiros = passageiroController.listarTodosPassageiros();
            passageiros.forEach(p -> System.out.println(
                    "-> ID: " + p.getId() + " | Nome: " + p.getNome() + " | CPF: " + p.getCpf()));

            // ===== TESTES DE VOO =====
            System.out.println("\n=== TESTES DE VOO ===");

            // Cadastro de voos
            System.out.println("\n25. Cadastrando voo nacional...");
            vooController.cadastrarVooNacional("V123", "São Paulo", "Rio de Janeiro",
                    LocalDateTime.now().plusDays(1), 500.0, 1, 50.0, 1); // Aeronave Boeing 737, Empresa Latam
            System.out.println("-> Voo nacional cadastrado");

            System.out.println("\n26. Cadastrando voo internacional...");
            vooController.cadastrarVooInternacional("V456", "Rio de Janeiro", "Lisboa",
                    LocalDateTime.now().plusDays(2), 2000.0, 1, 100.0, 80.0, 1); // Aeronave Boeing 737, Empresa Latam
            System.out.println("-> Voo internacional cadastrado");

            // Teste voo duplicado
            System.out.println("\n27. Tentando cadastrar voo duplicado...");
            try {
                vooController.cadastrarVooNacional("V123", "Recife", "Fortaleza",
                        LocalDateTime.now().plusDays(3), 600.0, 1, 60.0, 1);
                System.out.println("-> Falha: Não lançou exceção para voo duplicado");
            } catch (VooJaCadastradoException e) {
                System.out.println("-> Sucesso: " + e.getMessage());
            }

            // Busca de voos
            System.out.println("\n28. Buscando voo existente...");
            Voo vooSp = vooController.buscarVoo("V123");
            System.out.println("-> Encontrado: " + vooSp.getOrigem() + "-" + vooSp.getDestino());

            System.out.println("\n29. Buscando voo inexistente...");
            try {
                vooController.buscarVoo("V999");
                System.out.println("-> Falha: Não lançou exceção para voo inexistente");
            } catch (VooNaoEncontradoException e) {
                System.out.println("-> Sucesso: " + e.getMessage());
            }

            // Cálculo de preço (polimorfismo)
            System.out.println("\n30. Calculando preço final (polimorfismo):");
            System.out.println("-> Voo V123 (Nacional): R$" + vooController.calcularPrecoFinalVoo("V123"));
            System.out.println("-> Voo V456 (Internacional): R$" + vooController.calcularPrecoFinalVoo("V456"));

            // Listagem
            System.out.println("\n31. Listando todos voos:");
            List<Voo> voos = vooController.listarTodosVoos();
            voos.forEach(v -> System.out.println(
                    "-> Voo: " + v.getNumeroVoo() + " | " + v.getOrigem() + "-" + v.getDestino() +
                            " | Assentos: " + v.getAssentosDisponiveis() + "/" + v.getAeronave().getTotalAssentos()));

            // ===== TESTES DE PASSAGEM =====
            System.out.println("\n=== TESTES DE PASSAGEM ===");

            // Compra de passagens
            System.out.println("\n32. Comprando passagem válida...");
            Passagem passagem1 = new Passagem(joao, vooSp, "A1");
            try {
                passagemController.comprarPassagem(passagem1);
                System.out.println("-> Passagem comprada com sucesso");
            } catch (Exception e) {
                System.out.println("-> Falha: " + e.getMessage());
            }

            System.out.println("\n33. Tentando comprar para assento já ocupado...");
            Passagem passagem2 = new Passagem(joao, vooSp, "A1"); // Mesmo assento
            try {
                passagemController.comprarPassagem(passagem2);
                System.out.println("-> Falha: Permitiu comprar passagem para assento ocupado");
            } catch (AssentoOcupadoException e) {
                System.out.println("-> Sucesso: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("-> Falha: Exceção inesperada: " + e.getClass().getSimpleName());
            }

            System.out.println("\n34. Tentando comprar para voo lotado...");

// Primeiro, lota todos os assentos do voo
            for (int i = 1; i <= 180; i++) {
                Passagem p = new Passagem(joao, vooSp, "A" + i);
                try {
                    passagemController.comprarPassagem(p);
                } catch (Exception e) {
                    System.out.println("Erro ao preencher assento A" + i + ": " + e.getMessage());
                }
            }

// Agora tenta comprar uma passagem a mais
            Passagem passagemLotada = new Passagem(joao, vooSp, "A181");
            try {
                passagemController.comprarPassagem(passagemLotada);
                System.out.println("-> Falha: Permitiu comprar em voo lotado");
            } catch (VooLotadoException e) {
                System.out.println("-> Sucesso: " + e.getMessage());
            }

            // Busca de passagens
            System.out.println("\n35. Buscando passagem existente...");
            try {
                Passagem p = passagemController.buscarPassagemPorId(passagem1.getId());
                System.out.println("-> Encontrada: Assento " + p.getAssento());
            } catch (PassagemNaoEncontradaException e) {
                System.out.println("-> Falha: " + e.getMessage());
            }

            System.out.println("\n36. Buscando passagem inexistente...");
            try {
                passagemController.buscarPassagemPorId(9999);
                System.out.println("-> Falha: Não lançou exceção para passagem inexistente");
            } catch (PassagemNaoEncontradaException e) {
                System.out.println("-> Sucesso: " + e.getMessage());
            }

            // Listagens
            System.out.println("\n37. Listando todas passagens (apenas as 5 primeiras):");
            List<Passagem> passagens = passagemController.listarTodasPassagens();
            passagens.stream().limit(5).forEach(p -> System.out.println(
                    "-> ID: " + p.getId() + " | Assento: " + p.getAssento() +
                            " | Voo: " + p.getVoo().getNumeroVoo() +
                            " | Passageiro: " + p.getPassageiro().getNome()));
            System.out.println("-> Total de passagens: " + passagens.size());

            System.out.println("\n38. Listando passagens por passageiro (apenas as 5 primeiras):");
            List<Passagem> passagensJoao = passagemController.buscarPassagensPorPassageiro(1);
            passagensJoao.stream().limit(5).forEach(p -> System.out.println(
                    "-> Assento: " + p.getAssento() + " | Voo: " + p.getVoo().getNumeroVoo()));
            System.out.println("-> Total de passagens: " + passagensJoao.size());

            // Assentos disponíveis
            System.out.println("\n39. Listando assentos disponíveis (deveria estar vazio):");
            try {
                List<String> assentosDisponiveis = passagemController.listarAssentosDisponiveis("V123");
                System.out.println("Assentos disponíveis: " + assentosDisponiveis.size());
            } catch (VooNaoEncontradoException e) {
                System.out.println("-> Falha: " + e.getMessage());
            }

            // Remoção
            System.out.println("\n40. Removendo passagem...");
            try {
                int assentosAntes = vooSp.getAssentosDisponiveis();
                passagemController.removerPassagem(passagem1.getId());
                int assentosDepois = vooSp.getAssentosDisponiveis();

                System.out.println("-> Passagem removida com sucesso");
                System.out.println("Assentos antes: " + assentosAntes +
                        " | Assentos depois: " + assentosDepois);
            } catch (PassagemNaoEncontradaException e) {
                System.out.println("-> Falha: " + e.getMessage());
            }

            // Remoção de voo
            System.out.println("\n41. Removendo voo internacional...");
            try {
                vooController.removerVoo("V456");
                System.out.println("-> Verificando se voo foi removido...");
                vooController.buscarVoo("V456"); // Isso deve lançar exceção
                System.out.println("-> Falha: Voo ainda existe após remoção");
            } catch (VooNaoEncontradoException e) {
                System.out.println("-> Sucesso: Voo removido corretamente");
            }

            System.out.println("\n===== TODOS OS TESTES CONCLUÍDOS COM SUCESSO! =====");

        } catch (Exception e) {
            System.err.println("\n===== ERRO INESPERADO DURANTE OS TESTES =====");
            e.printStackTrace();
        }
    }
}