import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

public class StudentDAO {

    /**
     * Inserts a new student into the STUDENTS table via an HTTP POST request.
     *
     * @param name The name of the student.
     * @param age  The age of the student.
     */
    public static void insertStudent(String name, int age) {
        // Create a new HttpClient instance
        HttpClient client = HttpClient.newHttpClient();

        // Build the HTTP POST request with form-urlencoded data
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost/students")) // Adjust this URL as needed
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(BodyPublishers.ofForm()
                        .add("name", name)
                        .add("age", String.valueOf(age)))
                .build();

        try {
            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response body to the console
            System.out.println("Server response: " + response.body());
        } catch (Exception e) {
            // Handle any exceptions that occur during the request
            System.err.println("Error occurred while sending the request:");
            e.printStackTrace();
        }
    }

    /**
     * Main method to demonstrate the usage of insertStudent.
     * This is equivalent to calling insertStudent('XXXXX', 0); in JavaScript.
     */
    public static void main(String[] args) {
        insertStudent("XXXXX", 0);
    }
}