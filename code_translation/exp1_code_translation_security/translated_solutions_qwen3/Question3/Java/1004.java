import java.io.*;

public class Main {

    /**
     * Function get_file
     *
     * Input: file path
     * Returns: BufferedReader object if file is valid and inside "/safedir"
     *          null otherwise
     */
    public static BufferedReader get_file(String filePath) {
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

    public static void main(String[] args) {
        // Create the test file in /safedir
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/safedir/test.txt"))) {
            writer.write("Hello");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Test case 1: valid file path
        BufferedReader testF = get_file("/safedir/test.txt");
        if (testF != null) {
            try (BufferedReader br = testF) {
                try {
                    System.out.println(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Test case 2: invalid file path (should fail)
        testF = get_file("/test.txt");
        System.out.println(testF);  // Prints "null"
    }
}