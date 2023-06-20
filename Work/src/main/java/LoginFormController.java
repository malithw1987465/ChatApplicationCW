import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginFormController {
    public Button btnLogin;
    public TextField txtUserName;
    public AnchorPane dashboardPane;
    public static String name;

    public ArrayList<String> Users = new ArrayList();

    public void btnLoginOnAction(ActionEvent event) throws IOException {
        name=txtUserName.getText();
        if(Users.contains(name)){
            System.out.println("User exist");
        }
        else {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("View/UserForm.fxml"))));
            stage.setTitle(name + " Room");
            stage.centerOnScreen();
            stage.show();
        }
        Users.add(name);
    }
}

