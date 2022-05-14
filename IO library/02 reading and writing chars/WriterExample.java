import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriterExample {
    public static void main(String[] args) {
        try (Writer writer = new FileWriter("files/words.txt")) {
            writer.write("Hello World!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
