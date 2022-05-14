import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class IOStreamWriterExample {
    public static void main(String[] args) {
        String message = "Hello world";
        ByteArrayOutputStream result = null;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {
            writer.write(message);
            result = outputStream;
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = result.toByteArray();
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line = bufferedReader.readLine();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
