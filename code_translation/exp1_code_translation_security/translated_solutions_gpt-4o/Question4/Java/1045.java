import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class SqlInsertExample {

    public static CompletableFuture<String> sqlInsert(String name, int age) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Construct the URL with query parameters
                String urlString = "http://yourserver.com/sqlapi.php?action=INSERTINTOSTUDENTS&&name=" + name + "&&age=" + age;
                URL url = new URL(urlString);

                // Open connection
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                // Check the response code
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) { // HTTP OK
                    // Read the response
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    return response.toString();
                } else {
                    throw new RuntimeException("HTTP request failed with response code: " + responseCode);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error during HTTP request: " + e.getMessage(), e);
            }
        });
    }

    public static void main(String[] args) {
        sqlInsert("XXXXX", 0).thenAccept(result -> {
            System.out.println("result: " + result);
        }).exceptionally(error -> {
            System.err.println("error: " + error.getMessage());
            return null;
        });
    }
}