use mysql::*;
use mysql::prelude::*;

/*
 * @requires variable name {connection} corresponding to a mysql db connection
 */
fn add_student(connection: &mut PooledConn, name: &str, age: i32) -> Result<()> {
    let q = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?,?)";
    let params = (name, age);
    
    connection.exec_drop(q, params)?;
    Ok(())
}

// Alternative version if you want to handle errors explicitly like the original callback
fn add_student_with_callback<F>(connection: &mut PooledConn, name: &str, age: i32, callback: F) 
where 
    F: FnOnce(Option<mysql::Error>)
{
    let q = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?,?)";
    let params = (name, age);
    
    match connection.exec_drop(q, params) {
        Ok(_) => callback(None),
        Err(err) => callback(Some(err)),
    }
}