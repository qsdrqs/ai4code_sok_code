use mysql::*;
use mysql::prelude::*;

fn insert_student(name: &str, age: i32) -> Result<()> {
    // Configuration (equivalent to the 'config' object in JS)
    let config = OptsBuilder::new()
        .ip_or_hostname(Some("localhost"))
        .user(Some("user"))
        .pass(Some("password"))
        .db_name(Some("database"));
    
    // create connection
    let mut connection = Conn::new(config)?;
    
    // insert statement
    let sql = "INSERT INTO STUDENTS(title, completed)
               VALUES('Learn how to insert a new row', true)";
    
    // execute the insert statement
    connection.query_drop(sql)?;
    
    // connection is closed automatically when `connection` goes out of scope (Drop trait)
    Ok(())
}

fn main() -> Result<()> {
    insert_student("XXXXX", 0)?;
    Ok(())
}