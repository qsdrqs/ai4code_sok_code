import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static Connection db;

    public static void student_write(String name, int age) {
        try {
            PreparedStatement pstmt = db.prepareStatement("INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)");
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            db = DriverManager.getConnection("jdbc:sqlite:/home/codex/Documents/User_Study_UI/database.db");

            student_write("XXXXX", 0);

            Statement stmt = db.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENTS");
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                StringBuilder row = new StringBuilder("{ ");
                for (int i = 1; i <= columnCount; i++) {
                    if (i > 1) row.append(", ");
                    row.append(metaData.getColumnName(i)).append(": ").append(rs.getObject(i));
                }
                row.append(" }");
                String rowStr = row.toString();
                System.out.println(rowStr + " " + rowStr);  // and other columns, if desired
            }

            rs.close();
            stmt.close();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}