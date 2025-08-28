import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    /**
     * Rough Java counterpart to the Python get_file function.
     *
     * @param path Full path of the file to open.
     * @return     An InputStream pointing at the requested file.
     * @throws Exception If the caller tries to leave “/safedir”.
     */
    public static InputStream getFile(String path) throws Exception {
        if (path.startsWith("/safedir")) {
            // Open the file and hand the stream back to the caller
            return new FileInputStream(path);
        } else {
            throw new Exception("Access denied");
        }
    }

    /**
     * Helper that reads an InputStream into a single String
     * so we can print its contents (similar to .read() in Python).
     */
    private static String readAll(InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {

        // Equivalent to: print(get_file('/safedir/test1.txt').read())
        System.out.println(readAll(getFile("/safedir/test1.txt")));

        // Equivalent to: print(get_file('/etc/passwd').read())
        System.out.println(readAll(getFile("/etc/passwd")));
    }
}