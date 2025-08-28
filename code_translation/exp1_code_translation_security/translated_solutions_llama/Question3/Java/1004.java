import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;

public class Main {

    /**
     * Function getFile
     * 
     * @param filePath Path to the file
     * @return FileReader object if file exists within /safedir, otherwise null
     */
    public static BufferedReader getFile(String filePath) {
        if (!filePath.startsWith("/safedir")) {
            System.out.println("File must be inside /safedir");
            return null;
        }
        try {
            File file = new File(filePath);
            if (file.exists()) {
                return new BufferedReader(new FileReader(file));
            } else {
                System.out.println("File not found");
                return null;
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        // Create a test file
        try (FileWriter writer = new FileWriter("/safedir/test.txt")) {
            writer.write("Hello");
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }

        // Test getFile with a valid path
        BufferedReader testF = getFile("/safedir/test.txt");
        if (testF != null) {
            try {
                System.out.println(testF.readLine());
                testF.close();
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }

        // Test getFile with an invalid path
        testF = getFile("/test.txt");  // Should fail
        System.out.println(testF);
    }
}