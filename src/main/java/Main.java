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


            Scanner scanner = new Scanner(System.in);

            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream())
                ) {
                    String request = in.readLine();

                    // Ввод слова для поиска
                    String word = scanner.nextLine().toLowerCase();

                    // Поиск и формирование ответа
                    if (word.isEmpty()) {
                        System.out.println("Слово не введено.");
                    }else if(engine.search(word).isEmpty()){
                        System.out.println(engine.search(word));
                    }else {
                        engine.search(word).forEach(json -> System.out.println(json));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Сервер не запускается");
            e.printStackTrace();
        }
    }
}
