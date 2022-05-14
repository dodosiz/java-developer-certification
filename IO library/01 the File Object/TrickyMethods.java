import java.nio.file.Path;

public class TrickyMethods {
    public static void main(String[] args) {
        Path abolutePath = Path.of("C:/absolute/path");
        Path relativePath = Path.of("/relative/path");
        Path withImage = Path.of("path/with/image.png");
        Path withAnotherImage = Path.of("with/another/image2.png");
        Path imageOnly = Path.of("image.png");
        Path imageOnly2 = Path.of("image2.png");
        Path emptyPath = Path.of("");

        System.out.println("Resolve use cases:");
        // concatenate paths
        System.out.println(relativePath.resolve(withImage));
        // concatenate paths
        System.out.println(emptyPath.resolve(withAnotherImage));
        // take the absolut path
        System.out.println(relativePath.resolve(abolutePath));

        System.out.println("Resolve sibling use cases:");
        // take parent of first and apend to the second
        System.out.println(withImage.resolveSibling(withAnotherImage));
        // take parent of first and apend to the second
        System.out.println(withImage.resolveSibling(imageOnly2));
        // return the second path
        System.out.println(imageOnly2.resolveSibling(withImage));
        // return the second path
        System.out.println(imageOnly.resolveSibling(imageOnly2));

        Path oneRelativePath = Path.of("/files/images/");
        Path anotherRelativePath = Path.of("/files/videos/");
        Path oneAbsolutePath = Path.of("c:/files/images/");
        Path anotherAbsolutePath = Path.of("c:/files/videos/");
        // will print how to get from one to another
        System.out.println(oneAbsolutePath.relativize(anotherAbsolutePath));
        // will print how to get from one to another
        System.out.println(oneRelativePath.relativize(anotherRelativePath));
        // will throw an IllegalArgument exception
        // System.out.println(oneAbsolutePath.relativize(anotherRelativePath));

        Path unnormalized = Path.of("videos/../videos/../videos/images");
        System.out.println("normalized: " + unnormalized.normalize());
    }
}
