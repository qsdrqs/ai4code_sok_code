use mysql::*;
use mysql::prelude::*;

// Assuming `connection` is a valid MySQL connection pool or connection object
fn add_student(connection: &mut PooledConn, name: &str, age: u32) -> Result<()> {
    let query = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)";
    connection.exec_drop(query, (name, age))?;
    Ok(())
}