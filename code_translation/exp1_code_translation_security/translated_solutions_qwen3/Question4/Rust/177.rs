extern crate odbc;

use odbc::{Environment};

pub fn add_student(name: &str, age: i32) {
    let sql = format!("INSERT INTO STUDENTS VALUES ('{}', '{}');", name, age);
    let env = Environment::new().unwrap();
    let conn = env.connect("", "", "").unwrap();
    let mut stmt = conn.create_statement().unwrap();
    stmt.exec_direct(&sql).unwrap();
}