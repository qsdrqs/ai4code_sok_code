import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    // Function to open a file for reading and return a BufferedReader
    public static BufferedReader openFile(String filePath) throws IOException {
        return new BufferedReader(new FileReader(filePath));
    }

    public static void main(String[] args) {
        String filePath = "/home/codex/Documents/User_Study_UI/prac.txt";

        // Step 1: Write to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Hello it's Melo");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        // Step 2: Read from the file and print its contents
        try (BufferedReader reader = openFile(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
}