import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;
import java.util.ResourceBundle;

public class UserFormController extends Thread implements Initializable {
    public AnchorPane dashboardPane;
    public TextField txtMessage;
    public VBox vBox;
    public Button btnSend;
    public ImageView imgimoji;
    public ImageView imgcamera;
    public Button btnCamera;
    public Label lblName;
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    BufferedReader reader;
    PrintWriter writer;
    private File filePath;
    private String message = "";
    private String reply = "";
//Connet
    public  void connectSocket() {
        try {
            socket = new Socket("localhost", 8000);
            System.out.println("socket is connected with the server");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msg = reader.readLine();
                String[] token = msg.split(" ");
                String cmd = token[0];
                System.out.println(cmd);
                StringBuilder fullMsg = new StringBuilder();
                for (int i = 1; i < token.length; i++) {
                    fullMsg.append(token[i]);
                }
                String[] massageAr = msg.split(" ");
                String string = "";
                for (int i = 0; i < massageAr.length - 1; i++) {
                    string += massageAr[i + 1] + " ";
                }

                Text text = new Text(string);
                String fChar = "";

                if (string.length() > 3) {
                    fChar = string.substring(0, 3);
                }

                if (fChar.equalsIgnoreCase("img")) {
                    string = string.substring(3, string.length() - 1);

                    File file = new File(string);
                    Image image = new Image(file.toURI().toString());

                    ImageView imageView = new ImageView(image);

                    imageView.setFitWidth(70);
                    imageView.setFitHeight(70);

                    HBox hBox = new HBox(10);
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);//bottom-right
                    //vBox.setAlignment(Pos.BOTTOM_LEFT);
                    //hBox.setAlignment(Pos.CENTER_LEFT);

                    ///   hBox.getChildren().add(imageView);

                    if (!cmd.equalsIgnoreCase(/*LoginFormController.name*/lblName.getText())) {
                        vBox.setAlignment(Pos.BOTTOM_LEFT);//top left
                        hBox.setAlignment(Pos.CENTER_LEFT);//center right

                        Text text1 = new Text("  " + cmd + " :");
                        hBox.getChildren().add(text1);
                        hBox.getChildren().add(imageView);
                    } else {
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);//bottom right
                        hBox.getChildren().add(imageView);
                        Text text1 = new Text(": Me ");
                        hBox.getChildren().add(text1);
                    }

                    Platform.runLater(() -> vBox.getChildren().addAll(hBox));

                } else {
                    TextFlow tempTextFlow = new TextFlow();

                    if (!cmd.equalsIgnoreCase(/*LoginFormController.name*/lblName.getText() + ":")) {
                        Text name = new Text(cmd + " ");
                        name.getStyleClass().add("name");
                        tempTextFlow.getChildren().add(name);
                    }

                    tempTextFlow.getChildren().add(text);
                    tempTextFlow.setMaxWidth(120);

                    TextFlow textFlow = new TextFlow(tempTextFlow);
                    textFlow.setStyle("-fx-background-color:#ff6b81;" + "-fx-background-radius: 20px;" + "-fx-font-size: 17px;");
                    textFlow.setPadding(new Insets(5, 10, 5, 10));
                    HBox hBox = new HBox(10);
                    hBox.setPadding(new Insets(5));

                    if (!cmd.equalsIgnoreCase(/*LoginFormController.name*/lblName.getText()+ ":")) {
                        vBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.getChildren().add(textFlow);
                    } else {
                        Text text1 = new Text(fullMsg + ": Me");
                        TextFlow textFlow1 = new TextFlow(text1);
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(textFlow1);
                        textFlow1.setStyle("-fx-background-color:#7bed9f;" + "-fx-background-radius: 20px;" + "-fx-font-size: 17px;");
                        textFlow1.setPadding(new Insets(5, 10, 5, 10));
                    }
                    Platform.runLater(() -> vBox.getChildren().addAll(hBox));
                }

                System.out.println(fullMsg);
                if (cmd.equalsIgnoreCase(/*LoginFormController.name*/lblName.getText() + ":")) {
                    continue;
                } else if (fullMsg.toString().equalsIgnoreCase("bye")) {
                    break;
                }
            }
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public void bntSendOnAction(ActionEvent event) {
        String msg=txtMessage.getText();
        writer.println(lblName.getText()+" :"+msg);
        HBox hBox=new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setPadding(new Insets(5,5,5,10));
        Text text=new Text("Me :"+msg);
        text.setStyle("-fx-font-size: 15px");
        TextFlow textFlow=new TextFlow(text);
        textFlow.setStyle("-fx-color:rgb(239,242,255);"
                + "-fx-background-color: rgb(15,125,242);" +
                "-fx-background-radius: 20px");
        textFlow.setPadding(new Insets(5,10,5,10));
        text.setFill(Color.color(0.934, 0.945, 0.996));
        hBox.getChildren().add(textFlow);
        vBox.getChildren().add(hBox);
        writer.flush();
        txtMessage.setText("");
        if((msg.equalsIgnoreCase("BYE"))||(msg.equalsIgnoreCase("Logout"))){
            System.exit(0);
        }

    }

    public void btnCameraOnAction(MouseEvent mouseEvent) {
        try {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Image");
            this.filePath = fileChooser.showOpenDialog(stage);
            writer.println(LoginFormController.name + " " + "img" + filePath.getPath());
            writer.flush();
        } catch (NullPointerException e) {
            System.out.println("Image is not Selected!");
        }

    }

    public void btnEmojiOnAction(MouseEvent mouseEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectSocket();
        lblName.setText(LoginFormController.name);

    }



}
