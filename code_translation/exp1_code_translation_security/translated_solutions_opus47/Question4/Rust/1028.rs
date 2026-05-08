use mysql::prelude::*;
use mysql::PooledConn;

fn add_student(conn: &mut PooledConn, name: &str, age: i32) {
    let sql = format!(
        "INSERT INTO STUDENTS (name, age) VALUES ('{}', {})",
        name, age
    );
    match conn.query_drop(&sql) {
        Ok(_) => println!("1 record inserted"),
        Err(err) => panic!("{}", err),
    }
}