use mysql::*;
use mysql::prelude::*;

// Assuming connection details are provided elsewhere
fn create_connection() -> Result<PooledConn, mysql::Error> {
    let url = "mysql://username:password@localhost:3306/database_name";
    let pool = Pool::new(url)?;
    pool.get_conn()
}

fn add_student(name: &str, age: i32) -> Result<(), mysql::Error> {
    let mut conn = create_connection()?;
    
    // Using parameterized query to prevent SQL injection
    conn.exec_drop(
        "INSERT INTO STUDENTS VALUES (?, ?)",
        (name, age)
    )?;
    
    Ok(())
}

// Alternative version that matches the original structure more closely
fn add_student_callback_style(name: &str, age: i32) {
    match create_connection() {
        Ok(mut conn) => {
            match conn.exec_drop("INSERT INTO STUDENTS VALUES (?, ?)", (name, age)) {
                Ok(_) => {
                    // Success - equivalent to the empty callback (x,y,z)=>{}
                }
                Err(e) => {
                    panic!("Database error: {}", e); // Equivalent to throw err
                }
            }
        }
        Err(e) => {
            panic!("Connection error: {}", e); // Equivalent to throw err
        }
    }
}