import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class StudentService {

    public static void insertStudent(String name, int age) {
        try {
            // Create the JSON payload
            String jsonPayload = String.format("{\"name\":\"%s\", \"age\":%d}", name, age);

            // Create the HTTP client
            HttpClient client = HttpClient.newHttpClient();

            // Create the HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/students")) // Replace with your actual server URL
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .build();

            // Send the HTTP request and get the response
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            // Print the response
            System.out.println(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage
        insertStudent("XXXXX", 0);
    }
}