import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class BufferedWriterExample {
    public static void main(String[] args) {
        initializeInTryWithResourcesBlock();
    }
    /**
     * Not so clean.
     * If we don't flush the buffered writer it won't write anything to the
     * created file, this is because try with resources will only flush the writter
     * but nor the buffered writer, the best practice would be to write the buffered writer
     * in the try with resources block together with the writer.
     */
    public static void initializeInTryBlock() {
        try (Writer writer = new FileWriter("files/words.txt")) {
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("Hello from buffered writer.");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Cleaner then the previous.
     */
    public static void initializeInTryWithResourcesBlock() {
        try (Writer writer = new FileWriter("files/words.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write("Hello from buffered writer.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * The cleanest way to do this.
     */
    public static void useTheFactoryMethod() {
        Path path = Path.of("files/words.txt");
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            bufferedWriter.write("Hello from buffered writer.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
