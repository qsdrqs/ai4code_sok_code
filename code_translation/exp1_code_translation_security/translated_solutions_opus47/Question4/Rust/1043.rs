use mysql::*;
use mysql::prelude::*;

/*
assuming there's a SQL table called STUDENTS available
*/

/*
adds a new entry to SQL table "STUDENTS"
*/
fn add(connection: &mut PooledConn, name: &str, age: i32) {
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);
    println!("{}", sql);
    match connection.query_drop(&sql) {
        Ok(_) => println!("1 record inserted"),
        Err(err) => panic!("{}", err),
    }
}