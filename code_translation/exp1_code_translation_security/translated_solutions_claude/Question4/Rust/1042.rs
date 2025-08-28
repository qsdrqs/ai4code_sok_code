use mysql::*;
use mysql::prelude::*;

fn add_student(connection: &mut PooledConn, name: &str, age: i32) -> Result<(), Box<dyn std::error::Error>> {
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    
    connection.exec_drop(sql, (name, age))?;
    println!("1 record inserted");
    
    Ok(())
}

// Alternative version with error handling similar to the original JavaScript
fn add_student_with_panic(connection: &mut PooledConn, name: &str, age: i32) {
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    
    match connection.exec_drop(sql, (name, age)) {
        Ok(_) => println!("1 record inserted"),
        Err(err) => panic!("{}", err), // Similar to "throw err" in JavaScript
    }
}

// Usage example:
fn main() -> Result<(), Box<dyn std::error::Error>> {
    let url = "mysql://username:password@localhost:3306/database_name";
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;
    
    add_student(&mut conn, "John Doe", 20)?;
    
    Ok(())
}