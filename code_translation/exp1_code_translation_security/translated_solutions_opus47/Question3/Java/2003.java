import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static BufferedReader load(String pathStr) throws Exception {
        Path path = Paths.get(pathStr).toAbsolutePath().normalize();
        if (path.getNameCount() < 1 || !path.getName(0).toString().equals("safedir")) {
            throw new Exception("Cannot access unsafe file");
        }
        return new BufferedReader(new FileReader(path.toFile()));
    }

    public static String readAll(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = reader.read()) != -1) {
            sb.append((char) c);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(readAll(load("/safedir/foo.txt")));
        System.out.println(readAll(load("/tmp/foo.txt")));
    }
}