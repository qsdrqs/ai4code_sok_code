use rusqlite::{params, Connection, Result};
use std::sync::Arc;
use tokio::sync::Mutex;
use tokio::time::{sleep, Duration};

#[tokio::main]
async fn main() {
    // Simulate the IIFE (Immediately Invoked Function Expression) in JavaScript
    let db = create_database().await;

    // Uncomment the following lines to mimic the commented-out JavaScript code
    /*
    create_tables(&db).await;
    tokio::spawn(async move {
        sleep(Duration::from_millis(500)).await;
        insert(&db, "John Doe", 30).await;
    });

    tokio::spawn(async move {
        sleep(Duration::from_millis(1000)).await;
        let rows = fetch_all_students(&db).await;
        for row in rows {
            println!("{} {}", row.0, row.1);
        }
    })
    .await
    .unwrap();
    */
}

async fn create_database() -> Arc<Mutex<Connection>> {
    println!("created database");

    let db = Connection::open("temp.db").expect("Failed to create database");
    let db = Arc::new(Mutex::new(db));

    create_tables2(db.clone()).await;

    db
}

async fn create_tables(db: Arc<Mutex<Connection>>) {
    println!("pre-created tables");

    let db = db.lock().await;
    db.execute("CREATE TABLE IF NOT EXISTS students (name TEXT, age INT)", [])
        .expect("Failed to create table");
}

async fn create_tables2(db: Arc<Mutex<Connection>>) {
    println!("pre-created tables");

    let db = db.lock().await;
    db.execute("CREATE TABLE IF NOT EXISTS students (name TEXT, age INT)", [])
        .expect("Failed to create table");

    insert(db.clone(), "John Doe", 30).await;
}

async fn insert(db: Arc<Mutex<Connection>>, name: &str, age: i32) {
    let db = db.lock().await;

    let sql = "INSERT INTO students (name, age) VALUES (?, ?)";
    match db.execute(sql, params![name, age]) {
        Ok(_) => println!("row added successfully"),
        Err(err) => println!("Error inserting row: {}", err),
    }
}

async fn fetch_all_students(db: Arc<Mutex<Connection>>) -> Vec<(String, i32)> {
    let db = db.lock().await;

    let mut stmt = db
        .prepare("SELECT name, age FROM students")
        .expect("Failed to prepare statement");

    let rows = stmt
        .query_map([], |row| {
            let name: String = row.get(0)?;
            let age: i32 = row.get(1)?;
            Ok((name, age))
        })
        .expect("Failed to query rows");

    rows.map(|row| row.expect("Failed to map row")).collect()
}