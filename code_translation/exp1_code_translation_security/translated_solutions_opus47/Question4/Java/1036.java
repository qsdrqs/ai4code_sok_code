import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void add_new_entry(String name, int age) {
        Connection db = null;
        try {
            db = DriverManager.getConnection("jdbc:sqlite:database.db");

            PreparedStatement stmt = db.prepareStatement("INSERT INTO STUDENTS VALUES (?,?)");
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.executeUpdate();
            stmt.close();

            Statement selectStmt = db.createStatement();
            ResultSet rs = selectStmt.executeQuery("SELECT rowid AS id, NAME, AGE FROM STUDENTS");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ": " + rs.getString("NAME") + " " + rs.getInt("AGE"));
            }
            rs.close();
            selectStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                try {
                    db.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        add_new_entry("XXXXX", 0);
    }
}