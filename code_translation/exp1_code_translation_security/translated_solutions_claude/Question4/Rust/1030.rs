use rusqlite::{Connection, Result};
use std::process;

fn main() {
    // async issues 
    create_database();
}

fn create_database() {
    println!("created database");
    
    match Connection::open("temp.db") {
        Ok(conn) => {
            create_tables2(conn);
        }
        Err(err) => {
            println!("Getting error {}", err);
            process::exit(1);
        }
    }
}

fn create_tables(conn: Connection) {
    println!("pre-created tables");
    
    if let Err(err) = conn.execute("CREATE TABLE students(name TEXT, age INT)", []) {
        println!("Error creating table: {}", err);
    }
}

fn create_tables2(conn: Connection) {
    println!("pre-created tables");
    
    match conn.execute("CREATE TABLE students(name TEXT, age INT)", []) {
        Ok(_) => {
            insert(conn, "John Doe", 30);
        }
        Err(err) => {
            println!("Error creating table: {}", err);
        }
    }
}

fn insert(conn: Connection, name: &str, age: i32) {
    let sql = "INSERT INTO students(name, age) VALUES (?, ?)";
    
    match conn.execute(sql, [name, &age.to_string()]) {
        Ok(_) => {
            println!("row added successfully");
        }
        Err(err) => {
            println!("{}", err);
        }
    }
}