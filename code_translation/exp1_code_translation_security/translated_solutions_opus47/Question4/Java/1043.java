import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

/*
assuming there's a SQL table called STUDENTS available
*/

public class Students {
    private Connection connection;
    
    public Students(Connection connection) {
        this.connection = connection;
    }
    
    /*
    adds a new entry to SQL table "STUDENTS"
    */
    public void add(String name, int age) throws SQLException {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")";
        System.out.println(sql);
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("1 record inserted");
        }
    }
}