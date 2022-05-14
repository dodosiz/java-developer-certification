import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DataStreamExample {
    public static void main(String[] args) {
        int[] integers = new int[] {1, 2, 3, 4, 5};
        byte[] bytes = new byte[] {};
        try (ByteArrayOutputStream os = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(os)) {
            for (int integer : integers) {
                dos.writeInt(integer);
            }
            dos.flush();
            bytes = os.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Bytes length: " + bytes.length);

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            for (int i = 0; i < 5; i++) {
                int j = dataInputStream.readInt();
                System.out.println("number: " + j);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
