import java.io.*;

public class Translator {

    public static Object returnObject(String path) {
        try {
            File file = new File(path);
            String rpath = file.getCanonicalPath();

            // Check if the canonical path starts with "/safedir"
            if (rpath.length() >= 8 && rpath.substring(0, 8).equals("/safedir")) {
                return -1;
            }

            try {
                FileWriter fp = new FileWriter(file);
                return fp;
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
                return null;
            }

        } catch (IOException e) {
            // This corresponds to errors in resolving the canonical path
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        String fileName = "/tmp/foo";
        System.out.println(returnObject(fileName));
    }
}