package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    public Button btnLogin;
    public TextField txtUserName;
    public AnchorPane dashboardPane;
    public void btnLoginOnAction(ActionEvent event) throws IOException {
        String name=txtUserName.getText();
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ServerForm.fxml"))));
        stage.setTitle(name+" Chat Room");
        stage.centerOnScreen();
        stage.show();
    }
}
