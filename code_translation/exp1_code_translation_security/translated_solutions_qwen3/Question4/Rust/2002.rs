use mysql::{Pool, PooledConn};
use mysql::prelude::*;

fn add_student(name: &str, age: i32, pool: &Pool) {
    let mut conn = pool.get_conn().expect("Failed to get connection from pool");
    let query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    let params = (name, age);
    conn.execute(query, params).ok();
}