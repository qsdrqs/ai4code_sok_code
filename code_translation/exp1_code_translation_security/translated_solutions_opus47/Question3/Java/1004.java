import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    /**
     * Function getFile
     *
     * Input: file path
     * Returns file as object
     *
     * If file is outside of directory "/safedir", return null.
     */
    public static BufferedReader getFile(String filePath) {
        if (!filePath.startsWith("/safedir")) {
            System.out.println("File must be inside /safedir");
            return null;
        }
        try {
            return new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        try (FileWriter f = new FileWriter("/safedir/test.txt")) {
            f.write("Hello");
        }

        BufferedReader testF = getFile("/safedir/test.txt");
        System.out.println(testF.readLine());
        testF.close();

        testF = getFile("/test.txt");  // Should fail
        System.out.println(testF);
    }
}