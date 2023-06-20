package Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class UserManager extends Thread{

    String message="";
    private ArrayList<Controllers.UserManager> clients;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public UserManager(Socket socket, ArrayList<Controllers.UserManager> clients) throws IOException {
        this.clients=clients;
        this.socket=socket;
        this.reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer=new PrintWriter(socket.getOutputStream(),true);
    }

    public void run(){
        try {
            String msg;
            while ((msg = reader.readLine()) != null) {
                if (msg.equalsIgnoreCase( "exit")) {
                    break;
                }
                for (Controllers.UserManager cl : clients) {
                    cl.writer.println(msg);
                    System.out.println(msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
                writer.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


