package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;


public class LoginForm extends Application {
    public Button btnLogin;
    public TextField txtUserName;
    public AnchorPane dashboardPane;

    public void btnLoginOnAction(ActionEvent event) throws IOException {
        String name=txtUserName.getText();
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/ServerForm.fxml"))));
        stage.setTitle(name+" Room");
        stage.centerOnScreen();
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/LoginForm.fxml"));
        primaryStage.setTitle("Login Portal");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
