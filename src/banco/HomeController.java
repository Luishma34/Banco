/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package banco;

import Model.Agencia;
import Model.Cliente;
import Model.Conta;
import Model.Extrato;

import java.net.URL;
import java.util.ResourceBundle;

import Dao.AgenciaDao;
import Dao.ClienteDao;
import Dao.ContaDao;
import Dao.ExtratoDao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HomeController implements Initializable {

    private Cliente c;

    public void setCliente(Cliente c) {
        this.c = c;
    }

    static Stage stagehc;

    public static Stage getStagehc() {
        return stagehc;
    }

    public static void setStagehc(Stage stagehc) {
        HomeController.stagehc = stagehc;
    }

    @FXML
    private TableColumn<Extrato, Timestamp> coluna_data;

    @FXML
    private TableColumn<Extrato, String> coluna_destino;

    @FXML
    private TableColumn<Extrato, String> coluna_origem;

    @FXML
    private TableColumn<Extrato, Double> coluna_saldo;

    @FXML
    private TableColumn<Extrato, String> coluna_tipo;

    @FXML
    private TableColumn<Extrato, Double> coluna_valor;

    @FXML
    private TableView<Extrato> tabela_extrato;

    @FXML
    private Button btn_alterardados;

    @FXML
    private Button btn_depositar;

    @FXML
    private Button btn_excluir;

    @FXML
    private Button btn_menu_dados;

    @FXML
    private Button btn_menu_depositar;

    @FXML
    private Button btn_menu_extrato;

    @FXML
    private Button btn_menu_sacar;

    @FXML
    private Button btn_menu_sair;

    @FXML
    private Button btn_menu_transferir;

    @FXML
    private Button btn_sacar;

    @FXML
    private Button btn_transferir;

    @FXML
    private ChoiceBox<String> cb_agencia;

    @FXML
    private AnchorPane conteudo;

    @FXML
    private Label lb_agencia;

    @FXML
    private Label lb_cpf;

    @FXML
    private Label lb_data;

    @FXML
    private Label lb_destinatario;

    @FXML
    private Label lb_erro_dados;

    @FXML
    private Label lb_login;

    @FXML
    private Label lb_msg;

    @FXML
    private Label lb_nome;

    @FXML
    private Label lb_nova_senha;

    @FXML
    private Label lb_numconta;

    @FXML
    private Label lb_rg;

    @FXML
    private Label lb_saldo;

    @FXML
    private Label lb_senha_atual;

    @FXML
    private Label lb_tel;

    @FXML
    private Label lb_titulo;

    @FXML
    private Label lb_valor;

    @FXML
    private TextField tf_login;

    @FXML
    private AnchorPane menu;

    @FXML
    private Pane tela_dados;

    @FXML
    private Pane tela_depositar;

    @FXML
    private Pane tela_extrato;

    @FXML
    private Pane tela_operacoes;

    @FXML
    private Pane tela_sacar;

    @FXML
    private Pane tela_transferir;

    @FXML
    private TextField tf_cpf;

    @FXML
    private TextField tf_data;

    @FXML
    private TextField tf_destinatario;

    @FXML
    private TextField tf_nome;

    @FXML
    private TextField tf_rg;

    @FXML
    private PasswordField tf_senha_nova;

    @FXML
    private TextField tf_telefone;

    @FXML
    private TextField tf_valor;

    @FXML
    private Label titulo;

    @FXML
    private Label tx_numconta;

    @FXML
    private Label tx_saldo;

    @FXML
    private Label tx_saldo_dados;

    @FXML
    private PasswordField tx_senha_atual;

    @FXML
    void depositar(ActionEvent event) throws SQLException {
        if (!tf_valor.getText().isEmpty()) {
            ContaDao cd = new ContaDao();
            if (cd.depositar(c.getId_cliente(), Double.parseDouble(tf_valor.getText()))) {
                lb_msg.setVisible(true);
                lb_msg.setText("Deposito realizado com sucesso!");
                Conta conta = new Conta();
                conta = cd.consultar(c.getId_cliente());
                tx_saldo.setText("R$" + String.valueOf(conta.getSaldo()));

                Extrato extrato = new Extrato();
                ExtratoDao ed = new ExtratoDao();

                extrato.setValor(Double.parseDouble(tf_valor.getText()));
                extrato.setTipo("Deposito");
                extrato.setId_cliente(c.getId_cliente());
                extrato.setSaldo(cd.consultar(c.getId_cliente()).getSaldo());
                extrato.setOrigem(c.getCpf());
                extrato.setDestino(c.getCpf());

                ed.inserir(extrato);
            } else {
                lb_msg.setVisible(true);
                lb_msg.setText("Erro ao realizar o deposito!");

            }
        } else {
            lb_msg.setVisible(true);
            lb_msg.setText("Campo de valor vazio!");
        }
    }

    @FXML
    void menu_depositar(ActionEvent event) throws SQLException {
        tela_dados.setVisible(false);
        tela_extrato.setVisible(false);

        tela_transferir.setVisible(false);
        tela_sacar.setVisible(false);
        lb_msg.setVisible(false);

        tela_operacoes.setVisible(true);
        tela_depositar.setVisible(true);
        lb_titulo.setText("DEPOSITAR");

        ContaDao cd = new ContaDao();
        Conta conta = new Conta();
        conta = cd.consultar(c.getId_cliente());
        tx_saldo.setText("R$" + String.valueOf(conta.getSaldo()));

        tf_valor.setText("");

    }

    @FXML
    void menu_extrato(ActionEvent event) {
        tela_dados.setVisible(false);
        tela_operacoes.setVisible(false);

        tela_extrato.setVisible(true);

        ClienteDao clienteDao = new ClienteDao();

        coluna_data.setCellValueFactory(new PropertyValueFactory<>("data"));
        
        coluna_origem.setCellValueFactory(new PropertyValueFactory<>("origem"));
        coluna_destino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        // coluna_origem.setCellValueFactory(cellData -> {

        //     try {
        //         int origem = cellData.getValue().getOrigem();
        //         String cpf;
        //         cpf = clienteDao.consultar(String.valueOf(origem), "id_cliente").getCpf();
        //         return new SimpleStringProperty(cpf);
        //     } catch (SQLException e) {
        //         e.printStackTrace();
        //         return new SimpleStringProperty("");
        //     }

        // });
        // coluna_destino.setCellValueFactory(cellData -> {

        //     try {
        //         int destino = cellData.getValue().getDestino();
        //         String cpf;
        //         cpf = clienteDao.consultar(String.valueOf(destino), "id_cliente").getCpf();
        //         return new SimpleStringProperty(cpf);
        //     } catch (SQLException e) {
        //         e.printStackTrace();
        //         return new SimpleStringProperty("");
        //     }
        // });
        coluna_saldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        coluna_tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        coluna_valor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        try {
            ExtratoDao ed = new ExtratoDao();
            List<Extrato> lista = ed.lista_extrato(c.getId_cliente());
            ObservableList<Extrato> listaObservavel = FXCollections.observableArrayList(lista);
            tabela_extrato.setItems(listaObservavel);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void menu_sacar(ActionEvent event) throws SQLException {
        tela_dados.setVisible(false);
        tela_extrato.setVisible(false);

        tela_transferir.setVisible(false);
        tela_depositar.setVisible(false);

        lb_msg.setVisible(false);

        tela_operacoes.setVisible(true);
        tela_sacar.setVisible(true);

        lb_titulo.setText("SACAR");

        ContaDao cd = new ContaDao();
        Conta conta = new Conta();
        conta = cd.consultar(c.getId_cliente());
        tx_saldo.setText("R$" + String.valueOf(conta.getSaldo()));

        tf_valor.setText("");

    }

    @FXML
    void menu_sair(ActionEvent event) throws Exception {
        FXMLDocumentController fc = new FXMLDocumentController();
        Stage stage2 = new Stage();
        fc.start(stage2);
        stagehc.close();

    }

    @FXML
    void menu_transferir(ActionEvent event) throws SQLException {
        tela_dados.setVisible(false);
        tela_extrato.setVisible(false);

        tela_sacar.setVisible(false);
        tela_depositar.setVisible(false);

        lb_msg.setVisible(false);

        tela_operacoes.setVisible(true);
        tela_transferir.setVisible(true);

        lb_titulo.setText("TRANSFERIR");

        ContaDao cd = new ContaDao();
        Conta conta = new Conta();
        conta = cd.consultar(c.getId_cliente());
        tx_saldo.setText("R$" + String.valueOf(conta.getSaldo()));

        tf_valor.setText("");

    }

    @FXML
    void menu_dados(ActionEvent event) throws SQLException {
        tela_extrato.setVisible(false);
        tela_operacoes.setVisible(false);

        lb_erro_dados.setVisible(false);

        tela_dados.setVisible(true);

        lb_titulo.setText("VIZUALIZAR DADOS");

        ContaDao cd = new ContaDao();
        Conta conta = new Conta();
        AgenciaDao agenciaD = new AgenciaDao();

        tf_nome.setText(c.getNome());
        tf_cpf.setText(c.getCpf());
        tf_rg.setText(c.getRg());
        tf_telefone.setText(c.getTelefone());
        tf_data.setText(c.getData_nas());
        tf_login.setText(c.getLogin());

        conta = cd.consultar(c.getId_cliente());

        tx_numconta.setText(conta.getNum_conta());
        tx_saldo_dados.setText("R$" + conta.getSaldo());

        List<Agencia> agencias = agenciaD.listar();
        List<String> agenciasFormatadas = new ArrayList<String>();
        for (Agencia agencia : agencias) {
            String agenciaFormatada = agencia.getId_agencia() + " - " + agencia.getNome() + " - "
                    + agencia.getEndereco() + " - " + agencia.getTelefone();
            agenciasFormatadas.add(agenciaFormatada);
        }
        cb_agencia.setItems(FXCollections.observableArrayList(agenciasFormatadas));
        cb_agencia.getSelectionModel().select(conta.getId_agencia() - 1);
    }

    @FXML
    void sacar(ActionEvent event) throws SQLException {
        if (!tf_valor.getText().isEmpty()) {
            ContaDao cd = new ContaDao();
            if (cd.sacar(c.getId_cliente(), Double.parseDouble(tf_valor.getText()))) {
                lb_msg.setVisible(true);
                lb_msg.setText("Saque realizado com sucesso!");
                Conta conta = new Conta();
                conta = cd.consultar(c.getId_cliente());
                tx_saldo.setText("R$" + String.valueOf(conta.getSaldo()));

                Extrato extrato = new Extrato();
                ExtratoDao ed = new ExtratoDao();

                extrato.setValor(Double.parseDouble(tf_valor.getText()));
                extrato.setTipo("Saque");
                extrato.setId_cliente(c.getId_cliente());
                extrato.setSaldo(cd.consultar(c.getId_cliente()).getSaldo());
                extrato.setOrigem(c.getCpf());
                extrato.setDestino(c.getCpf());

                ed.inserir(extrato);
            } else {
                lb_msg.setVisible(true);
                lb_msg.setText("Não foi possível realizar o saque!");

            }
        } else {
            lb_msg.setVisible(true);
            lb_msg.setText("Campo de valor vazio!");
        }

    }

    @FXML
    void transferir(ActionEvent event) throws SQLException {
        if (!tf_valor.getText().isEmpty() && !tf_destinatario.getText().isEmpty()) {
            ContaDao cd = new ContaDao();
            if (tf_destinatario.getText().equals(c.getCpf())) {
                lb_msg.setVisible(true);
                lb_msg.setText("Não é possível transferir para você mesmo!");

            } else if (cd.transferir(c.getId_cliente(), tf_destinatario.getText(),
                    Double.parseDouble(tf_valor.getText()))) {
                lb_msg.setVisible(true);
                lb_msg.setText("Transferência realizada com sucesso!");
                Conta conta = new Conta();
                conta = cd.consultar(c.getId_cliente());
                tx_saldo.setText("R$" + String.valueOf(conta.getSaldo()));

                Extrato extrato = new Extrato();
                ExtratoDao ed = new ExtratoDao();
                ClienteDao cliented = new ClienteDao();

                extrato.setValor(Double.parseDouble(tf_valor.getText()));
                extrato.setTipo("Transferência");
                extrato.setId_cliente(c.getId_cliente());
                extrato.setSaldo(cd.consultar(c.getId_cliente()).getSaldo());
                extrato.setOrigem(c.getCpf());
                extrato.setDestino(tf_destinatario.getText());

                ed.inserir(extrato);

            } else {
                lb_msg.setText("Você não pode transferir para você mesmo!");
            }
        } else {
            lb_msg.setVisible(true);
            lb_msg.setText("Não foi possível realizar a transferência!");

        }
    }

    @FXML
    void alterar_dados(ActionEvent event) throws SQLException {
        if (!tf_cpf.getText().isEmpty() && !tf_data.getText().isEmpty() && !tf_login.getText().isEmpty()
                && !tf_nome.getText().isEmpty() && !tf_rg.getText().isEmpty() && !tx_senha_atual.getText().isEmpty()
                && !tf_telefone.getText().isEmpty()) {
            Cliente clienteAtualizar = new Cliente();
            clienteAtualizar.setId_cliente(c.getId_cliente());
            clienteAtualizar.setNome(tf_nome.getText());
            clienteAtualizar.setCpf(tf_cpf.getText());
            clienteAtualizar.setRg(tf_rg.getText());
            clienteAtualizar.setTelefone(tf_telefone.getText());
            clienteAtualizar.setData_nas(tf_data.getText());
            clienteAtualizar.setLogin(tf_login.getText());

            if (!tf_senha_nova.getText().isEmpty()) {
                clienteAtualizar.setSenha(tf_senha_nova.getText());
            } else {
                clienteAtualizar.setSenha(tx_senha_atual.getText());
            }

            ClienteDao cd = new ClienteDao();

            if (cd.atualizar(clienteAtualizar, tx_senha_atual.getText())) {
                ContaDao contaDao = new ContaDao();
                String[] agenciaSplit = cb_agencia.getValue().split(" - ");
                if (contaDao.atualizar(Integer.valueOf(agenciaSplit[0]), c.getId_cliente())) {
                    setCliente(cd.consultar(String.valueOf(c.getId_cliente()), "id_cliente"));
                    menu_dados(event);
                    lb_erro_dados.setVisible(true);
                    lb_erro_dados.setText("Dados alterados com sucesso!");
                } else {
                    lb_erro_dados.setVisible(true);
                    lb_erro_dados.setText("Falha ao atualizar dados!");
                }
            } else {
                lb_erro_dados.setVisible(true);
                lb_erro_dados.setText("Falha ao atualizar dados!");

            }
        } else {
            lb_erro_dados.setVisible(true);
            lb_erro_dados.setText("Preencha todos os campos!");
        }
    }

    @FXML
    void excluir(ActionEvent event) throws Exception {

        ClienteDao cd = new ClienteDao();
        if (!tx_senha_atual.getText().isEmpty() && cd.consultar(String.valueOf(c.getId_cliente()), "id_cliente")
                .getSenha().equals(tx_senha_atual.getText())) {
            ContaDao contaDao = new ContaDao();
            ExtratoDao extratoDao = new ExtratoDao();
            if (extratoDao.excluir(c.getId_cliente()) && contaDao.excluir(c.getId_cliente())) {
                if(cd.excluir(c.getId_cliente())){
                FXMLDocumentController fc = new FXMLDocumentController();
                Stage stage2 = new Stage();
                fc.start(stage2);
                stagehc.close();
                }

            } else {
                lb_erro_dados.setVisible(true);
                lb_erro_dados.setText("Não foi possível excluir!");
            }
        } else {
            lb_erro_dados.setVisible(true);
            lb_erro_dados.setText("Confirme sua senha!");
        }
    }

    /**
     * Initializes the controller class.
     *
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            if (c.getNome() != null) {
                lb_titulo.setText("Bem-vindo " + c.getNome() + "!");

            }
            tela_dados.setVisible(false);
            tela_operacoes.setVisible(false);
            tela_extrato.setVisible(false);
        });

    }

    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/home.fxml"));
        Parent root = loader.load();
        HomeController hc = loader.getController();
        hc.setCliente(c);
        Scene scene = new Scene(root);
        setStagehc(stage);
        stage.setScene(scene);
        stage.show();

        // Parent root = FXMLLoader.load(getClass().getResource("/View/home.fxml"));
        // HomeController hc = new HomeController();
        // hc.setCliente(c);
        // Scene scene = new Scene(root);

        // stage.setScene(scene);
        // stage.show();

        // setStage1(stage);

        // FXMLLoader loader = new
        // FXMLLoader(getClass().getResource("/View/home.fxml"));
        // Parent root = loader.load();
        // HomeController hc = loader.getController();
        // hc.setCliente(c);
        // Scene scene = new Scene(root);
        // stage.setScene(scene);
        // stage.show();

    }
}
