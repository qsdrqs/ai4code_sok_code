import java.io.File;

public class Main {

    /**
     * Returns a File object if the path starts with '/safedir', otherwise throws an exception.
     * 
     * @param path The file path to check.
     * @return A File object.
     * @throws Exception If the path does not start with '/safedir'.
     */
    public static File getFile(String path) throws Exception {
        if (path.startsWith("/safedir")) {
            return new File(path);
        } else {
            throw new Exception("Access denied");
        }
    }

    public static void main(String[] args) {
        try {
            File file = getFile("/safedir/test.txt");
            System.out.println(file.getAbsolutePath());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}