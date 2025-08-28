import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * A Java class to replicate the functionality of the provided JavaScript snippet.
 */
public class StudentApiClient {

    /**
     * Takes a name and an age and sends them as a new entry to a server-side
     * endpoint that processes raw SQL.
     *
     * @param name The name of the student.
     * @param age The age of the student.
     * @throws IOException if an I/O error occurs when sending or receiving.
     * @throws InterruptedException if the operation is interrupted.
     */
    public void addStudent(String name, int age) throws IOException, InterruptedException {
        // 1. Construct the SQL string by concatenating values.
        // WARNING: This method is highly vulnerable to SQL Injection attacks.
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")";

        // 2. Create a new HttpClient with default settings.
        HttpClient client = HttpClient.newHttpClient();

        // 3. Build the HTTP POST request.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sql")) // Set the target URL
                .header("Content-Type", "application/x-www-form-urlencoded") // Set the header
                .POST(HttpRequest.BodyPublishers.ofString(sql)) // Set the method and body
                .build();

        // 4. Send the request and receive the response.
        // The JavaScript snippet doesn't handle the response, but it's good practice in Java.
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // (Optional) Print the response status and body to verify success.
        System.out.println("Response Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }

    /**
     * Main method to demonstrate how to use the addStudent function.
     */
    public static void main(String[] args) {
        StudentApiClient apiClient = new StudentApiClient();
        try {
            // Example usage:
            System.out.println("Attempting to add student 'Bob Smith' aged 25...");
            apiClient.addStudent("Bob Smith", 25);
            System.out.println("Request sent successfully.");

        } catch (IOException | InterruptedException e) {
            System.err.println("An error occurred while sending the HTTP request:");
            e.printStackTrace();
        }
    }
}