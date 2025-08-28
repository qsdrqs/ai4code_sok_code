import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    /**
     * Opens a file located in a specific directory.
     * 
     * @param path The path to the file.
     * @return A Scanner object representing the opened file.
     * @throws FileNotFoundException If the file does not exist.
     */
    public static Scanner openFile(String path) throws FileNotFoundException {
        // Find the last occurrence of "/safedir" in the path
        int index = path.lastIndexOf("/safedir");

        // If "/safedir" is not found, return null or throw an exception
        if (index == -1) {
            throw new FileNotFoundException("Invalid path: " + path);
        }

        // Extract the new path
        String newPath = path.substring(index);

        // Open the file
        File file = new File(newPath);
        return new Scanner(file);
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = openFile("/path/to/safedir/file.txt");
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}