import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Main {

    /**
     * Opens a file in read mode.
     * 
     * @param filePath The path to the file to be opened.
     * @return A BufferedReader object for the opened file.
     * @throws IOException If an I/O error occurs.
     */
    public static BufferedReader openFile(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        return new BufferedReader(fileReader);
    }

    public static void main(String[] args) {
        String filePath = "/home/codex/Documents/User_Study_UI/prac.txt";

        // Write to the file
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write("Hello it's Melo");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        // Open and read the file
        try (BufferedReader file = openFile(filePath)) {
            String line;
            while ((line = file.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
}