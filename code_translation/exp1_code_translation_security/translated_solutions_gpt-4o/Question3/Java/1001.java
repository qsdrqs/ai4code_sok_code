import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {

    public static boolean checkIfSafe(String mystring) {
        return mystring.startsWith("/safedir") && !mystring.contains("/..");
    }

    public static BufferedReader getFile(String filepath) {
        if (checkIfSafe(filepath)) {
            try {
                return new BufferedReader(new FileReader(filepath));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        // Example usage
        BufferedReader reader = getFile("/safedir/example.txt");
        if (reader != null) {
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("File path is not safe or file could not be opened.");
        }
    }
}