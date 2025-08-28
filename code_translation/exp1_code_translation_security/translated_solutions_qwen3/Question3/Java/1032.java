import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        try {
            FileWrapper file = getFile("/safedir/test1.txt");
            System.out.println(file.read());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Uncomment the following block to test the denied path
        /*
        try {
            FileWrapper file2 = getFile("/etc/passwd");
            System.out.println(file2.read());
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }

    public static FileWrapper getFile(String path) throws Exception {
        if (path.startsWith("/safedir")) {
            return new FileWrapper(path);
        } else {
            throw new Exception("Access denied");
        }
    }

    static class FileWrapper {
        private final String path;

        public FileWrapper(String path) {
            this.path = path;
        }

        public String read() throws IOException {
            // Read all bytes and convert to String using UTF-8 encoding
            return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        }
    }
}