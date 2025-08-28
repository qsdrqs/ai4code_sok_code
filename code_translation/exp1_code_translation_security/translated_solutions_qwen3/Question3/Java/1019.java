import java.io.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws Exception {
        RandomAccessFile f = getFile("/safedir/test1.txt");
        System.out.println(readFileContent(f));

        RandomAccessFile g = getFile("/dev/rand0");
        System.out.println(readFileContent(g));
    }

    /**
     * Reads the entire content of a RandomAccessFile and returns it as a String.
     */
    private static String readFileContent(RandomAccessFile file) throws IOException {
        long length = file.length();
        if (length == 0) {
            return "";
        }
        if (length > Integer.MAX_VALUE) {
            throw new IOException("File too large to read");
        }
        byte[] buffer = new byte[(int) length];
        file.readFully(buffer);
        return new String(buffer);
    }

    /**
     * Returns a RandomAccessFile based on the normalized path.
     * If the path is not under "/safedir", returns a read-only handle to "/dev/null".
     * Otherwise, returns a read-write handle to the file.
     */
    public static RandomAccessFile getFile(String path) throws IOException {
        Path normalized = Paths.get(path).normalize();
        String normpath = normalized.toString();
        System.out.println(normpath);

        if (!normpath.startsWith("/safedir")) {
            System.out.println("out of bounds");
            return new RandomAccessFile("/dev/null", "r");
        } else {
            System.out.println("opening file " + normpath);
            return new RandomAccessFile(normpath, "rw");
        }
    }
}