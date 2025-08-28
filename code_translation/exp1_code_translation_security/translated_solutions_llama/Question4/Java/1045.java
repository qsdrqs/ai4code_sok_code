import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class Main {

    public static CompletableFuture<String> sqlInsert(String name, int age) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                URL url = new URL("http://localhost/sqlapi.php?action=INSERTINTOSTUDENTS&&name=" + name + "&&age=" + age);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    return response.toString();
                } else {
                    return "Error: " + responseCode;
                }
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        });
    }

    public static void main(String[] args) {
        sqlInsert("XXXXX", 0).thenAccept(result -> {
            System.out.println("Result: " + result);
        }).exceptionally(ex -> {
            System.err.println("Error: " + ex.getMessage());
            return null;
        });
    }
}