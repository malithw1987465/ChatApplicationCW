import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
   private static final ArrayList<UserManager> chats=new ArrayList<>();

   public static void main(String[] args) {
      ServerSocket serverSocket;
      Socket socket;
      try {
         serverSocket=new ServerSocket(8000);
         while(true){
            System.out.println("Waiting for clients");
            socket= serverSocket.accept();
            System.out.println("connected");
            UserManager chatThread=new UserManager(socket,chats);
            chats.add(chatThread);
            chatThread.start();
         }

      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
