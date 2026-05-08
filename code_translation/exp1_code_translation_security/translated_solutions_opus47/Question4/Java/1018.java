import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TodoService {

    public static void add(String name, int age, String table) {
        // Load config (equivalent to require('./config.js'))
        Properties config = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            config.load(input);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }

        String url = "jdbc:mysql://" + config.getProperty("host") + ":"
                   + config.getProperty("port") + "/" + config.getProperty("database");
        String user = config.getProperty("user");
        String password = config.getProperty("password");

        String stmt = "INSERT INTO todos(title, completed) VALUES (?, ?)";

        // try-with-resources handles connection closing automatically
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(stmt)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);

            // Execute the insert and get affected rows
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("Row inserted:" + affectedRows);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}