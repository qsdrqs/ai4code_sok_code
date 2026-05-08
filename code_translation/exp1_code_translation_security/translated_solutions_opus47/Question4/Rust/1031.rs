use mysql::prelude::*;
use mysql::*;

fn insert(name: &str, age: i32) -> Result<()> {
    let url = "mysql://yourusername:yourpassword@localhost/STUDENTS";

    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;

    println!("Connected!");

    let sql = "INSERT INTO students (name, age) VALUES (?, ?)";
    conn.exec_drop(sql, (name, age))?;

    println!("1 record inserted");

    Ok(())
}

fn main() -> Result<()> {
    insert("Alice", 21)?;
    Ok(())
}