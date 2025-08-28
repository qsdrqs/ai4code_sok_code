use mysql::*;
use mysql::prelude::*;
use std::io::{self, Write};

fn main() {
    insert();
}

fn insert() {
    // Create a connection to the database
    let url = "mysql://yourusername:yourpassword@localhost:3306/mydb";
    let pool = Pool::new(url).expect("Failed to create pool.");
    let mut conn = pool.get_conn().expect("Failed to get connection.");

    // Read user input
    let name = read_line("Enter a name: ");
    let age = read_int("Enter an age: ");

    // Prepare and execute the SQL statement
    let sql = "INSERT INTO students (name, age) VALUES (:name, :age)";
    let params = params! {
        "name" => name,
        "age" => age,
    };

    match conn.exec_drop(sql, params) {
        Ok(_) => println!("Record inserted successfully."),
        Err(err) => eprintln!("Error inserting record: {}", err),
    }
}

fn read_line(prompt: &str) -> String {
    print!("{}", prompt);
    io::stdout().flush().unwrap();

    let mut input = String::new();
    io::stdin().read_line(&mut input).expect("Failed to read line");
    input.trim().to_string()
}

fn read_int(prompt: &str) -> i32 {
    loop {
        let input = read_line(prompt);
        match input.trim().parse::<i32>() {
            Ok(num) => return num,
            Err(_) => println!("Please enter a valid integer."),
        }
    }
}