import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The Path object was introduced in java after the File object for
 * one specific reason, to make platform dependent (based on the OS)
 * implementations.
 */
public class PathExample {
    public static void main(String[] args) {
        // two factory methods to create paths
        Path path1 = Paths.get("files/images/seaside.jpg");
        Path path2 = Path.of("files/images/mountain.jpg");

        System.out.println("Path1: " + path1);
        System.out.println("Path2: " + path2);
        System.out.println("Path1 class: " + path1.getClass());

        // absolute path is platform dependent, in windows absolute paths
        // start with c:\ or \\
        Path path3 = Paths.get("//files/example");
        Path path4 = Paths.get("C:/files/example");
        System.out.println("path1 absolute? " + path1.isAbsolute());
        System.out.println("path1 root: " + path1.getRoot());
        System.out.println("path3 absolute? " + path3.isAbsolute());
        System.out.println("path3 root: " + path3.getRoot());
        System.out.println("path4 absolute? " + path4.isAbsolute());
        System.out.println("path4 root: " + path4.getRoot());

        // for some tricky path methods look at TrickyMethods (very important)

    }
}
