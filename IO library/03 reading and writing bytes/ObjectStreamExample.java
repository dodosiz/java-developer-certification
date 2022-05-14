import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectStreamExample {
    public static void main(String[] args) {
        User user1 = new User("Paul", 23);
        User user2 = new User("Jennifer", 25);
        try (FileOutputStream fos = new FileOutputStream("files/users.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            // in order to write an object into a file, the object has to implement
            // the serializable interface else we get a NotSerializableException
            oos.writeObject(user1);
            oos.writeObject(user2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream("files/users.bin");
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            User jennifer = (User) ois.readObject();
            User paul = (User) ois.readObject();
            System.out.println("Paul: " + jennifer);
            System.out.println("Jennifer: " + paul);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
