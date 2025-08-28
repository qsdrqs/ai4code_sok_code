import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * A utility class for inserting a new student into the STUDENTS table.
 * (Assume a DataSource bean or object will be supplied at runtime.)
 */
public final class StudentRepository {

    private final DataSource dataSource;

    public StudentRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Inserts a single row into the STUDENTS table.
     *
     * @param name the student's name (non-null)
     * @param age  the student's age
     * @throws SQLException if the insert fails
     */
    public void insertStudent(String name, int age) throws SQLException {
        final String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setInt(2, age);

            stmt.executeUpdate();
        }
    }

    /* quick usage example */
    public static void main(String[] args) {
        try {
            // Assume MyDataSource is supplied elsewhere with proper JDBC settings
            DataSource ds = MyDataSource.getInstance();
            StudentRepository repo = new StudentRepository(ds);

            repo.insertStudent("XXXXX", 0);
            System.out.println("Insert successful.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}