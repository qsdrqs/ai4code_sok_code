import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StudentService {
    
    public static void insertStudent(String name, int age) {
        try {
            // Create URL object
            URL url = new URL("http://localhost:8080/students"); // Assuming localhost, adjust as needed
            
            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Set request method to POST
            connection.setRequestMethod("POST");
            
            // Set request headers
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);
            
            // Prepare POST data
            String postData = "name=" + name + "&age=" + age;
            byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);
            
            // Write POST data
            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(postDataBytes);
            }
            
            // Get response code
            int responseCode = connection.getResponseCode();
            
            // Read response
            BufferedReader reader;
            if (responseCode >= 200 && responseCode < 300) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            
            // Print response (equivalent to console.log)
            System.out.println(response.toString());
            
            connection.disconnect();
            
        } catch (IOException e) {
            System.err.println("Error occurred while inserting student: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        insertStudent("XXXXX", 0);
    }
}