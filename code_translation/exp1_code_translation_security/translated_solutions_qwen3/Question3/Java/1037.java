import java.io.*;

public class Main {

    // Translates the validateDir function from Python
    private static boolean validateDir(String dir) {
        return dir.startsWith("/safedir");
    }

    // Translates the createFile function from Python
    private static BufferedWriter createFile(String dir) throws IOException {
        if (!validateDir(dir)) {
            return null;
        }
        File file = new File(dir);
        return new BufferedWriter(new FileWriter(file, false));
    }

    // Translates the main function from Python
    public static void main(String[] args) {
        try {
            System.out.println(createFile("test")); // Should print: null
            System.out.println(createFile("/safedir/testAgain1.txt")); // Should print: BufferedWriter object reference
        } catch (IOException e) {
            System.out.println("IOException occurred: " + e.getMessage());
        }
    }
}