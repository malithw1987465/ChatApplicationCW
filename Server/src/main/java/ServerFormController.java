import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController {
    public TextArea txtArea;
    public TextField txtMessage;
    public Button btnSend;
    public ImageView imgCamera;
    public AnchorPane dashboardPane;
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public String name=LoginFormController.name;

    private  String message="";
    private  String reply = "";

    public void initialize() {
        CheckClient();
    }


   private void CheckClient(){

        Thread initialThrade = new Thread(() -> {
            try {
                serverSocket=new ServerSocket(8000);
                txtArea.appendText("sever started !");
                socket=serverSocket.accept();
                txtArea.appendText("client accepted !");

                inputStream = new DataInputStream(socket.getInputStream());
                outputStream = new DataOutputStream(socket.getOutputStream());

                while (!message.equals("finish")){
                    message=inputStream.readUTF();
                    txtArea.appendText("client : "+"\n\t"+message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        initialThrade.start();
    }

    public void btnSendOnAction(ActionEvent event) {
        try {
            outputStream.writeUTF(txtMessage.getText().trim());
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void btnCameraOnAction(ActionEvent event) {
    }

    public void btnEmojiOnAction(ActionEvent event) {
    }
}
