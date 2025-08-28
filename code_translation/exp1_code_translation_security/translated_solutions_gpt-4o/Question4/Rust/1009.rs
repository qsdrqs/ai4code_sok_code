use mysql::*;
use mysql::prelude::*;

fn create_entry(name: &str, age: u32) -> Result<()> {
    // Create a connection to the database
    let url = "mysql://username:password@localhost:3306/database_name";
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;

    // Execute the query
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
    match create_entry("John Doe", 20) {
        Ok(_) => println!("Entry created successfully."),
        Err(err) => eprintln!("Error creating entry: {}", err),
    }
}