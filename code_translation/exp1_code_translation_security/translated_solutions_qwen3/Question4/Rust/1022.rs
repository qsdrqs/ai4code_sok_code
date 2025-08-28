use rusqlite::{Connection, Result};

fn student_write(conn: &Connection, name: &str, age: i32) {
    conn.execute(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)",
        &[name, &age],
    )
    .expect("Failed to insert student");
}

fn main() {
    // Open the SQLite database
    let conn = Connection::open("/home/codex/Documents/User_Study_UI/database.db")
        .expect("Failed to open database");

    // Insert a new student
    student_write(&conn, "XXXXX", 0);

    // Query all students
    let mut stmt = conn
        .prepare("SELECT * FROM STUDENTS")
        .expect("Failed to prepare statement");

    let rows = stmt
        .query_map([], |row| {
            Ok((
                row.get::<_, String>("NAME").expect("Failed to get NAME"),
                row.get::<_, i32>("AGE").expect("Failed to get AGE"),
            ))
        })
        .expect("Failed to execute query");

    // Print each row
    for row in rows {
        let (name, age) = row.expect("Error in row");
        println!("name: {}, age: {}", name, age);
    }
}