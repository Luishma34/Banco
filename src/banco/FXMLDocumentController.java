/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package banco;

import Dao.ClienteDao;
import Model.Cliente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXMLDocumentController extends Application implements Initializable {

    static Stage stage1;

    public static Stage getStage1() {
        return stage1;
    }

    public static void setStage1(Stage stage1) {
        FXMLDocumentController.stage1 = stage1;
    }

    @FXML
    private Button btn_registro;

    @FXML
    private Button btn_listar;

    @FXML
    private Button btnlog;

    @FXML
    private ImageView cacto;

    @FXML
    private AnchorPane fx_fundo;

    @FXML
    private Pane fx_fundo2;

    @FXML
    private Label label1;

    @FXML
    private Label label12;

    @FXML
    private TextField tf_login;

    @FXML
    private TextField tf_senha;

    @FXML
    private Label titulo;

    @FXML
    private ImageView user_img;

    @FXML
    void logar(ActionEvent event) throws Exception {
        if (!tf_login.getText().isEmpty() && !tf_senha.getText().isEmpty()) {
            ClienteDao cd = new ClienteDao();
            Cliente e = new Cliente();
            e.setLogin(tf_login.getText());
            e.setSenha(tf_senha.getText());
            if (cd.entrar(e)) {
                Cliente c = cd.consultar(e.getLogin(), "login");
                
                HomeController hc = new HomeController();
                hc.setCliente(c);
                Stage stage = new Stage();
                hc.start(stage);
                stage1.close();
                
            } else {

            }
        }
    }

    @FXML
    void redirecionar_registro(ActionEvent event) throws Exception {
        CadastroController cc = new CadastroController();
        Stage stage = new Stage();
        cc.start(stage);
        stage1.close();
    }

    @FXML
    void listar(ActionEvent event) {
        fx_fundo2.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        setStage1(stage);
        stage.setScene(scene);
        stage.show();
    }
}
