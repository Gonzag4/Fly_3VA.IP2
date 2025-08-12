package com.application;

import com.controller.SistemaVendasPassagens;
import com.exceptions.*;
import com.model.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

//metodo scanner sem javafx, preparação para o front:

public class SistemaConsole {
    private static final SistemaVendasPassagens sistema = SistemaVendasPassagens.getInstance();
    private static final Scanner scanner = new Scanner(System.in);
    private static Passageiro passageiroLogado = null;
    private static EmpresaAerea empresaLogada = null;

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE VENDAS DE PASSAGENS AÉREAS ===");

        while (true) {
            if (passageiroLogado == null && empresaLogada == null) {
                menuPrincipal();
            } else if (passageiroLogado != null) {
                menuPassageiro();
            } else {
                menuEmpresa();
            }
        }
    }

    private static void menuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Login Passageiro");
        System.out.println("2. Cadastrar Passageiro");
        System.out.println("3. Login Empresa Aérea");
        System.out.println("4. Cadastrar Empresa Aérea");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        try {
            switch (opcao) {
                case 1 -> loginPassageiro();
                case 2 -> cadastrarPassageiro();
                case 3 -> loginEmpresa();
                case 4 -> cadastrarEmpresa();
                case 5 -> {
                    System.out.println("Saindo do sistema...");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida!");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void loginPassageiro() {
        System.out.println("\n=== LOGIN PASSAGEIRO ===");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        try {
            passageiroLogado = sistema.loginPassageiro(login, senha);
            System.out.println("Bem-vindo, " + passageiroLogado.getNome() + "!");
        } catch (PassageiroNaoEncontradoException e) {
            System.out.println("Erro no login: " + e.getMessage());
        }
    }

    private static void cadastrarPassageiro() {
        System.out.println("\n=== CADASTRO DE PASSAGEIRO ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        try {
            sistema.cadastrarPassageiro(nome, cpf, telefone, login, senha);
            System.out.println("Passageiro cadastrado com sucesso!");
        } catch (PassageiroJaCadastradoException e) {
            System.out.println("Erro no cadastro: " + e.getMessage());
        }
    }

    private static void cadastrarEmpresa() {
        System.out.println("\n=== CADASTRO DE EMPRESA AÉREA ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        try {
            sistema.cadastrarEmpresa(nome, cnpj, login, senha);
            System.out.println("Empresa aérea cadastrada com sucesso!");
        } catch (EmpresaAereaJaCadastradaException e) {
            System.out.println("Erro no cadastro: " + e.getMessage());
        }
    }

    private static void loginEmpresa() {
        System.out.println("\n=== LOGIN EMPRESA AÉREA ===");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        try {
            empresaLogada = sistema.loginEmpresa(login, senha);
            System.out.println("Bem-vindo, " + empresaLogada.getNome() + "!");
        } catch (EmpresaAereaNaoEncontradaException e) {
            System.out.println("Erro no login: " + e.getMessage());
        }
    }

    private static void menuPassageiro() {
        System.out.println("\n=== MENU PASSAGEIRO ===");
        System.out.println("1. Ver voos disponíveis");
        System.out.println("2. Comprar passagem");
        System.out.println("3. Ver minhas passagens");
        System.out.println("4. Cancelar passagem");
        System.out.println("5. Atualizar meus dados");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        try {
            switch (opcao) {
                case 1 -> listarVoosDisponiveis();
                case 2 -> comprarPassagem();
                case 3 -> listarPassagensPassageiro();
                case 4 -> cancelarPassagem();
                case 5 -> atualizarPassageiro();
                case 6 -> {
                    passageiroLogado = null;
                    System.out.println("Logout realizado com sucesso!");
                }
                default -> System.out.println("Opção inválida!");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void atualizarPassageiro() {
        System.out.println("\n=== ATUALIZAR DADOS ===");
        System.out.print("Novo nome (" + passageiroLogado.getNome() + "): ");
        String nome = scanner.nextLine();
        System.out.print("Novo telefone (" + passageiroLogado.getTelefone() + "): ");
        String telefone = scanner.nextLine();
        System.out.print("Nova senha: ");
        String senha = scanner.nextLine();

        passageiroLogado.setNome(nome.isEmpty() ? passageiroLogado.getNome() : nome);
        passageiroLogado.setTelefone(telefone.isEmpty() ? passageiroLogado.getTelefone() : telefone);
        passageiroLogado.setSenha(senha.isEmpty() ? passageiroLogado.getSenha() : senha);

        try {
            sistema.atualizarPassageiro(passageiroLogado);
            System.out.println("Dados atualizados com sucesso!");
        } catch (PassageiroNaoEncontradoException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    private static void listarVoosDisponiveis() {
        System.out.println("\n=== VOOS DISPONÍVEIS ===");
        List<Voo> voos = sistema.listarVoosDisponiveis();

        if (voos.isEmpty()) {
            System.out.println("Nenhum voo disponível no momento.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (Voo voo : voos) {
            System.out.println("\nVoo: " + voo.getNumeroVoo());
            System.out.println("Origem: " + voo.getOrigem() + " -> Destino: " + voo.getDestino());
            System.out.println("Data/Hora: " + voo.getDataHora().format(formatter));
            System.out.println("Assentos disponíveis: " + voo.getAssentosDisponiveis());
            System.out.println("Preço: R$" + String.format("%.2f", voo.calcularPrecoFinal()));
            System.out.println("Tipo: " + (voo instanceof VooInternacional ? "Internacional" : "Nacional"));
            System.out.println("Operado por: " + voo.getAeronave().getIdEmpresa());
        }
    }

    private static void comprarPassagem() {
        System.out.println("\n=== COMPRAR PASSAGEM ===");
        try {
            listarVoosDisponiveis();
            System.out.print("\nNúmero do voo: ");
            String numeroVoo = scanner.nextLine();

            // Listar assentos disponíveis
            List<String> assentos = sistema.listarAssentosDisponiveis(numeroVoo);
            System.out.println("Assentos disponíveis: " + assentos);

            System.out.print("Escolha um assento: ");
            String assento = scanner.nextLine();

            sistema.comprarPassagem(passageiroLogado.getId(), numeroVoo, assento);
            System.out.println("Passagem comprada com sucesso!");
        } catch (VooNaoEncontradoException | PassageiroNaoEncontradoException |
                 VooLotadoException | AssentoOcupadoException | PassagemJaCadastradaException e) {
            System.out.println("Erro ao comprar passagem: " + e.getMessage());
        }
    }

    private static void listarPassagensPassageiro() {
        System.out.println("\n=== MINHAS PASSAGENS ===");
        try {
            List<Passagem> passagens = sistema.buscarPassagensPorPassageiro(passageiroLogado.getId());

            if (passagens.isEmpty()) {
                System.out.println("Você não possui passagens compradas.");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            for (Passagem p : passagens) {
                Voo v = p.getVoo();
                System.out.println("\nPassagem #" + p.getId());
                System.out.println("Voo: " + v.getNumeroVoo() + " - " + v.getOrigem() + " -> " + v.getDestino());
                System.out.println("Data/Hora: " + v.getDataHora().format(formatter));
                System.out.println("Assento: " + p.getAssento());
                System.out.println("Preço pago: R$" + String.format("%.2f", p.getPrecoPago()));
                System.out.println("Status: " + (v.getDataHora().isBefore(LocalDateTime.now()) ? "VOO REALIZADO" : "CONFIRMADO"));
            }
        } catch (PassageiroNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void cancelarPassagem() {
        System.out.println("\n=== CANCELAR PASSAGEM ===");
        try {
            listarPassagensPassageiro();
            System.out.print("\nID da passagem a cancelar: ");
            int idPassagem = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            sistema.cancelarPassagem(idPassagem);
            System.out.println("Passagem cancelada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cancelar passagem: " + e.getMessage());
        }
    }

    private static void menuEmpresa() {
        System.out.println("\n=== MENU EMPRESA ===");
        System.out.println("1. Cadastrar aeronave");
        System.out.println("2. Cadastrar voo nacional");
        System.out.println("3. Cadastrar voo internacional");
        System.out.println("4. Listar voos");
        System.out.println("5. Listar aeronaves");
        System.out.println("6. Atualizar dados da empresa");
        System.out.println("7. Sair");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        try {
            switch (opcao) {
                case 1 -> cadastrarAeronave();
                case 2 -> cadastrarVooNacional();
                case 3 -> cadastrarVooInternacional();
                case 4 -> listarVoosEmpresa();
                case 5 -> listarAeronavesEmpresa();
                case 6 -> atualizarEmpresa();
                case 7 -> {
                    empresaLogada = null;
                    System.out.println("Logout realizado com sucesso!");
                }
                default -> System.out.println("Opção inválida!");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void atualizarEmpresa() {
        System.out.println("\n=== ATUALIZAR DADOS ===");
        System.out.print("Novo nome (" + empresaLogada.getNome() + "): ");
        String nome = scanner.nextLine();
        System.out.print("Novo CNPJ (" + empresaLogada.getCnpj() + "): ");
        String cnpj = scanner.nextLine();
        System.out.print("Nova senha: ");
        String senha = scanner.nextLine();

        empresaLogada.setNome(nome.isEmpty() ? empresaLogada.getNome() : nome);
        empresaLogada.setCnpj(cnpj.isEmpty() ? empresaLogada.getCnpj() : cnpj);
        empresaLogada.setSenha(senha.isEmpty() ? empresaLogada.getSenha() : senha);

        try {
            sistema.atualizarEmpresa(empresaLogada);
            System.out.println("Dados atualizados com sucesso!");
        } catch (EmpresaAereaNaoEncontradaException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    private static void listarAeronavesEmpresa() {
        System.out.println("\n=== AERONAVES DA EMPRESA ===");
        List<Aeronave> aeronaves = sistema.listarAeronaves();

        if (aeronaves.isEmpty()) {
            System.out.println("Nenhuma aeronave cadastrada.");
            return;
        }

        for (Aeronave a : aeronaves) {
            if (a.getIdEmpresa() == empresaLogada.getId()) {
                System.out.println("\nID: " + a.getId());
                System.out.println("Modelo: " + a.getModelo());
                System.out.println("Capacidade: " + a.getTotalAssentos() + " assentos");
                System.out.println("Configuração: " + a.getFileiras() + " fileiras x " +
                        a.getAssentosPorFileira() + " assentos");
            }
        }
    }

    private static void cadastrarAeronave() {
        System.out.println("\n=== CADASTRAR AERONAVE ===");
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Número de fileiras: ");
        int fileiras = scanner.nextInt();
        System.out.print("Assentos por fileira: ");
        int assentosPorFileira = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        try {
            sistema.cadastrarAeronave(modelo, fileiras, assentosPorFileira, empresaLogada.getId());            System.out.println("Aeronave cadastrada com sucesso!");
        } catch (AeronaveJaCadastradaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void cadastrarVooNacional() {
        System.out.println("\n=== CADASTRAR VOO NACIONAL ===");
        cadastrarVoo(false);
    }

    private static void cadastrarVooInternacional() {
        System.out.println("\n=== CADASTRAR VOO INTERNACIONAL ===");
        cadastrarVoo(true);
    }

    private static void cadastrarVoo(boolean internacional) {
        try {
            System.out.print("Número do voo: ");
            String numeroVoo = scanner.nextLine();
            System.out.print("Origem: ");
            String origem = scanner.nextLine();
            System.out.print("Destino: ");
            String destino = scanner.nextLine();

            System.out.print("Data (dd/MM/yyyy): ");
            String dataStr = scanner.nextLine();
            System.out.print("Hora (HH:mm): ");
            String horaStr = scanner.nextLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dataHora = LocalDateTime.parse(dataStr + " " + horaStr, formatter);

            System.out.print("Preço base: ");
            double precoBase = scanner.nextDouble();

            // Listar aeronaves da empresa
            List<Aeronave> aeronaves = sistema.listarAeronaves();
            System.out.println("\nAeronaves disponíveis:");
            for (Aeronave a : aeronaves) {
                if (a.getIdEmpresa() == empresaLogada.getId()) {
                    System.out.println(a.getId() + " - " + a.getModelo() +
                            " (" + a.getTotalAssentos() + " assentos)");
                }
            }

            System.out.print("ID da aeronave: ");
            int aeronaveId = scanner.nextInt();
            System.out.print("Taxa de embarque: ");
            double taxaEmbarque = scanner.nextDouble();

            if (internacional) {
                System.out.print("Taxa de alfândega: ");
                double taxaAlfandega = scanner.nextDouble();
                scanner.nextLine(); // Limpar buffer

                sistema.cadastrarVooInternacional(numeroVoo, origem, destino, dataHora,
                        precoBase, aeronaveId, taxaEmbarque, taxaAlfandega, empresaLogada.getId());
            } else {
                scanner.nextLine(); // Limpar buffer
                sistema.cadastrarVooNacional(numeroVoo, origem, destino, dataHora,
                        precoBase, aeronaveId, taxaEmbarque, empresaLogada.getId());
            }

            System.out.println("Voo cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar voo: " + e.getMessage());
        }
    }

    private static void listarVoosEmpresa() {
        System.out.println("\n=== VOOS DA EMPRESA ===");
        List<Voo> voos = sistema.listarVoos();

        if (voos.isEmpty()) {
            System.out.println("Nenhum voo cadastrado.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (Voo voo : voos) {
            if (voo.getAeronave().getIdEmpresa() == empresaLogada.getId()) {
                System.out.println("\nVoo: " + voo.getNumeroVoo());
                System.out.println("Origem: " + voo.getOrigem() + " -> Destino: " + voo.getDestino());
                System.out.println("Data/Hora: " + voo.getDataHora().format(formatter));
                System.out.println("Assentos disponíveis: " + voo.getAssentosDisponiveis() +
                        "/" + voo.getAeronave().getTotalAssentos());
                System.out.println("Preço base: R$" + String.format("%.2f", voo.getPrecoBase()));
                System.out.println("Tipo: " + (voo instanceof VooInternacional ? "Internacional" : "Nacional"));
                System.out.println("Aeronave: " + voo.getAeronave().getModelo());
            }
        }
    }
}