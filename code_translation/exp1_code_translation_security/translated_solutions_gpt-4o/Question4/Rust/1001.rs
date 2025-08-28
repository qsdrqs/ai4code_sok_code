use rusqlite::{params, Connection, Result};

fn add_sql_entry(conn: &Connection, name: &str, age: i32) -> Result<()> {
    let sql = "INSERT INTO students (name, age) VALUES (?, ?)";
    match conn.execute(sql, params![name, age]) {
        Ok(_) => {
            let last_id = conn.last_insert_rowid();
            println!("User added with id: {}", last_id);
        }
        Err(err) => {
            eprintln!("Error: {}", err);
        }
    }
    Ok(())
}