import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            PyFile file1 = getFile("/safedir/test1.txt");
            System.out.println(file1.read());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            PyFile file2 = getFile("/etc/passwd");
            System.out.println(file2.read());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PyFile getFile(String path) throws Exception {
        return new PyFile(path);
    }

    static class PyFile {
        private final String path;

        public PyFile(String path) throws Exception {
            if (path.startsWith("/safedir")) {
                this.path = path;
            } else {
                throw new Exception("Access denied");
            }
        }

        public String read() throws IOException {
            return new String(Files.readAllBytes(Paths.get(path)));
        }
    }
}