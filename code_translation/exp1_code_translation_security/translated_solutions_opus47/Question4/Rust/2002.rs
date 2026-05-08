use mysql::prelude::*;
use mysql::PooledConn;

/// Requires a mysql db connection passed as `connection`
fn add_student(connection: &mut PooledConn, name: &str, age: i32) {
    let q = "INSERT INTO STUDENTS(NAME, AGE)
             VALUES(?,?)";
    let v = (name, age);
    let _ = connection.exec_drop(q, v);
}