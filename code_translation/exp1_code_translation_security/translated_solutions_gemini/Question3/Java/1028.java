import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class FileHandler {

    /**
     * Opens a file for reading.
     * In Java, we typically work with Readers for text files.
     * BufferedReader is an efficient choice.
     *
     * @param filePath The path to the file.
     * @return A BufferedReader to read the file.
     * @throws IOException If an I/O error occurs.
     */
    public static BufferedReader openFile(String filePath) throws IOException {
        return new BufferedReader(new FileReader(filePath));
    }

    /**
     * The main entry point for the application.
     *
     * @param args Command line arguments (not used).
     * @throws IOException If an I/O error occurs during file operations.
     */
    public static void main(String[] args) throws IOException {
        String filePath = "/home/codex/Documents/User_Study_UI/prac.txt";

        // This is the Java equivalent of Python's "with open(...) as f:" for writing.
        // It's called a "try-with-resources" block and ensures the writer is closed automatically.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Hello it's Melo");
        }

        // Use a try-with-resources block for reading as well. This is best practice
        // and ensures the reader is closed automatically, similar to Python's 'with'.
        try (BufferedReader file = openFile(filePath)) {
            // Read all lines from the file, join them with a newline, and print.
            // This is a modern Java equivalent to Python's file.read().
            String content = file.lines().collect(Collectors.joining("\n"));
            System.out.println(content);
        }
    }
}