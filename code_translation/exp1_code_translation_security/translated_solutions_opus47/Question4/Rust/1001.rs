use rusqlite::{params, Connection};

fn add_sql_entry(db: &Connection, name: &str, age: i32) {
    let sql = "INSERT INTO students (name, age) VALUES (?, ?)";
    match db.execute(sql, params![name, age]) {
        Ok(_) => {
            println!("User added with id: {}", db.last_insert_rowid());
        }
        Err(err) => {
            println!("{}", err);
        }
    }
}