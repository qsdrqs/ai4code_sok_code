use rusqlite::{params, Connection};

// db: rusqlite::Connection, assumed to contain a STUDENTS (NAME, AGE) table
// name: student name string
// age: student age integer
//
// This function doesn't do any error checking on the types or sizes of `name`
// and `age`, and neither does Sqlite, so if you care, don't pass things that
// you don't want in your database.
pub fn add_student(db: &Connection, name: &str, age: i32) {
    let result = db.execute(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)",
        params![name, age],
    );
    match result {
        Ok(_) => println!("inserted null"),
        Err(err) => println!("inserted {}", err),
    }
}