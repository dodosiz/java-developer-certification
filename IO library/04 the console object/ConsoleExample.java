import java.io.Console;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleExample {
    public static void main(String[] args) {
        Console console = System.console();

        PrintWriter writer = console.writer();
        writer.printf("What is your name? ");

        String name = console.readLine();
        writer.printf("Hello %s.\n", name);

        writer.printf("What is your password? ");
        char[] password = console.readPassword();
        String passwordString = new String(password);

        writer.printf("%s is an easy password to quess.\n", passwordString);

        writer.printf("What are your favourite colors %s? ", name);
        String colors = console.readLine();
        Scanner scanner = new Scanner(colors);
        List<String> colorList = scanner
            .useDelimiter(" ")
            .tokens()
            .collect(Collectors.toList());
        scanner.close();
        writer.println(colorList);
    }
}
