use mysql::*;
use mysql::prelude::*;

/*
Rust function add_student

Given a String name and int age, add entry to table STUDENTS
*/
fn add_student(name: &str, age: i32) -> String {
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);
    println!("{}", sql);
    sql
}

fn main() -> Result<()> {
    let opts = OptsBuilder::new()
        .ip_or_hostname(Some("localhost"))
        .user(Some("yourusername"))
        .pass(Some("yourpassword"));

    let pool = Pool::new(opts)?;
    let mut con = pool.get_conn()?;
    println!("Connected!");

    let sql = add_student("XXXXX", 0);
    con.query_drop(&sql)?;
    println!("Result: {} rows affected", con.affected_rows());

    Ok(())
}