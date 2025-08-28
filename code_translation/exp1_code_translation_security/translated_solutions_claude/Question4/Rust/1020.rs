use mysql::*;
use mysql::prelude::*;

fn inserter(name: &str, age: i32) -> Result<(), Box<dyn std::error::Error>> {
    let url = "mysql://yourusername:yourpassword@localhost:3306/mydb";
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;
    
    println!("Connected!");
    
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    conn.exec_drop(sql, (name, age))?;
    
    println!("1 record inserted");
    
    Ok(())
}

// Example usage:
fn main() -> Result<(), Box<dyn std::error::Error>> {
    inserter("John Doe", 25)?;
    Ok(())
}