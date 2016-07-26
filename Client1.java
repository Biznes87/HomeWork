import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by andrey on 23/07/2016.
 */
public class Client1 {
    private Listener listener;
    private Socket socket;
    private PrintWriter writer;
    private static Form form;

    public Client1(Listener listener) {
        this.listener = listener;
    }

    public void connect() {
        try {
            socket = new Socket("localhost", Server.PORT);
            writer = new PrintWriter(socket.getOutputStream());
            processResponse();
        } catch (IOException e) {
            throw new CanNotConnectToServerException(e.getMessage(), e);
        }
    }

    public void sendMsg(String msg) {
        System.out.println("sending.. " + msg);
        writer.println(msg);
        writer.flush();
    }


    public void showResponse(String msg){
        System.out.println(msg);
    }

    private void processResponse() {
        new Thread(() -> {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (true) {
                    String s = null;
                    s = reader.readLine();
                    if (s == null)
                        break;
                    notifyListener(s);
                    form.textArea.append(s + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (socket != null)
                        socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void notifyListener(String s) {
        listener.processMessage(s);
    }





    public static void main(String[] args) {

        Client1 client = new Client1((s) -> {System.out.println(s);});

        form=new Form(client);
        client.connect();

        client.sendMsg("Hi there");
        client.sendMsg("My name is mr. Robot");

    }
}
