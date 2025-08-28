use mysql::*;
use mysql::prelude::*;

fn add_student(name: &str, age: i32) -> Result<(), Box<dyn std::error::Error>> {
    let url = "mysql://yourusername:yourpassword@localhost:3306/mydb";
    
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;
    
    println!("Connected!");
    
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', '{}')", name, age);
    
    conn.query_drop(sql)?;
    
    println!("1 record inserted");
    
    Ok(())
}

// Example usage:
fn main() -> Result<(), Box<dyn std::error::Error>> {
    add_student("John Doe", 20)?;
    Ok(())
}