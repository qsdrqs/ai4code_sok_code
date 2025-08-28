use mysql::*;
use mysql::prelude::*;

fn add(name: &str, age: u32) -> Result<()> {
    // Create a connection to the MySQL database
    let url = "mysql://username:password@localhost:3306/database_name";
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;

    // Prepare and execute the query
    let query = "INSERT INTO students (name, age) VALUES (:name, :age)";
    conn.exec_drop(query, params! {
        "name" => name,
        "age" => age,
    })?;

    println!("Added {} to students table", name);
    Ok(())
}

fn main() {
    match add("John Doe", 25) {
        Ok(_) => println!("Student added successfully."),
        Err(err) => eprintln!("Error adding student: {}", err),
    }
}