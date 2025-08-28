use mysql::*;
use mysql::prelude::*;

fn add_student(name: &str, age: u32) -> Result<()> {
    // Create a connection to the database
    let url = "mysql://username:password@localhost:3306/database_name"; // Replace with your actual database credentials
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;

    // Insert the student into the database
    conn.exec_drop(
        "INSERT INTO STUDENTS (name, age) VALUES (:name, :age)",
        params! {
            "name" => name,
            "age" => age,
        },
    )?;

    Ok(())
}

fn main() {
    // Example usage
    if let Err(err) = add_student("John Doe", 20) {
        eprintln!("Error: {}", err);
    }
}