import java.io.RandomAccessFile;
import java.io.IOException;
import java.nio.file.Paths;

public class FileGetter {
    public static RandomAccessFile getFile(String path) throws IOException {
        String normpath = Paths.get(path).normalize().toString();
        System.out.println(normpath);
        if (!normpath.startsWith("/safedir")) {
            System.out.println("out of bounds");
            return new RandomAccessFile("/dev/null", "r");
        } else {
            System.out.println("opening file " + normpath);
            return new RandomAccessFile(normpath, "rw");
        }
    }

    public static String readAll(RandomAccessFile file) throws IOException {
        StringBuilder sb = new StringBuilder();
        int b;
        while ((b = file.read()) != -1) {
            sb.append((char) b);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        RandomAccessFile f = getFile("/safedir/test1.txt");
        System.out.println(readAll(f));

        RandomAccessFile g = getFile("/dev/rand0");
        System.out.println(readAll(g));
    }
}