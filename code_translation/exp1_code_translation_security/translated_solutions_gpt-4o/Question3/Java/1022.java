import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {

    /**
     * Return file at file location
     */
    public static BufferedReader getFile(String fileLocation) {
        if (fileLocation.toLowerCase().trim().contains("/safedir")) {
            try {
                return new BufferedReader(new FileReader(fileLocation));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Provided File location is inaccessible");
        }
        return null;
    }

    public static void main(String[] args) {
        BufferedReader reader = getFile("/safedir/test1.txt");
        if (reader != null) {
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}