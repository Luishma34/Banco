/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package banco;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Banco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        FXMLDocumentController login = new FXMLDocumentController();
        Stage stage = new Stage();
        login.start(stage);

    }

}
