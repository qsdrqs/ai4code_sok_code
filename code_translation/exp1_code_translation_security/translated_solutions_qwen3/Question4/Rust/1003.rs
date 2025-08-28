use rusqlite::Connection;

pub fn add_student(db: &Connection, name: &str, age: i32) {
    let result = db.execute(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)",
        rusqlite::params![name, age],
    );

    match result {
        Ok(_) => println!("inserted null"),
        Err(e) => println!("inserted {:?}", e),
    }
}