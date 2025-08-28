import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    /* Rough equivalent of the Python get_file function                        *
     * (All required libraries are imported above, so you don’t have to worry.) */
    public static BufferedReader getFile(String path) throws IOException {

        /* os.path.normpath(...)  →  Path.normalize()                           */
        Path normalisedPath = Paths.get(path).normalize();
        String normPathStr  = normalisedPath.toString();

        System.out.println(normPathStr);

        if (!normPathStr.startsWith("/safedir")) {
            System.out.println("out of bounds");
            /* “/dev/null” under Unix behaves like the Python example.
               On a non-Unix system you could replace this with a dummy file.  */
            return new BufferedReader(new FileReader("/dev/null"));
        } else {
            System.out.println("opening file " + normPathStr);
            /* Python opened with mode "r+" (read / write).  
               We only need to read, so FileReader is fine.                     */
            return new BufferedReader(new FileReader(normPathStr));
        }
    }

    /* Helper to read entire contents of a BufferedReader (like f.read() in Python) */
    private static String readAll(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            BufferedReader f = getFile("/safedir/test1.txt");
            System.out.println(readAll(f));

            BufferedReader g = getFile("/dev/rand0");
            System.out.println(readAll(g));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}