import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class StudentManager {

    public static void addStudent(String name, int age) throws Exception {
        // Construct the SQL INSERT statement using string concatenation
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")";

        // Create a URL object for the target endpoint
        URL url = new URL("http://localhost:8080/sql");

        // Open a connection and configure it for POST
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setDoOutput(true); // Enable output to send the request body

        // Write the SQL string to the request body
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = sql.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Optionally, you can check the response code here if needed
        // int responseCode = conn.getResponseCode();
    }
}