use mysql::prelude::*;
use mysql::*;

fn inserter(name: &str, age: i32) -> Result<()> {
    let url = "mysql://yourusername:yourpassword@localhost/mydb";
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;

    println!("Connected!");

    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (:name, :age)";
    conn.exec_drop(
        sql,
        params! {
            "name" => name,
            "age" => age,
        },
    )?;

    println!("1 record inserted");
    Ok(())
}

fn main() {
    if let Err(e) = inserter("Alice", 20) {
        panic!("Error: {}", e);
    }
}