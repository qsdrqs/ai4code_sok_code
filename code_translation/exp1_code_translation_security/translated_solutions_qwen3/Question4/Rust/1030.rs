use rusqlite::{Connection, Result};

fn main() {
    create_database();
}

fn create_database() {
    println!("created database");
    let db = Connection::open("temp.db").expect("Getting error");
    create_tables2(&db);
}

fn create_tables2(db: &Connection) {
    println!("pre-created tables");
    db.execute("CREATE TABLE students(name TEXT, age INT)", [])
        .expect("Error creating tables");
    insert(db, "John Doe", 30);
}

fn insert(db: &Connection, name: &str, age: i32) {
    match db.execute(
        "INSERT INTO students(name, age) VALUES (?1, ?2)",
        (name, age),
    ) {
        Ok(_) => println!("row added successfully"),
        Err(e) => println!("error: {}", e),
    }
}