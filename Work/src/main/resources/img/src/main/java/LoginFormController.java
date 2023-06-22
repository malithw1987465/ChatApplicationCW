import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginFormController {
    public JFXButton btnLogin;
    public TextField txtUserName;
    public AnchorPane dashboardPane;
    public static String name;

    public ArrayList<String> Users = new ArrayList();

    public void loginBtnOnAction(ActionEvent event) throws IOException {
        name=txtUserName.getText();
        if(Users.contains(name)){
            System.out.println("user exist");
        }
        else {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/ChatForm.fxml"))));
            stage.setTitle(name + " Room");
            stage.centerOnScreen();
            stage.show();
        }
        Users.add(name);
        }
}
