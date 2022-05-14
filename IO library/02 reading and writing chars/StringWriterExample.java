import java.io.IOException;
import java.io.StringWriter;

/**
 * String writer writes in an internal string buffer.
 * It is an in memory writer.
 */
public class StringWriterExample {
    public static void main(String[] args) {
        try (StringWriter stringWriter = new StringWriter()) {
            stringWriter.write("Hello World");
            stringWriter.flush();
            // two ways of print the content
            System.out.println(stringWriter.toString());
            StringBuffer sb = stringWriter.getBuffer();
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
