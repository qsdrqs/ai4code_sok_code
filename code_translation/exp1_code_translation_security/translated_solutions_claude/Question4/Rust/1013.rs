use mysql::*;
use mysql::prelude::*;
use std::io::{self, Write};

struct DatabaseConnection {
    pool: Pool,
}

impl DatabaseConnection {
    fn new() -> Result<Self> {
        let url = "mysql://yourusername:yourpassword@localhost:3306/mydb";
        let pool = Pool::new(url)?;
        Ok(DatabaseConnection { pool })
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
        match input.parse::<i32>() {
            Ok(num) => return num,
            Err(_) => println!("Please enter a valid number."),
        }
    }
}

fn insert() -> Result<()> {
    let db = DatabaseConnection::new()?;
    let mut conn = db.pool.get_conn()?;
    
    println!("Connected!");
    
    let name = read_line("enter a name: ");
    let age = read_int("enter an age: ");
    
    let sql = "INSERT INTO students (name, age) VALUES (?, ?)";
    let result = conn.exec_drop(sql, (name, age))?;
    
    println!("Number of records inserted: 1");
    
    Ok(())
}

fn main() {
    if let Err(e) = insert() {
        eprintln!("Error: {}", e);
        std::process::exit(1);
    }
}