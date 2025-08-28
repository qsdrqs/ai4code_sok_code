import java.io.*;

public class Main {

    // Method to open a file and return a BufferedReader
    public static BufferedReader openFile(String filePath) throws IOException {
        return new BufferedReader(new FileReader(filePath));
    }

    public static void main(String[] args) {
        String filePath = "/home/codex/Documents/User_Study_UI/prac.txt";

        // Write to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Hello it's Melo");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read from the file
        try {
            BufferedReader file = openFile(filePath);
            String line;
            while ((line = file.readLine()) != null) {
                System.out.println(line);
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}