import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Main {

    /** Equivalent of the Python “load” function. */
    public static BufferedReader load(String pathStr) throws IOException {
        // Resolve the path to an absolute, normalised form (similar to Path.resolve()).
        Path path = Paths.get(pathStr).toAbsolutePath().normalize();

        /*
         * Safety check:
         *  - The first “name element” of an absolute path such as “/safedir/foo.txt”
         *    is “safedir”.
         *  - If it is missing or not equal to "safedir", refuse access.
         */
        if (path.getNameCount() < 1 ||               // path like “/”
            !path.getName(0).toString().equals("safedir")) {
            throw new RuntimeException("Cannot access unsafe file");
        }

        return Files.newBufferedReader(path);
    }

    public static void main(String[] args) {
        try {
            // This one succeeds (provided the file exists).
            System.out.println(
                load("/safedir/foo.txt").lines().collect(Collectors.joining("\n"))
            );

            // This one will throw RuntimeException.
            System.out.println(
                load("/tmp/foo.txt").lines().collect(Collectors.joining("\n"))
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}