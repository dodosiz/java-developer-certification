import java.io.File;
import java.io.IOException;

class FileExample {
    public static void main(String[] args) throws IOException {
        File file = new File("files/sonnet.txt");
        System.out.println("File: " + file);

        // if I create a new file object with a file that does not exist
        // java will not complain
        File file2 = new File("files/sonnet2.txt");
        System.out.println("File: " + file2);

        System.out.println("Exists? " + file.exists());
        System.out.println("Is directory? " + file.isDirectory());
        System.out.println("Is file? " + file.isFile());

        // if the file does not exist we can create it
        boolean isCreated = file2.createNewFile();
        System.out.println("File created? " + isCreated);

        // we can also create directories
        File directory = new File("files/data");
        boolean directoryCreated = directory.mkdir();
        System.out.println("Directory created? " + directoryCreated);

        File directories = new File("files/data2/new");
        boolean directoriesCreated = directories.mkdirs();
        System.out.println("Directories created? " + directoriesCreated);

        // delete a directory
        boolean directoryDeleted = directory.delete();
        System.out.println("Directory deleted? " + directoryDeleted);

        // name, parent and path of files or directories
        System.out.println("Name: " + directory.getName());
        System.out.println("Parent: " + directory.getParent());
        System.out.println("Path: " + directory.getPath());

        // absolute and cannonical path
        File dir = new File("files/example/../data/images/../new");
        // the absolute path displays the value as it is
        System.out.println("Absolute path: " + dir.getAbsolutePath());
        // the cannonical path simplifies the .. points
        System.out.println("Cannonical path: " + dir.getCanonicalPath());
    }
}