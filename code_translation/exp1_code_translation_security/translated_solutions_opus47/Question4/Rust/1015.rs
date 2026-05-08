use mysql::*;
use mysql::prelude::*;

fn add_student(name: &str, age: i32) -> Result<()> {
    let url = "mysql://yourusername:yourpassword@localhost/mydb";

    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;

    println!("Connected!");

    conn.exec_drop(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (:name, :age)",
        params! {
            "name" => name,
            "age" => age,
        },
    )?;

    println!("1 record inserted");

    Ok(())
}