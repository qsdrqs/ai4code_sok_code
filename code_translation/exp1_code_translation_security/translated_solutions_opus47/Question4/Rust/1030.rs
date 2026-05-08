// async issues
use rusqlite::{params, Connection};

fn main() {
    // let db = Connection::open("./temp.db").unwrap();
    create_database();
    /*
    create_tables(&db);
    std::thread::sleep(std::time::Duration::from_millis(500));
    insert(&db, "John Doe", 30);
    std::thread::sleep(std::time::Duration::from_millis(1000));
    let mut stmt = db.prepare("select * FROM students").unwrap();
    let rows = stmt
        .query_map([], |row| {
            Ok((row.get::<_, String>(0)?, row.get::<_, i32>(1)?))
        })
        .unwrap();
    for row in rows {
        let (name, age) = row.unwrap();
        println!("{} {}", name, age);
    }
    */
}

fn create_database() {
    println!("created database");
    let newdb = match Connection::open("temp.db") {
        Ok(conn) => conn,
        Err(err) => {
            println!("Getting error {}", err);
            std::process::exit(1);
        }
    };
    create_tables2(&newdb);
}

#[allow(dead_code)]
fn create_tables(db: &Connection) {
    println!("pre-created tables");
    db.execute("CREATE TABLE students(name TEXT,age INT)", [])
        .unwrap();
}

fn create_tables2(db: &Connection) {
    println!("pre-created tables");
    match db.execute("CREATE TABLE students(name TEXT,age INT)", []) {
        Ok(_) => {
            insert(db, "John Doe", 30);
        }
        Err(err) => {
            println!("{}", err);
        }
    }
}

fn insert(db: &Connection, name: &str, age: i32) {
    let sql = "insert into students(name, age) VALUES (?,?)";
    match db.execute(sql, params![name, age]) {
        Ok(_) => {
            println!("row added successfully");
        }
        Err(err) => {
            println!("{}", err);
        }
    }
}