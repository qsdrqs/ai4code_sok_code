import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class SqlApiHandler {

    // Create a single, reusable HttpClient instance.
    private static final HttpClient client = HttpClient.newHttpClient();

    /**
     * Performs an asynchronous GET request to insert a student record.
     *
     * @param name The name of the student.
     * @param age The age of the student.
     * @return A CompletableFuture that will complete with the response body as a String.
     */
    public static CompletableFuture<String> sqlInsert(String name, int age) {
        // Note: The original JS used a relative URL "sqlapi.php".
        // Java's HttpClient requires a full, absolute URI.
        // Replace "http://your-server.com" with your actual domain.
        final String BASE_URL = "http://your-server.com/sqlapi.php";

        try {
            // Properly encode URL parameters to handle special characters in the name.
            String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);

            // Build the full URL with query parameters.
            // Note: The standard separator is '&', not '&&' as in the original JS.
            // Most servers will handle '&&', but '&' is more correct.
            String url = String.format("%s?action=INSERTINTOSTUDENTS&name=%s&age=%d",
                BASE_URL, encodedName, age);

            // Create an HTTP request object.
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET() // This is the default method, but it's good to be explicit.
                .build();

            // Send the request asynchronously.
            // HttpResponse.BodyHandlers.ofString() processes the response body as a String.
            // The sendAsync method itself returns a CompletableFuture<HttpResponse<String>>.
            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    // This block is similar to the 'onreadystatechange' logic.
                    // We check the status code to determine success.
                    if (response.statusCode() == 200) {
                        // If successful, we return the body, fulfilling the promise with the result.
                        return response.body();
                    } else {
                        // If not successful, we throw an exception to trigger the .exceptionally() block.
                        // This is equivalent to a Promise rejection.
                        throw new CompletionException(
                            new RuntimeException("Request failed with status code: " + response.statusCode())
                        );
                    }
                });

        } catch (Exception e) {
            // If an error occurs during request setup (e.g., bad URL),
            // return a future that is already completed exceptionally.
            return CompletableFuture.failedFuture(e);
        }
    }

    public static void main(String[] args) {
        // This is the equivalent of the JavaScript call chain.
        System.out.println("Sending request...");

        CompletableFuture<Void> future = sqlInsert("XXXXX", 0)
            .thenAccept(result -> {
                // This is the .then() block for a successful operation.
                System.out.println("Result: " + result);
            })
            .exceptionally(error -> {
                // This is the .catch() block for a failed operation.
                // The actual exception is wrapped in a CompletionException.
                System.err.println("Error: " + error.getCause().getMessage());
                return null; // Required return for the exceptionally block.
            });

        // In a simple main method, the program would exit before the async task completes.
        // We call .join() to block the main thread and wait for the future to finish.
        // In a real application (like a web server or GUI), you wouldn't block like this.
        future.join();

        System.out.println("Request finished.");
    }
}