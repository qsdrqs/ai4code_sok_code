import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        // async issues
        // Connection db = DriverManager.getConnection("jdbc:sqlite:./temp.db");
        createDatabase();
        /*
        createTables(db);
        try {
            Thread.sleep(500);
            insert(db, "John Doe", 30);
        } catch (InterruptedException e) { }
        try {
            Thread.sleep(500);
            Statement stmt = db.createStatement();
            ResultSet rows = stmt.executeQuery("select * FROM students");
            while (rows.next()) {
                System.out.println(rows.getString("name") + " " + rows.getInt("age"));
            }
        } catch (Exception e) { }
        */
    }

    public static void createDatabase() {
        System.out.println("created database");
        try {
            // In Java, the SQLite JDBC driver (org.xerial:sqlite-jdbc) is loaded via DriverManager
            Connection newdb = DriverManager.getConnection("jdbc:sqlite:temp.db");
            createTables2(newdb);
        } catch (SQLException err) {
            System.out.println("Getting error " + err);
            System.exit(1);
        }
    }

    public static void createTables(Connection db) {
        System.out.println("pre-created tables");
        try {
            Statement stmt = db.createStatement();
            stmt.execute("CREATE TABLE students(name TEXT,age INT)");
        } catch (SQLException err) {
            // ignore for demonstration
        }
    }

    public static void createTables2(Connection db) {
        System.out.println("pre-created tables");
        try {
            Statement stmt = db.createStatement();
            stmt.execute("CREATE TABLE students(name TEXT,age INT)");
        } catch (SQLException err) {
            // continue even if table already exists, mimicking JS callback behavior
        }
        insert(db, "John Doe", 30);
    }

    public static void insert(Connection db, String name, int age) {
        String sql = "insert into students(name, age) VALUES (?,?)";
        try {
            PreparedStatement pstmt = db.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
            System.out.println("row added successfully");
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }
}