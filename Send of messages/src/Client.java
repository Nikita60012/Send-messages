import java.io.*;
import java.net.Socket;


public class Client {
    public static void main(String[] args) throws IOException{
        try(Socket socket = new Socket("localhost", 1020)) {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Введите сообщение, также для того, чтобы завершить передачу сообщений, введите exit");
            while (true) {
                String message = reader.readLine();

                out.write(message + "\n");
                out.flush();
                if (message.equals("exit")) {
                    System.out.println("Работа завершена, до свидания");
                    out.close();
                    reader.close();
                    input.close();
                    socket.close();
                    break;
                }


                String response = input.readLine();
                System.out.println("response: " + response);
            }
        }catch (IOException e) {
            System.err.println(e);
        }
    }
}