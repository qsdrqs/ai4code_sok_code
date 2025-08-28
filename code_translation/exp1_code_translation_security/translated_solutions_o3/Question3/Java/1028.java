import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    // Equivalent of the Python openFile function
    // Returns a BufferedReader that can be used to read the file.
    public static BufferedReader openFile(String filePath) throws IOException {
        return new BufferedReader(new FileReader(filePath));
    }

    public static void main(String[] args) {
        String filePath = "/home/codex/Documents/User_Study_UI/prac.txt";

        // Write "Hello it's Melo" to the file (overwrites if it already exists)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Hello it's Melo");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Read the file and print its contents
        try (BufferedReader reader = openFile(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}