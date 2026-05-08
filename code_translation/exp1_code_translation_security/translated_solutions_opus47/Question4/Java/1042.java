public void addStudent(String name, int age) {
    String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setString(1, name);
        pstmt.setInt(2, age);
        pstmt.executeUpdate();
        System.out.println("1 record inserted");
    } catch (SQLException err) {
        throw new RuntimeException(err);
    }
}