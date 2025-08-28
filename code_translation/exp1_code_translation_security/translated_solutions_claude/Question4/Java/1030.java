import java.sql.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class DatabaseExample {
    
    public static void main(String[] args) {
        // async issues 
        // Equivalent of the IIFE (Immediately Invoked Function Expression)
        
        //Connection db = createConnection();
        createDatabase();
        /*
        createTables(db);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.schedule(() -> {
            insert(db, "John Doe", 30);
        }, 500, TimeUnit.MILLISECONDS);
        
        executor.schedule(() -> {
            try {
                Statement stmt = db.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM students");
                while (rs.next()) {
                    System.out.println(rs.getString("name") + " " + rs.getInt("age"));
                }
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, 1000, TimeUnit.MILLISECONDS);
        */
    }
    
    public static void createDatabase() {
        System.out.println("created database");
        
        CompletableFuture.supplyAsync(() -> {
            try {
                // Load SQLite JDBC driver
                Class.forName("org.sqlite.JDBC");
                Connection connection = DriverManager.getConnection("jdbc:sqlite:temp.db");
                return connection;
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Getting error " + e.getMessage());
                System.exit(1);
                return null;
            }
        }).thenAccept(connection -> {
            if (connection != null) {
                createTables2(connection);
            }
        });
    }
    
    public static void createTables(Connection db) {
        System.out.println("pre-created tables");
        try {
            Statement stmt = db.createStatement();
            stmt.execute("CREATE TABLE students(name TEXT, age INT)");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void createTables2(Connection db) {
        System.out.println("pre-created tables");
        
        CompletableFuture.runAsync(() -> {
            try {
                Statement stmt = db.createStatement();
                stmt.execute("CREATE TABLE students(name TEXT, age INT)");
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
            
            // Call insert after table creation is complete
            insert(db, "John Doe", 30);
        });
    }
    
    public static void insert(Connection db, String name, int age) {
        String sql = "INSERT INTO students(name, age) VALUES (?, ?)";
        
        CompletableFuture.runAsync(() -> {
            try {
                PreparedStatement pstmt = db.prepareStatement(sql);
                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                pstmt.executeUpdate();
                pstmt.close();
                System.out.println("row added successfully");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}