import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class UserManager extends Thread{
    String message = "";
    private ArrayList<UserManager> chats;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public UserManager(Socket socket,ArrayList<UserManager> chats) throws IOException {
        try{
            this.socket=socket;
            this.chats=chats;
            this.reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer=new PrintWriter(socket.getOutputStream(),true);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        try{
            while((message= reader.readLine())!=null) {
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
                for (UserManager all : chats) {
                    all.writer.println(message);
                }
            }
        }catch(SocketException ignore){
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                reader.close();
                writer.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


