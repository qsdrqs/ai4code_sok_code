use rusqlite::Connection;

/// Given an SQL table named "STUDENTS" with columns "NAME" and "AGE", add a new entry
///
/// # Arguments
///
/// * `name` - string for the "NAME" of the entry
/// * `age` - int for the "AGE" of the entry
/// * `db` - a given database connection
fn add_entry(name: &str, age: i32, db: &Connection) {
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);
    db.execute(&sql, []).unwrap();
}