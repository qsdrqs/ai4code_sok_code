import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SqlInsertExample {
    
    public static CompletableFuture<String> sqlInsert(String name, int age) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // URL encode the parameters to handle special characters
                String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);
                String encodedAge = URLEncoder.encode(String.valueOf(age), StandardCharsets.UTF_8);
                
                // Build the URL
                String url = "sqlapi.php?action=INSERTINTOSTUDENTS&&name=" + encodedName + "&&age=" + encodedAge;
                
                // Create HTTP client and request
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
                
                // Send request and get response
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                
                // Check if request was successful (status code 200)
                if (response.statusCode() == 200) {
                    return response.body();
                } else {
                    throw new RuntimeException("HTTP request failed with status code: " + response.statusCode());
                }
                
            } catch (Exception e) {
                throw new RuntimeException("Error during HTTP request", e);
            }
        });
    }
    
    public static void main(String[] args) {
        sqlInsert("XXXXX", 0)
            .thenAccept(result -> {
                System.out.println("result: " + result);
            })
            .exceptionally(error -> {
                System.err.println("error: " + error.getMessage());
                error.printStackTrace();
                return null;
            });
        
        // Keep the main thread alive to see the async result
        try {
            Thread.sleep(5000); // Wait 5 seconds for the async operation to complete
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}