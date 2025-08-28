use rusqlite::{Connection, params};

fn main() {
    // Establish a connection to an in-memory database
    let conn = Connection::open_in_memory().unwrap();

    // Create a table
    conn.execute(
        "CREATE TABLE students (name TEXT, age INT)",
        params![],
    ).unwrap();

    // Insert a student into the table
    insert_student(&conn, "XXXXX", 0);

    // Retrieve and print all students
    let mut stmt = conn.prepare("SELECT * FROM students").unwrap();
    let mut rows = stmt.query(params![]).unwrap();
    while let Some(row) = rows.next().unwrap() {
        println!("name: {}, age: {}", row.get_ref(0).unwrap(), row.get_ref(1).unwrap());
    }

    // Close the database connection
    drop(conn);
}

fn insert_student(conn: &Connection, name: &str, age: i32) {
    conn.execute(
        "INSERT INTO students (name, age) VALUES (?1, ?2)",
        params![name, age],
    ).unwrap();
}