use mysql::prelude::*;
use mysql::*;

fn insertion(name: &str, age: i32) -> Result<()> {
    // Connection URL — adjust credentials/host/db as needed
    let url = "mysql://user:password@localhost:3306/mydatabase";

    // Create a connection pool and get a connection
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;

    // Use a parameterized query to prevent SQL injection
    conn.exec_drop(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)",
        (name, age),
    )?;

    Ok(())
}

fn main() {
    match insertion("Alice", 21) {
        Ok(_)  => println!("Row inserted successfully."),
        Err(e) => eprintln!("Error inserting row: {}", e),
    }
}