import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class Main {

    public static CompletableFuture<String> sqlInsert(String name, int age) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost/sqlapi.php?action=INSERTINTOSTUDENTS&&name=" + name + "&&age=" + age))
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    if (response.statusCode() == 200) {
                        return response.body();
                    }
                    throw new RuntimeException("HTTP error: " + response.statusCode());
                });
    }

    public static void main(String[] args) {
        sqlInsert("XXXXX", 0)
                .thenAccept(result -> System.out.println("result " + result))
                .exceptionally(error -> {
                    System.err.println("error " + error);
                    return null;
                })
                .join(); // wait for the async task to finish before the program exits
    }
}