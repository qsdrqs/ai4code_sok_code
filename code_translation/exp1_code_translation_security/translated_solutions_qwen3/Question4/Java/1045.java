import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class SqlInsertExample {

    public static void main(String[] args) {
        sqlInsert("XXXXX", 0)
            .thenAccept(result -> System.out.println("result: " + result))
            .exceptionally(ex -> {
                System.err.println("error: " + ex);
                return null;
            });
    }

    public static CompletableFuture<String> sqlInsert(String name, int age) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Encode the name parameter to handle special characters
                String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);

                // Construct the URL with encoded parameters
                String url = "sqlapi.php?action=INSERTINTOSTUDENTS&&name=" + encodedName + "&&age=" + age;

                // Open the HTTP connection
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                // Get the HTTP response code
                int responseCode = conn.getResponseCode();

                if (responseCode == 200) {
                    // Read and return the response body
                    return readStream(conn.getInputStream());
                } else {
                    // Throw an exception for non-200 responses
                    throw new IOException("HTTP response code: " + responseCode);
                }
            } catch (Exception e) {
                // Wrap any exception in a CompletionException
                throw new CompletionException(e);
            }
        });
    }

    // Helper method to read InputStream into a String
    private static String readStream(InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }
}