use mysql::prelude::*;
use mysql::*;
use std::io::{self, Write};

fn read_line(prompt: &str) -> String {
    print!("{}", prompt);
    io::stdout().flush().unwrap();
    let mut input = String::new();
    io::stdin()
        .read_line(&mut input)
        .expect("Failed to read line");
    input.trim().to_string()
}

fn read_int(prompt: &str) -> i32 {
    let input = read_line(prompt);
    input.parse::<i32>().expect("Please enter a valid integer")
}

fn insert() -> Result<()> {
    let url = "mysql://yourusername:yourpassword@localhost/mydb";
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;
    println!("Connected!");

    let sql = "INSERT INTO students (name, age) VALUES (?, ?)";
    let name = read_line("enter a name: ");
    let age = read_int("enter an age: ");

    conn.exec_drop(sql, (name, age))?;

    println!("Number of records inserted: {}", conn.affected_rows());
    Ok(())
}

fn main() {
    if let Err(e) = insert() {
        eprintln!("Error: {}", e);
    }
}