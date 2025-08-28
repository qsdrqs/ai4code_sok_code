use rusqlite::{Connection, Result};

// db: Connection object, assumed to contain a STUDENTS (NAME, AGE) table
// name: student name string
// age: student age integer
//
// This function doesn't do any error checking on the types or sizes of `name`
// and `age`, and neither does Sqlite, so if you care, don't pass things that
// you don't want in your database.
pub fn add_student(db: &Connection, name: &str, age: i32) {
    match db.execute(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)",
        &[&name as &dyn rusqlite::ToSql, &age as &dyn rusqlite::ToSql],
    ) {
        Ok(_) => println!("inserted {:?}", None::<rusqlite::Error>),
        Err(err) => println!("inserted {:?}", Some(err)),
    }
}

// In Rust, we don't have module.exports, but we can make the function public
// so it can be used from other modules by using the `pub` keyword above