use mysql::*;
use mysql::prelude::*;

fn add_student(connection: &mut PooledConn, name: &str, age: u32) {
    let sql = format!(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})",
        name, age
    );
    match connection.query_drop(sql) {
        Err(err) => panic!("{}", err),
        Ok(_) => println!("1 record inserted"),
    }
}