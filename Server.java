import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by andrey on 23/07/2016.
 */
public class Server {

    public static final int PORT = 8189;

    public void startServer(){
        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            while(true){
                initConnection(serverSocket.accept());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void initConnection(Socket socket){
        new Thread(()->{
            try{
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                String s = null;
                 while((s = reader.readLine()) != null) {

                    System.out.println(" got a message: " + s);
                    writer.println("echo: " + s);
                    writer.flush();

                   //  s = null;
                }



            } catch(IOException e){
                e.printStackTrace();
            } finally {
                cleanUpSocket(socket);
            }

        }).start();
    }

    private void cleanUpSocket(Socket socket) {
        if (socket != null)
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        new Server().startServer();
    }
}
