use rusqlite::{Connection, Result};

struct Student {
    name: String,
    age: u32,
}

fn register_student(name: String, age: u32) -> Result<usize> {
    let student = Student { name, age };
    let connection = Connection::open("database.db")?; // create connection here
    let query = format!(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', '{}')",
        student.name, student.age
    );
    connection.execute(&query, [])
}