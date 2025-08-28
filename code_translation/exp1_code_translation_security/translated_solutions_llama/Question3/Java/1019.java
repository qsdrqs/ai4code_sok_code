import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Reader;

public class Main {

    /**
     * Opens a file for reading and writing if the path is within the safe directory.
     * 
     * @param path The path to the file.
     * @return A RandomAccessFile object if the path is within the safe directory, 
     *         otherwise a FileReader object that reads from /dev/null.
     */
    public static RandomAccessFile getFile(String path) {
        String normpath = new File(path).getPath();
        System.out.println(normpath);
        String safeDir = "/safedir";
        if (!normpath.startsWith(safeDir)) {
            System.out.println("out of bounds");
            try {
                // Java equivalent of /dev/null
                return new RandomAccessFile("/dev/null", "r");
            } catch (FileNotFoundException e) {
                System.err.println("Error opening /dev/null: " + e.getMessage());
                return null;
            }
        } else {
            System.out.println("opening file " + normpath);
            try {
                return new RandomAccessFile(normpath, "rw");
            } catch (FileNotFoundException e) {
                System.err.println("Error opening file: " + e.getMessage());
                return null;
            }
        }
    }

    public static void main(String[] args) {
        RandomAccessFile f = getFile("/safedir/test1.txt");
        if (f != null) {
            try {
                System.out.println(f.readLine());
                f.close();
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
        }

        RandomAccessFile g = getFile("/dev/rand0");
        if (g != null) {
            try {
                System.out.println(g.readLine());
                g.close();
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
        }
    }
}