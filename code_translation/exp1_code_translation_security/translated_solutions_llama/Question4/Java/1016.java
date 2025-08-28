import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    /**
     * Adds a new student to the STUDENTS table in the SQL database.
     * 
     * @param name The name of the student.
     * @param age  The age of the student.
     * @throws Exception If an error occurs during the HTTP request.
     */
    public static void addStudent(String name, int age) throws Exception {
        // Construct the SQL query
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")";

        // Construct the URL and parameters
        URL url = new URL("http://localhost:8080/sql");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        // Send the request
        String parameters = "sql=" + sql;
        connection.setDoOutput(true);
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeBytes(parameters);
        outputStream.flush();
        outputStream.close();

        // Check the response
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            throw new Exception("Failed to add student. Response code: " + responseCode + ". Response: " + response.toString());
        }
    }

    public static void main(String[] args) throws Exception {
        addStudent("John Doe", 20);
    }
}