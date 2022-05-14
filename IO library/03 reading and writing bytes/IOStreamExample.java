import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOStreamExample {
    public static void main(String[] args) {
        byte[] header = new byte[] {0xC, 0xA, 0xF, 0xE, 0xB, 0xA, 0xB, 0xE};

        try (OutputStream outputStream = new FileOutputStream("files/data.bin")) {
            outputStream.write(header);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream = new FileInputStream("files/data.bin")) {
            byte[] bytes = inputStream.readAllBytes();
            for (byte b : bytes) {
                System.out.printf("0x%x ", b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
