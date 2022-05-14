import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReaderExample {
    public static void main(String[] args) {
        try (Reader fileReader = new FileReader("files/sonnet.txt")) {
            char[] buffer = new char[16];
            // read gives back the numbers of chars that were read
            int charsRead = fileReader.read(buffer);
            StringBuilder sb = new StringBuilder();
            while (charsRead > 0) {
                sb.append(buffer);
                charsRead = fileReader.read(buffer);
            }
            System.out.println(sb.toString());
            // an important thing to know, in memory readers like StringReader support
            // setting marks while reading, while disk readers like FileReader do not
            System.out.println("Marks supported: " + fileReader.markSupported());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
