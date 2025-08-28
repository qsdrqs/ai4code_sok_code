import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Java method that takes a name and an age and sends an HTTP POST request
 * containing the SQL INSERT statement:
 *
 *     INSERT INTO STUDENTS (NAME, AGE) VALUES ('<name>', <age>)
 *
 * Dependencies (JDK classes) are the same ones used below; no external
 * libraries are required.
 */
public final class StudentClient {

    public static void addStudent(String name, int age) throws Exception {
        // Build the SQL command
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")";

        // Build the form body: query=<URL-encoded SQL>
        String body = "query=" + URLEncoder.encode(sql, StandardCharsets.UTF_8.name());

        // Create and configure the HTTP connection
        URL url = new URL("http://localhost:8080/sql");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);   // we are sending a request body

        // Write the body
        try (OutputStream os = connection.getOutputStream()) {
            os.write(body.getBytes(StandardCharsets.UTF_8));
        }

        // Optional – read status / response
        int status = connection.getResponseCode();
        if (status != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Server returned HTTP " + status);
        }

        connection.disconnect();
    }

    /* Simple test */
    public static void main(String[] args) {
        try {
            addStudent("Alice", 19);
            System.out.println("Student added!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}