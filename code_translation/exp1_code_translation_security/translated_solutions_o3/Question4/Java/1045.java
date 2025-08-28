import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public class StudentApi {

    /**
     * Asynchronously calls
     *   sqlapi.php?action=INSERTINTOSTUDENTS&&name={name}&&age={age}
     * and completes with the response body (or an exception).
     */
    public static CompletableFuture<String> sqlInsert(String name, int age) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String url =
                        "http://your-host/sqlapi.php?action=INSERTINTOSTUDENTS"
                        + "&&name=" + URLEncoder.encode(name, StandardCharsets.UTF_8)
                        + "&&age="  + age;

                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("GET");

                int status = conn.getResponseCode();
                if (status != HttpURLConnection.HTTP_OK) {
                    throw new RuntimeException("HTTP error code " + status);
                }

                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {

                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    return response.toString();
                }
            } catch (Exception e) {
                throw new RuntimeException("Request failed", e);
            }
        });
    }

    /* ------------ Example Usage ------------ */
    public static void main(String[] args) {
        sqlInsert("XXXXX", 0)
            .thenAccept(result -> System.out.println("result: " + result))
            .exceptionally(err -> {
                System.err.println("error: " + err.getMessage());
                return null;
            })
            .join();        // wait so the program doesn't exit too early
    }
}