
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            BooleanSearchEngine engine = new BooleanSearchEngine(new File("pdfs"));

            Scanner scanner = new Scanner(System.in);

            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream())
                ) {
                    String request = in.readLine();
                    String answer;

                    // Ввод слова для поиска
                    System.out.print("Введите слово для поиска: ");
                    String word = scanner.nextLine();

                    // Поиск и формирование ответа
                    if (word.isEmpty()) {
                        answer = "Слово не введено.";
                    } else {
                        answer = engine.search(word).toString();
                    }

                    System.out.println(answer);
                }
            }
        } catch (IOException e) {
            System.out.println("Сервер не запускается");
            e.printStackTrace();
        }
    }
}