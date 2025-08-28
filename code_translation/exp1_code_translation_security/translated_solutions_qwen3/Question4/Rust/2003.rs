use rusqlite::{named_params, Connection};

fn main() {
    // Open an in-memory SQLite database
    let conn = Connection::open_in_memory().unwrap();

    // Create the students table
    conn.execute("CREATE TABLE students (name TEXT, age INT)", [])
        .unwrap();

    // Insert a student into the table
    insert_student(&conn, "XXXXX", 0);

    // Query and print all students
    let mut stmt = conn.prepare("SELECT * FROM students").unwrap();
    let rows = stmt
        .query_map([], |row| {
            let name: String = row.get("name").unwrap();
            let age: i32 = row.get("age").unwrap();
            Ok((name, age))
        })
        .unwrap();

    for row in rows {
        let (name, age) = row.unwrap();
        println!("name: {}, age: {}", name, age);
    }
}

// Function to insert a student into the database
fn insert_student(conn: &Connection, name: &str, age: i32) {
    conn.execute(
        "INSERT INTO students (name, age) VALUES ($name, $age)",
        named_params! {
            "$name": name,
            "$age": age,
        },
    )
    .unwrap();
}