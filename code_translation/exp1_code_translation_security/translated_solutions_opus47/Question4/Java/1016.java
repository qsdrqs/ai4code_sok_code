import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class StudentManager {
    /* Java function that takes as input a String name and an int age and adds a new entry 
       to an SQL table named "STUDENTS" with columns for 'NAME' and 'AGE' */
    public static void addStudent(String name, int age) throws Exception {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")";
        URL url = new URL("http://localhost:8080/sql");
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setRequestMethod("POST");
        request.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
        request.setDoOutput(true);

        try (OutputStream os = request.getOutputStream()) {
            byte[] input = sql.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = request.getResponseCode();
        // handle response as needed
    }
}