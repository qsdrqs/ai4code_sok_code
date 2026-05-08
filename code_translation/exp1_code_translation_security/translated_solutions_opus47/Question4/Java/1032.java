public static void addStudent(Connection conn, String name, int age) throws SQLException {
    String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, name);
        pstmt.setInt(2, age);
        pstmt.executeUpdate();
    }
}