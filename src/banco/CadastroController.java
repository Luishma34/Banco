/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package banco;

import Dao.AgenciaDao;
import Dao.ClienteDao;
import Dao.ContaDao;
import Model.Agencia;
import Model.Cliente;
import Model.Conta;

import static banco.FXMLDocumentController.setStage1;
import static banco.FXMLDocumentController.stage1;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CadastroController implements Initializable {

    static Stage stage1;

    public static Stage getStage1() {
        return stage1;
    }

    public static void setStage1(Stage stage1) {
        CadastroController.stage1 = stage1;
    }

    @FXML
    private ChoiceBox<String> agencia;

    @FXML
    private Button btn_cadastrar;

    @FXML
    private ImageView cabana;

    @FXML
    private ImageView cacto;

    @FXML
    private ImageView cacto2;

    @FXML
    private ImageView cacto3;

    @FXML
    private ImageView cacto4;

    @FXML
    private ImageView cavalo;

    @FXML
    private Label lb_agencia;

    @FXML
    private Label lb_cpf;

    @FXML
    private Label lb_data;

    @FXML
    private Label lb_login;

    @FXML
    private Label lb_msg;

    @FXML
    private Label lb_nome;

    @FXML
    private Label lb_rg;

    @FXML
    private Label lb_senha;

    @FXML
    private Label lb_tel;

    @FXML
    private ImageView passaro1;

    @FXML
    private ImageView passaro2;

    @FXML
    private TextField tf_cpf;

    @FXML
    private TextField tf_data;

    @FXML
    private TextField tf_login;

    @FXML
    private TextField tf_nome;

    @FXML
    private TextField tf_rg;

    @FXML
    private PasswordField tf_senha;

    @FXML
    private TextField tf_tel;

    @FXML
    private Label titulo;

    @FXML
    void cadastrar(ActionEvent event) throws Exception {
        if (!tf_cpf.getText().isEmpty() && !tf_data.getText().isEmpty() && !tf_login.getText().isEmpty()
                && !tf_nome.getText().isEmpty() && !tf_rg.getText().isEmpty() && !tf_senha.getText().isEmpty()
                && !tf_tel.getText().isEmpty()) {

            ClienteDao cd = new ClienteDao();
            Cliente cliente = new Cliente();

            cliente.setNome(tf_nome.getText());
            cliente.setCpf(tf_cpf.getText());
            cliente.setData_nas(tf_data.getText());
            cliente.setTelefone(tf_tel.getText());
            cliente.setRg(tf_rg.getText());
            cliente.setLogin(tf_login.getText());
            cliente.setSenha(tf_senha.getText());

            if (cd.inserir(cliente)) {
                Conta conta = new Conta();
                ContaDao contaDao = new ContaDao();
                List<Conta> listaContas = contaDao.lista_contas();

                String[] agenciaSplit = agencia.getValue().split(" - ");
                conta.setId_agencia(Integer.valueOf(agenciaSplit[0]));
                conta.setId_cliente(cd.consultar(cliente.getCpf(), "cpf").getId_cliente());
                conta.setSaldo(0);
                conta.setNum_conta(String.format("%03d%03d%03d", conta.getId_agencia(), conta.getId_cliente(),
                        listaContas.size() + 1));

                if (contaDao.inserir(conta) == true) {

                    cliente.setId_cliente(cd.consultar(cliente.getCpf(), "cpf").getId_cliente());

                    HomeController hc = new HomeController();
                    hc.setCliente(cliente);
                    Stage stage = new Stage();
                    hc.start(stage);
                    stage1.close();

                    // FXMLLoader loader = new
                    // FXMLLoader(getClass().getResource("/View/home.fxml"));
                    // Parent root = loader.load();
                    // HomeController hc = loader.getController();
                    // hc.setCliente(cliente);
                    // Stage stage = new Stage();
                    // stage.setScene(new Scene(root));
                    // stage.show();
                    // stage1.close();
                } else {
                    lb_msg.setVisible(true);
                    lb_msg.setText("Erro ao cadastrar");
                }

            } else {
                lb_msg.setVisible(true);
                lb_msg.setText("Erro ao cadastrar");
            }
        } else {
            lb_msg.setVisible(true);
            lb_msg.setText("Preencha todos os campos!");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {

            AgenciaDao agenciaDao = new AgenciaDao();
            List<Agencia> agencias = agenciaDao.listar();
            List<String> agenciasFormatadas = new ArrayList<String>();
            for (Agencia agencia : agencias) {
                String agenciaFormatada = agencia.getId_agencia() + " - " + agencia.getNome() + " - "
                        + agencia.getEndereco() + " - " + agencia.getTelefone();
                agenciasFormatadas.add(agenciaFormatada);
            }
            agencia.setItems(FXCollections.observableArrayList(agenciasFormatadas));
            agencia.getSelectionModel().selectFirst();

        });
    }

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/cadastro.fxml"));

        Scene scene = new Scene(root);
        setStage1(stage);
        stage.setScene(scene);
        stage.show();
    }

}
