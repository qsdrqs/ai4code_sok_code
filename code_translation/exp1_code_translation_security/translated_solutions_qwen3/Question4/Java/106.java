public class StudentManager {

    /**
     * Adds a new entry to the STUDENTS table.
     *
     * @param name the name of the student
     * @param age the age of the student
     * @param db the database connection object
     */
    public static void addEntry(String name, int age, Database db) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")";
        db.run(sql);
    }
}