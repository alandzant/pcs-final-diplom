import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            System.out.println("Идет соединение с сервером на порту 8989");
            BooleanSearchEngine engine = new BooleanSearchEngine(new File("pdfs"));

            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream())
                ) {
                    String request = in.readLine();
                    String answer;

                    // Поиск и формирование ответа
                    if (request.isEmpty()) {
                        answer = "Слово не введено.";
                    } else {
                        engine.search(request).stream().forEach(jsonObject -> {
                            System.out.println(jsonObject);
                        });
                        continue;
                    }

                    out.println(answer);
                }
            }
        } catch (IOException e) {
            System.out.println("Сервер не запускается");
            e.printStackTrace();
        }
    }
}
