import java.io.File;

public class FileAccess {

    public static File getFile(String path) throws Exception {
        if (path.startsWith("/safedir")) {
            return new File(path);
        } else {
            throw new Exception("Access denied");
        }
    }

    public static void main(String[] args) {
        try {
            File file = getFile("/safedir/example.txt");
            System.out.println("File accessed: " + file.getPath());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}