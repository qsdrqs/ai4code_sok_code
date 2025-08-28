use rusqlite::{Connection, Result};
use std::rc::Rc;

fn add_sql_entry(db: &Connection, name: &str, age: i32) -> Result<()> {
    let sql = "INSERT INTO students (name, age) VALUES (?, ?)";
    
    match db.execute(sql, &[&name, &age as &dyn rusqlite::ToSql]) {
        Ok(_) => {
            let last_id = db.last_insert_rowid();
            println!("User added with id: {}", last_id);
            Ok(())
        }
        Err(err) => {
            println!("{}", err);
            Err(err)
        }
    }
}

// Alternative version with more explicit error handling
fn add_sql_entry_alt(db: &Connection, name: &str, age: i32) {
    let sql = "INSERT INTO students (name, age) VALUES (?, ?)";
    
    let result = db.execute(sql, &[&name, &age as &dyn rusqlite::ToSql]);
    
    match result {
        Ok(_) => {
            let last_id = db.last_insert_rowid();
            println!("User added with id: {}", last_id);
        }
        Err(err) => {
            println!("{}", err);
        }
    }
}