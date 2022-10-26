import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings("InfiniteLoopStatement")
public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(1020)){
            while (true){
                Socket socket = serverSocket.accept();
                serverClient(socket);
            }
        }
    }
    private static void serverClient(Socket socket) throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        System.out.println("Сервер открыт");

        while (true){
            String message = input.readLine();


            if(message.equals("exit")){
                System.out.println("Сервер закрыт");
                out.close();
                input.close();
                socket.close();
                System.exit(0);
            }
            out.write(message + "\n");
            out.flush();
        }
    }
}