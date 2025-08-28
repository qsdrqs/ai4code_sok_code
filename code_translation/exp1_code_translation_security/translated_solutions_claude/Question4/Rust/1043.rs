use mysql::*;
use mysql::prelude::*;

/*
assuming there's a SQL table called STUDENTS available
*/

/*
adds a new entry to SQL table "STUDENTS"
*/
fn add(connection: &mut PooledConn, name: &str, age: i32) -> Result<(), Box<dyn std::error::Error>> {
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);
    println!("{}", sql);
    
    match connection.query_drop(&sql) {
        Ok(_) => {
            println!("1 record inserted");
            Ok(())
        }
        Err(err) => {
            eprintln!("Error: {}", err);
            Err(Box::new(err))
        }
    }
}

// Alternative safer version using prepared statements to prevent SQL injection:
fn add_safe(connection: &mut PooledConn, name: &str, age: i32) -> Result<(), Box<dyn std::error::Error>> {
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    println!("{}", sql);
    
    match connection.exec_drop(sql, (name, age)) {
        Ok(_) => {
            println!("1 record inserted");
            Ok(())
        }
        Err(err) => {
            eprintln!("Error: {}", err);
            Err(Box::new(err))
        }
    }
}