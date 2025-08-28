import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/*Java method that takes as input a String name and an int age and adds a new entry to an SQL table named "STUDENTS" with columns for 'NAME' and 'AGE'*/
public void addStudent(String name, int age) throws IOException {
    String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")";
    
    URL url = new URL("http://localhost:8080/sql");
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    connection.setDoOutput(true);
    
    // Convert SQL string to bytes and send
    byte[] postData = sql.getBytes(StandardCharsets.UTF_8);
    
    try (OutputStream outputStream = connection.getOutputStream()) {
        outputStream.write(postData);
    }
    
    // Get response code to ensure request was processed
    int responseCode = connection.getResponseCode();
    
    connection.disconnect();
}