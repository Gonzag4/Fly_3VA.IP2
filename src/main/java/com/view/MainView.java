package com.view;

import com.controller.MainController;
import com.model.Passageiro;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainView {
    private final MainController controller;
    private final Stage stage;

    public MainView(MainController controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
    }

    private void aplicarCss(Scene scene) {
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    }

    public void mostrarMensagemErro(String mensagem) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public void telaPrincipal() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getStyleClass().add("root");

        Label titulo = new Label("✈ PASSAGENS AÉREAS ✈");
        titulo.getStyleClass().add("titulo");

        Button btnLoginPassageiro = new Button("Login Passageiro");
        Button btnCadastroPassageiro = new Button("Cadastrar Passageiro");
        Button btnLoginEmpresa = new Button("Login Empresa Aérea");
        Button btnCadastroEmpresa = new Button("Cadastrar Empresa Aérea");
        Button btnSair = new Button("Sair");

        for (Button btn : new Button[]{btnLoginPassageiro, btnCadastroPassageiro, btnLoginEmpresa, btnCadastroEmpresa, btnSair}) {
            btn.getStyleClass().add("menu");
        }

        btnLoginPassageiro.setOnAction(e -> controller.mostrarTelaLoginPassageiro());
        btnCadastroPassageiro.setOnAction(e -> controller.mostrarTelaCadastroPassageiro());
        btnLoginEmpresa.setOnAction(e -> controller.mostrarTelaLoginEmpresa());
        btnCadastroEmpresa.setOnAction(e -> controller.mostrarTelaCadastroEmpresa());
        btnSair.setOnAction(e -> stage.close());

        root.getChildren().addAll(
                titulo,
                btnLoginPassageiro,
                btnCadastroPassageiro,
                btnLoginEmpresa,
                btnCadastroEmpresa,
                btnSair
        );
        Scene scene = new Scene(root, 450, 420);
        aplicarCss(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void telaLoginPassageiro() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getStyleClass().add("root");

        Label titulo = new Label("LOGIN PASSAGEIRO");
        titulo.getStyleClass().add("titulo");
        TextField txtLogin = new TextField();
        txtLogin.setPromptText("Login");
        PasswordField txtSenha = new PasswordField();
        txtSenha.setPromptText("Senha");
        Button btnEntrar = new Button("Entrar");
        Button btnVoltar = new Button("Voltar");
        Label lblMensagem = new Label();

        btnEntrar.getStyleClass().add("menu");
        btnVoltar.getStyleClass().add("menu");

        btnEntrar.setOnAction(e -> controller.efetuarLoginPassageiro(txtLogin.getText(), txtSenha.getText(), lblMensagem));
        btnVoltar.setOnAction(e -> telaPrincipal());

        root.getChildren().addAll(titulo, txtLogin, txtSenha, btnEntrar, btnVoltar, lblMensagem);
        Scene scene = new Scene(root, 350, 250);
        aplicarCss(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void telaCadastroPassageiro() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getStyleClass().add("root");

        Label titulo = new Label("CADASTRO DE PASSAGEIRO");
        titulo.getStyleClass().add("titulo");
        TextField txtNome = new TextField();
        txtNome.setPromptText("Nome");
        TextField txtCpf = new TextField();
        txtCpf.setPromptText("CPF");
        TextField txtTelefone = new TextField();
        txtTelefone.setPromptText("Telefone");
        TextField txtLogin = new TextField();
        txtLogin.setPromptText("Login");
        PasswordField txtSenha = new PasswordField();
        txtSenha.setPromptText("Senha");
        Button btnCadastrar = new Button("Cadastrar");
        Button btnVoltar = new Button("Voltar");
        Label lblMensagem = new Label();

        btnCadastrar.getStyleClass().add("menu");
        btnVoltar.getStyleClass().add("menu");

        btnCadastrar.setOnAction(e -> controller.efetuarCadastroPassageiro(
                txtNome.getText(), txtCpf.getText(), txtTelefone.getText(),
                txtLogin.getText(), txtSenha.getText(), lblMensagem
        ));
        btnVoltar.setOnAction(e -> telaPrincipal());

        root.getChildren().addAll(titulo, txtNome, txtCpf, txtTelefone, txtLogin, txtSenha, btnCadastrar, btnVoltar, lblMensagem);
        Scene scene = new Scene(root, 350, 350);
        aplicarCss(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void telaLoginEmpresa() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getStyleClass().add("root");

        Label titulo = new Label("LOGIN EMPRESA AÉREA");
        titulo.getStyleClass().add("titulo");
        TextField txtLogin = new TextField();
        txtLogin.setPromptText("Login");
        PasswordField txtSenha = new PasswordField();
        txtSenha.setPromptText("Senha");
        Button btnEntrar = new Button("Entrar");
        Button btnVoltar = new Button("Voltar");
        Label lblMensagem = new Label();

        btnEntrar.getStyleClass().add("menu");
        btnVoltar.getStyleClass().add("menu");

        btnEntrar.setOnAction(e -> controller.efetuarLoginEmpresa(txtLogin.getText(), txtSenha.getText(), lblMensagem));
        btnVoltar.setOnAction(e -> telaPrincipal());

        root.getChildren().addAll(titulo, txtLogin, txtSenha, btnEntrar, btnVoltar, lblMensagem);
        Scene scene = new Scene(root, 350, 250);
        aplicarCss(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void telaCadastroEmpresa() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getStyleClass().add("root");

        Label titulo = new Label("CADASTRO DE EMPRESA AÉREA");
        titulo.getStyleClass().add("titulo");
        TextField txtNome = new TextField();
        txtNome.setPromptText("Nome");
        TextField txtCnpj = new TextField();
        txtCnpj.setPromptText("CNPJ");
        TextField txtLogin = new TextField();
        txtLogin.setPromptText("Login");
        PasswordField txtSenha = new PasswordField();
        txtSenha.setPromptText("Senha");
        Button btnCadastrar = new Button("Cadastrar");
        Button btnVoltar = new Button("Voltar");
        Label lblMensagem = new Label();

        btnCadastrar.getStyleClass().add("menu");
        btnVoltar.getStyleClass().add("menu");

        btnCadastrar.setOnAction(e -> controller.efetuarCadastroEmpresa(
                txtNome.getText(), txtCnpj.getText(), txtLogin.getText(), txtSenha.getText(), lblMensagem
        ));
        btnVoltar.setOnAction(e -> telaPrincipal());

        root.getChildren().addAll(titulo, txtNome, txtCnpj, txtLogin, txtSenha, btnCadastrar, btnVoltar, lblMensagem);
        Scene scene = new Scene(root, 350, 320);
        aplicarCss(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void telaMenuPassageiro(Passageiro passageiro) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(25));
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getStyleClass().add("root");

        Label titulo = new Label("MENU PASSAGEIRO");
        titulo.getStyleClass().add("titulo");

        Button btnVerVoos = new Button("Ver voos disponíveis");
        Button btnComprarPassagem = new Button("Comprar passagem");
        Button btnMinhasPassagens = new Button("Ver minhas passagens");
        Button btnCancelarPassagem = new Button("Cancelar passagem");
        Button btnAtualizarDados = new Button("Atualizar meus dados");
        Button btnSair = new Button("Sair");

        for (Button btn : new Button[]{btnVerVoos, btnComprarPassagem, btnMinhasPassagens, btnCancelarPassagem, btnAtualizarDados, btnSair}) {
            btn.getStyleClass().add("menu");
        }

        btnVerVoos.setOnAction(e -> controller.mostrarVoosDisponiveis(passageiro));
        btnComprarPassagem.setOnAction(e -> controller.mostrarComprarPassagem(passageiro));
        btnMinhasPassagens.setOnAction(e -> controller.mostrarMinhasPassagens(passageiro));
        btnCancelarPassagem.setOnAction(e -> controller.mostrarCancelarPassagem(passageiro));
        btnAtualizarDados.setOnAction(e -> controller.mostrarAtualizarPassageiro(passageiro));
        btnSair.setOnAction(e -> controller.mostrarTelaPrincipal());

        root.getChildren().addAll(
                titulo,
                btnVerVoos,
                btnComprarPassagem,
                btnMinhasPassagens,
                btnCancelarPassagem,
                btnAtualizarDados,
                btnSair
        );
        Scene scene = new Scene(root, 400, 420);
        aplicarCss(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void telaListaVoos(java.util.List<com.model.Voo> voos, com.model.Passageiro passageiro) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(25));
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getStyleClass().add("root");

        Label titulo = new Label("VOOS DISPONÍVEIS");
        titulo.getStyleClass().add("titulo");

        TableView<com.model.Voo> tabela = new TableView<>();
        tabela.setItems(FXCollections.observableArrayList(voos));

        TableColumn<com.model.Voo, String> colNumero = new TableColumn<>("Nº Voo");
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numeroVoo"));

        TableColumn<com.model.Voo, String> colOrigem = new TableColumn<>("Origem");
        colOrigem.setCellValueFactory(new PropertyValueFactory<>("origem"));

        TableColumn<com.model.Voo, String> colDestino = new TableColumn<>("Destino");
        colDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));

        TableColumn<com.model.Voo, String> colDataHora = new TableColumn<>("Data/Hora");
        colDataHora.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getDataHora().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                )
        );

        TableColumn<com.model.Voo, Integer> colAssentos = new TableColumn<>("Assentos");
        colAssentos.setCellValueFactory(new PropertyValueFactory<>("assentosDisponiveis"));

        tabela.getColumns().addAll(colNumero, colOrigem, colDestino, colDataHora, colAssentos);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Button btnVoltar = new Button("Voltar");
        btnVoltar.getStyleClass().add("menu");
        btnVoltar.setOnAction(e -> telaMenuPassageiro(passageiro));

        root.getChildren().addAll(titulo, tabela, btnVoltar);
        Scene scene = new Scene(root, 700, 400);
        aplicarCss(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void telaComprarPassagem(java.util.List<com.model.Voo> voos, com.model.Passageiro passageiro) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(25));
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getStyleClass().add("root");

        Label titulo = new Label("COMPRAR PASSAGEM");
        titulo.getStyleClass().add("titulo");

        TableView<com.model.Voo> tabela = new TableView<>();
        tabela.setItems(FXCollections.observableArrayList(voos));

        TableColumn<com.model.Voo, String> colNumero = new TableColumn<>("Nº Voo");
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numeroVoo"));
        TableColumn<com.model.Voo, String> colOrigem = new TableColumn<>("Origem");
        colOrigem.setCellValueFactory(new PropertyValueFactory<>("origem"));
        TableColumn<com.model.Voo, String> colDestino = new TableColumn<>("Destino");
        colDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        TableColumn<com.model.Voo, String> colDataHora = new TableColumn<>("Data/Hora");
        colDataHora.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getDataHora().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                )
        );
        TableColumn<com.model.Voo, Integer> colAssentos = new TableColumn<>("Assentos");
        colAssentos.setCellValueFactory(new PropertyValueFactory<>("assentosDisponiveis"));

        tabela.getColumns().addAll(colNumero, colOrigem, colDestino, colDataHora, colAssentos);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Label lblAssentos = new Label("Selecione um voo para ver os assentos disponíveis.");
        ComboBox<String> comboAssentos = new ComboBox<>();
        comboAssentos.setDisable(true);

        tabela.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                try {
                    java.util.List<String> assentos = controller.listarAssentosDisponiveis(newV.getNumeroVoo());
                    comboAssentos.setItems(FXCollections.observableArrayList(assentos));
                    comboAssentos.setDisable(false);
                    lblAssentos.setText("Assentos disponíveis para o voo selecionado:");
                } catch (Exception ex) {
                    comboAssentos.setItems(FXCollections.observableArrayList());
                    comboAssentos.setDisable(true);
                    lblAssentos.setText("Erro ao buscar assentos: " + ex.getMessage());
                }
            } else {
                comboAssentos.setItems(FXCollections.observableArrayList());
                comboAssentos.setDisable(true);
                lblAssentos.setText("Selecione um voo para ver os assentos disponíveis.");
            }
        });

        Button btnComprar = new Button("Comprar");
        btnComprar.getStyleClass().add("menu");
        Label lblMensagem = new Label();

        btnComprar.setOnAction(e -> {
            com.model.Voo vooSel = tabela.getSelectionModel().getSelectedItem();
            String assentoSel = comboAssentos.getValue();
            if (vooSel == null || assentoSel == null) {
                lblMensagem.setText("Selecione um voo e um assento.");
                return;
            }
            try {
                controller.comprarPassagem(passageiro.getId(), vooSel.getNumeroVoo(), assentoSel);
                lblMensagem.setText("Passagem comprada com sucesso!");
                controller.mostrarMinhasPassagens(passageiro);
            } catch (Exception ex) {
                lblMensagem.setText("Erro: " + ex.getMessage());
            }
        });

        Button btnVoltar = new Button("Voltar");
        btnVoltar.getStyleClass().add("menu");
        btnVoltar.setOnAction(e -> telaMenuPassageiro(passageiro));

        root.getChildren().addAll(titulo, tabela, lblAssentos, comboAssentos, btnComprar, btnVoltar, lblMensagem);
        Scene scene = new Scene(root, 700, 500);
        aplicarCss(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void telaMinhasPassagens(java.util.List<com.model.Passagem> passagens, com.model.Passageiro passageiro) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(25));
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getStyleClass().add("root");

        Label titulo = new Label("MINHAS PASSAGENS");
        titulo.getStyleClass().add("titulo");

        TableView<com.model.Passagem> tabela = new TableView<>();
        tabela.setItems(FXCollections.observableArrayList(passagens));

        TableColumn<com.model.Passagem, String> colVoo = new TableColumn<>("Nº Voo");
        colVoo.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getVoo().getNumeroVoo())
        );

        TableColumn<com.model.Passagem, String> colOrigem = new TableColumn<>("Origem");
        colOrigem.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getVoo().getOrigem())
        );

        TableColumn<com.model.Passagem, String> colDestino = new TableColumn<>("Destino");
        colDestino.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getVoo().getDestino())
        );

        TableColumn<com.model.Passagem, String> colDataHora = new TableColumn<>("Data/Hora");
        colDataHora.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getVoo().getDataHora().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                )
        );

        TableColumn<com.model.Passagem, String> colAssento = new TableColumn<>("Assento");
        colAssento.setCellValueFactory(new PropertyValueFactory<>("assento"));

        TableColumn<com.model.Passagem, String> colPreco = new TableColumn<>("Preço Pago");
        colPreco.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        String.format("R$ %.2f", cellData.getValue().getPrecoPago())
                )
        );

        TableColumn<com.model.Passagem, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(cellData -> {
            var dataHora = cellData.getValue().getVoo().getDataHora();
            String status = dataHora.isBefore(java.time.LocalDateTime.now()) ? "VOO REALIZADO" : "CONFIRMADO";
            return new javafx.beans.property.SimpleStringProperty(status);
        });

        tabela.getColumns().addAll(colVoo, colOrigem, colDestino, colDataHora, colAssento, colPreco, colStatus);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Button btnVoltar = new Button("Voltar");
        btnVoltar.getStyleClass().add("menu");
        btnVoltar.setOnAction(e -> telaMenuPassageiro(passageiro));

        root.getChildren().addAll(titulo, tabela, btnVoltar);
        Scene scene = new Scene(root, 800, 400);
        aplicarCss(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void telaCancelarPassagem(java.util.List<com.model.Passagem> passagens, com.model.Passageiro passageiro) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(25));
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getStyleClass().add("root");

        Label titulo = new Label("CANCELAR PASSAGEM");
        titulo.getStyleClass().add("titulo");

        TableView<com.model.Passagem> tabela = new TableView<>();
        tabela.setItems(FXCollections.observableArrayList(passagens));

        TableColumn<com.model.Passagem, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<com.model.Passagem, String> colVoo = new TableColumn<>("Nº Voo");
        colVoo.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getVoo().getNumeroVoo())
        );

        TableColumn<com.model.Passagem, String> colOrigem = new TableColumn<>("Origem");
        colOrigem.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getVoo().getOrigem())
        );

        TableColumn<com.model.Passagem, String> colDestino = new TableColumn<>("Destino");
        colDestino.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getVoo().getDestino())
        );

        TableColumn<com.model.Passagem, String> colDataHora = new TableColumn<>("Data/Hora");
        colDataHora.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getVoo().getDataHora().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                )
        );

        TableColumn<com.model.Passagem, String> colAssento = new TableColumn<>("Assento");
        colAssento.setCellValueFactory(new PropertyValueFactory<>("assento"));

        tabela.getColumns().addAll(colId, colVoo, colOrigem, colDestino, colDataHora, colAssento);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Button btnCancelar = new Button("Cancelar Passagem");
        btnCancelar.getStyleClass().add("menu");
        Label lblMensagem = new Label();

        btnCancelar.setOnAction(e -> {
            com.model.Passagem passagemSel = tabela.getSelectionModel().getSelectedItem();
            if (passagemSel == null) {
                lblMensagem.setText("Selecione uma passagem para cancelar.");
                return;
            }
            try {
                controller.cancelarPassagem(passagemSel.getId());
                lblMensagem.setText("Passagem cancelada com sucesso!");
                controller.mostrarMinhasPassagens(passageiro);
            } catch (Exception ex) {
                lblMensagem.setText("Erro ao cancelar: " + ex.getMessage());
            }
        });

        Button btnVoltar = new Button("Voltar");
        btnVoltar.getStyleClass().add("menu");
        btnVoltar.setOnAction(e -> telaMenuPassageiro(passageiro));

        root.getChildren().addAll(titulo, tabela, btnCancelar, btnVoltar, lblMensagem);
        Scene scene = new Scene(root, 800, 400);
        aplicarCss(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void telaAtualizarPassageiro(com.model.Passageiro passageiro) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(25));
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getStyleClass().add("root");

        Label titulo = new Label("ATUALIZAR DADOS");
        titulo.getStyleClass().add("titulo");

        TextField txtNome = new TextField(passageiro.getNome());
        txtNome.setPromptText("Nome");

        TextField txtTelefone = new TextField(passageiro.getTelefone());
        txtTelefone.setPromptText("Telefone");

        PasswordField txtSenha = new PasswordField();
        txtSenha.setPromptText("Nova senha (deixe em branco para não alterar)");

        Button btnSalvar = new Button("Salvar");
        btnSalvar.getStyleClass().add("menu");
        Button btnVoltar = new Button("Voltar");
        btnVoltar.getStyleClass().add("menu");
        Label lblMensagem = new Label();

        btnSalvar.setOnAction(e -> {
            String nome = txtNome.getText().trim();
            String telefone = txtTelefone.getText().trim();
            String senha = txtSenha.getText().trim();

            passageiro.setNome(nome.isEmpty() ? passageiro.getNome() : nome);
            passageiro.setTelefone(telefone.isEmpty() ? passageiro.getTelefone() : telefone);
            if (!senha.isEmpty()) {
                passageiro.setSenha(senha);
            }

            try {
                controller.atualizarPassageiro(passageiro);
                lblMensagem.setText("Dados atualizados com sucesso!");
                controller.mostrarMinhasPassagens(passageiro);
            } catch (Exception ex) {
                lblMensagem.setText("Erro ao atualizar: " + ex.getMessage());
            }
        });

        btnVoltar.setOnAction(e -> telaMenuPassageiro(passageiro));

        root.getChildren().addAll(titulo, txtNome, txtTelefone, txtSenha, btnSalvar, btnVoltar, lblMensagem);
        Scene scene = new Scene(root, 400, 350);
        aplicarCss(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void telaMenuEmpresa(com.model.EmpresaAerea empresa) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(25));
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getStyleClass().add("root");

        Label titulo = new Label("MENU EMPRESA AÉREA");
        titulo.getStyleClass().add("titulo");

        Button btnCadastrarAeronave = new Button("Cadastrar aeronave");
        Button btnCadastrarVooNacional = new Button("Cadastrar voo nacional");
        Button btnCadastrarVooInternacional = new Button("Cadastrar voo internacional");
        Button btnListarVoos = new Button("Listar voos");
        Button btnListarAeronaves = new Button("Listar aeronaves");
        Button btnAtualizarDados = new Button("Atualizar dados da empresa");
        Button btnSair = new Button("Sair");

        for (Button btn : new Button[]{btnCadastrarAeronave, btnCadastrarVooNacional, btnCadastrarVooInternacional, btnListarVoos, btnListarAeronaves, btnAtualizarDados, btnSair}) {
            btn.getStyleClass().add("menu");
        }

        btnCadastrarAeronave.setOnAction(e -> controller.mostrarCadastrarAeronave(empresa));
        btnCadastrarVooNacional.setOnAction(e -> controller.mostrarCadastrarVooNacional(empresa));
        btnCadastrarVooInternacional.setOnAction(e -> controller.mostrarCadastrarVooInternacional(empresa));
        btnListarVoos.setOnAction(e -> controller.mostrarListarVoosEmpresa(empresa));
        btnListarAeronaves.setOnAction(e -> controller.mostrarListarAeronavesEmpresa(empresa));
        btnAtualizarDados.setOnAction(e -> controller.mostrarAtualizarEmpresa(empresa));
        btnSair.setOnAction(e -> controller.mostrarTelaPrincipal());

        root.getChildren().addAll(
                titulo,
                btnCadastrarAeronave,
                btnCadastrarVooNacional,
                btnCadastrarVooInternacional,
                btnListarVoos,
                btnListarAeronaves,
                btnAtualizarDados,
                btnSair
        );
        Scene scene = new Scene(root, 400, 420);
        aplicarCss(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void telaCadastrarAeronave(com.model.EmpresaAerea empresa) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(25));
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getStyleClass().add("root");

        Label titulo = new Label("CADASTRAR AERONAVE");
        titulo.getStyleClass().add("titulo");

        TextField txtModelo = new TextField();
        txtModelo.setPromptText("Modelo da aeronave");
        TextField txtFileiras = new TextField();
        txtFileiras.setPromptText("Número de fileiras");
        TextField txtAssentos = new TextField();
        txtAssentos.setPromptText("Assentos por fileira");

        Button btnCadastrar = new Button("Cadastrar");
        btnCadastrar.getStyleClass().add("menu");
        Button btnVoltar = new Button("Voltar");
        btnVoltar.getStyleClass().add("menu");
        Label lblMensagem = new Label();

        btnCadastrar.setOnAction(e -> {
            String modelo = txtModelo.getText().trim();
            String fileirasStr = txtFileiras.getText().trim();
            String assentosStr = txtAssentos.getText().trim();
            if (modelo.isEmpty() || fileirasStr.isEmpty() || assentosStr.isEmpty()) {
                lblMensagem.setText("Preencha todos os campos.");
                return;
            }
            try {
                int fileiras = Integer.parseInt(fileirasStr);
                int assentos = Integer.parseInt(assentosStr);
                controller.cadastrarAeronave(modelo, fileiras, assentos, empresa);
                lblMensagem.setText("Aeronave cadastrada com sucesso!");
            } catch (Exception ex) {
                lblMensagem.setText("Erro: " + ex.getMessage());
            }
        });

        btnVoltar.setOnAction(e -> telaMenuEmpresa(empresa));

        root.getChildren().addAll(titulo, txtModelo, txtFileiras, txtAssentos, btnCadastrar, btnVoltar, lblMensagem);
        Scene scene = new Scene(root, 400, 350);
        aplicarCss(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void telaCadastrarVooNacional(com.model.EmpresaAerea empresa) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(25));
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getStyleClass().add("root");

        Label titulo = new Label("CADASTRAR VOO NACIONAL");
        titulo.getStyleClass().add("titulo");

        TextField txtNumero = new TextField();
        txtNumero.setPromptText("Número do voo");
        TextField txtOrigem = new TextField();
        txtOrigem.setPromptText("Origem");
        TextField txtDestino = new TextField();
        txtDestino.setPromptText("Destino");
        TextField txtData = new TextField();
        txtData.setPromptText("Data (dd/MM/yyyy)");
        TextField txtHora = new TextField();
        txtHora.setPromptText("Hora (HH:mm)");
        TextField txtPreco = new TextField();
        txtPreco.setPromptText("Preço base");
        TextField txtTaxaEmbarque = new TextField();
        txtTaxaEmbarque.setPromptText("Taxa de embarque");

        java.util.List<com.model.Aeronave> aeronaves = controller.listarAeronavesEmpresa(empresa.getId());
        ComboBox<com.model.Aeronave> comboAeronave = new ComboBox<>(FXCollections.observableArrayList(aeronaves));
        comboAeronave.setPromptText("Selecione a aeronave");
        comboAeronave.setCellFactory(cb -> new ListCell<>() {
            @Override
            protected void updateItem(com.model.Aeronave item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getModelo() + " (ID: " + item.getId() + ")");
            }
        });
        comboAeronave.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(com.model.Aeronave item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getModelo() + " (ID: " + item.getId() + ")");
            }
        });

        Button btnCadastrar = new Button("Cadastrar");
        btnCadastrar.getStyleClass().add("menu");
        Button btnVoltar = new Button("Voltar");
        btnVoltar.getStyleClass().add("menu");
        Label lblMensagem = new Label();

        btnCadastrar.setOnAction(e -> {
            String numero = txtNumero.getText().trim();
            String origem = txtOrigem.getText().trim();
            String destino = txtDestino.getText().trim();
            String data = txtData.getText().trim();
            String hora = txtHora.getText().trim();
            String precoStr = txtPreco.getText().trim();
            String taxaStr = txtTaxaEmbarque.getText().trim();
            com.model.Aeronave aeronave = comboAeronave.getValue();

            if (numero.isEmpty() || origem.isEmpty() || destino.isEmpty() || data.isEmpty() ||
                    hora.isEmpty() || precoStr.isEmpty() || taxaStr.isEmpty() || aeronave == null) {
                lblMensagem.setText("Preencha todos os campos.");
                return;
            }
            try {
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                java.time.LocalDateTime dataHora = java.time.LocalDateTime.parse(data + " " + hora, formatter);
                double preco = Double.parseDouble(precoStr);
                double taxa = Double.parseDouble(taxaStr);
                controller.cadastrarVooNacional(
                        numero, origem, destino, dataHora, preco, aeronave.getId(), taxa, empresa.getId()
                );
                lblMensagem.setText("Voo nacional cadastrado com sucesso!");
            } catch (Exception ex) {
                lblMensagem.setText("Erro: " + ex.getMessage());
            }
        });

        btnVoltar.setOnAction(e -> telaMenuEmpresa(empresa));

        root.getChildren().addAll(
                titulo, txtNumero, txtOrigem, txtDestino, txtData, txtHora, txtPreco, txtTaxaEmbarque,
                comboAeronave, btnCadastrar, btnVoltar, lblMensagem
        );
        Scene scene = new Scene(root, 400, 600);
        aplicarCss(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void telaCadastrarVooInternacional(com.model.EmpresaAerea empresa) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(25));
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getStyleClass().add("root");

        Label titulo = new Label("CADASTRAR VOO INTERNACIONAL");
        titulo.getStyleClass().add("titulo");

        TextField txtNumero = new TextField();
        txtNumero.setPromptText("Número do voo");
        TextField txtOrigem = new TextField();
        txtOrigem.setPromptText("Origem");
        TextField txtDestino = new TextField();
        txtDestino.setPromptText("Destino");
        TextField txtData = new TextField();
        txtData.setPromptText("Data (dd/MM/yyyy)");
        TextField txtHora = new TextField();
        txtHora.setPromptText("Hora (HH:mm)");
        TextField txtPreco = new TextField();
        txtPreco.setPromptText("Preço base");
        TextField txtTaxaEmbarque = new TextField();
        txtTaxaEmbarque.setPromptText("Taxa de embarque");
        TextField txtTaxaAlfandega = new TextField();
        txtTaxaAlfandega.setPromptText("Taxa de alfândega");

        java.util.List<com.model.Aeronave> aeronaves = controller.listarAeronavesEmpresa(empresa.getId());
        ComboBox<com.model.Aeronave> comboAeronave = new ComboBox<>(FXCollections.observableArrayList(aeronaves));
        comboAeronave.setPromptText("Selecione a aeronave");
        comboAeronave.setCellFactory(cb -> new ListCell<>() {
            @Override
            protected void updateItem(com.model.Aeronave item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getModelo() + " (ID: " + item.getId() + ")");
            }
        });
        comboAeronave.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(com.model.Aeronave item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getModelo() + " (ID: " + item.getId() + ")");
            }
        });

        Button btnCadastrar = new Button("Cadastrar");
        btnCadastrar.getStyleClass().add("menu");
        Button btnVoltar = new Button("Voltar");
        btnVoltar.getStyleClass().add("menu");
        Label lblMensagem = new Label();

        btnCadastrar.setOnAction(e -> {
            String numero = txtNumero.getText().trim();
            String origem = txtOrigem.getText().trim();
            String destino = txtDestino.getText().trim();
            String data = txtData.getText().trim();
            String hora = txtHora.getText().trim();
            String precoStr = txtPreco.getText().trim();
            String taxaStr = txtTaxaEmbarque.getText().trim();
            String alfandegaStr = txtTaxaAlfandega.getText().trim();
            com.model.Aeronave aeronave = comboAeronave.getValue();

            if (numero.isEmpty() || origem.isEmpty() || destino.isEmpty() || data.isEmpty() ||
                    hora.isEmpty() || precoStr.isEmpty() || taxaStr.isEmpty() || alfandegaStr.isEmpty() || aeronave == null) {
                lblMensagem.setText("Preencha todos os campos.");
                return;
            }
            try {
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                java.time.LocalDateTime dataHora = java.time.LocalDateTime.parse(data + " " + hora, formatter);
                double preco = Double.parseDouble(precoStr);
                double taxa = Double.parseDouble(taxaStr);
                double alfandega = Double.parseDouble(alfandegaStr);
                controller.cadastrarVooInternacional(
                        numero, origem, destino, dataHora, preco, aeronave.getId(), taxa, alfandega, empresa.getId()
                );
                lblMensagem.setText("Voo internacional cadastrado com sucesso!");
            } catch (Exception ex) {
                lblMensagem.setText("Erro: " + ex.getMessage());
            }
        });

        btnVoltar.setOnAction(e -> telaMenuEmpresa(empresa));

        root.getChildren().addAll(titulo, txtNumero, txtOrigem, txtDestino, txtData, txtHora, txtPreco, txtTaxaEmbarque, txtTaxaAlfandega, comboAeronave, btnCadastrar, btnVoltar, lblMensagem);
        Scene scene = new Scene(root, 400, 500);
        aplicarCss(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void telaListarAeronaves(java.util.List<com.model.Aeronave> aeronaves, com.model.EmpresaAerea empresa) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(25));
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getStyleClass().add("root");

        Label titulo = new Label("AERONAVES DA EMPRESA");
        titulo.getStyleClass().add("titulo");

        TableView<com.model.Aeronave> tabela = new TableView<>();
        tabela.setItems(FXCollections.observableArrayList(aeronaves));

        TableColumn<com.model.Aeronave, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<com.model.Aeronave, String> colModelo = new TableColumn<>("Modelo");
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        TableColumn<com.model.Aeronave, Integer> colFileiras = new TableColumn<>("Fileiras");
        colFileiras.setCellValueFactory(new PropertyValueFactory<>("fileiras"));

        TableColumn<com.model.Aeronave, Integer> colAssentos = new TableColumn<>("Assentos/Fileira");
        colAssentos.setCellValueFactory(new PropertyValueFactory<>("assentosPorFileira"));

        TableColumn<com.model.Aeronave, Integer> colTotal = new TableColumn<>("Total Assentos");
        colTotal.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getTotalAssentos()).asObject()
        );

        tabela.getColumns().addAll(colId, colModelo, colFileiras, colAssentos, colTotal);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Button btnVoltar = new Button("Voltar");
        btnVoltar.getStyleClass().add("menu");
        btnVoltar.setOnAction(e -> telaMenuEmpresa(empresa));

        root.getChildren().addAll(titulo, tabela, btnVoltar);
        Scene scene = new Scene(root, 600, 400);
        aplicarCss(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void telaListarVoos(java.util.List<com.model.Voo> voos, com.model.EmpresaAerea empresa) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(25));
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getStyleClass().add("root");

        Label titulo = new Label("VOOS DA EMPRESA");
        titulo.getStyleClass().add("titulo");

        TableView<com.model.Voo> tabela = new TableView<>();
        tabela.setItems(FXCollections.observableArrayList(voos));

        TableColumn<com.model.Voo, String> colNumero = new TableColumn<>("Nº Voo");
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numeroVoo"));

        TableColumn<com.model.Voo, String> colOrigem = new TableColumn<>("Origem");
        colOrigem.setCellValueFactory(new PropertyValueFactory<>("origem"));

        TableColumn<com.model.Voo, String> colDestino = new TableColumn<>("Destino");
        colDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));

        TableColumn<com.model.Voo, String> colDataHora = new TableColumn<>("Data/Hora");
        colDataHora.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getDataHora().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                )
        );

        TableColumn<com.model.Voo, Integer> colAssentos = new TableColumn<>("Assentos Disp.");
        colAssentos.setCellValueFactory(new PropertyValueFactory<>("assentosDisponiveis"));

        TableColumn<com.model.Voo, String> colTipo = new TableColumn<>("Tipo");
        colTipo.setCellValueFactory(cellData -> {
            String tipo = cellData.getValue() instanceof com.model.VooInternacional ? "Internacional" : "Nacional";
            return new javafx.beans.property.SimpleStringProperty(tipo);
        });

        TableColumn<com.model.Voo, String> colAeronave = new TableColumn<>("Aeronave");
        colAeronave.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAeronave().getModelo())
        );

        tabela.getColumns().addAll(colNumero, colOrigem, colDestino, colDataHora, colAssentos, colTipo, colAeronave);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Button btnVoltar = new Button("Voltar");
        btnVoltar.getStyleClass().add("menu");
        btnVoltar.setOnAction(e -> telaMenuEmpresa(empresa));

        root.getChildren().addAll(titulo, tabela, btnVoltar);
        Scene scene = new Scene(root, 800, 400);
        aplicarCss(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void telaAtualizarEmpresa(com.model.EmpresaAerea empresa) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(25));
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getStyleClass().add("root");

        Label titulo = new Label("ATUALIZAR DADOS DA EMPRESA");
        titulo.getStyleClass().add("titulo");

        TextField txtNome = new TextField(empresa.getNome() != null ? empresa.getNome() : "");
        txtNome.setPromptText("Nome");

        TextField txtCnpj = new TextField(empresa.getCnpj() != null ? empresa.getCnpj() : "");
        txtCnpj.setPromptText("CNPJ");

        PasswordField txtSenha = new PasswordField();
        txtSenha.setPromptText("Nova senha (deixe em branco para não alterar)");

        Button btnSalvar = new Button("Salvar");
        btnSalvar.getStyleClass().add("menu");
        Button btnVoltar = new Button("Voltar");
        btnVoltar.getStyleClass().add("menu");
        Label lblMensagem = new Label();

        btnSalvar.setOnAction(e -> {
            String nome = txtNome.getText().trim();
            String cnpj = txtCnpj.getText().trim();
            String senha = txtSenha.getText();

            empresa.setNome(nome.isEmpty() ? empresa.getNome() : nome);
            empresa.setCnpj(cnpj.isEmpty() ? empresa.getCnpj() : cnpj);
            if (!senha.isEmpty()) empresa.setSenha(senha);

            try {
                controller.atualizarEmpresa(empresa);
                lblMensagem.setText("Dados atualizados com sucesso!");
            } catch (Exception ex) {
                lblMensagem.setText("Erro: " + ex.getMessage());
            }
        });

        btnVoltar.setOnAction(e -> telaMenuEmpresa(empresa));

        root.getChildren().addAll(titulo, txtNome, txtCnpj, txtSenha, btnSalvar, btnVoltar, lblMensagem);
        Scene scene = new Scene(root, 400, 350);
        aplicarCss(scene);
        stage.setScene(scene);
        stage.show();
    }
}