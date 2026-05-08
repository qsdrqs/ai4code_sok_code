use rusqlite::{params, Connection, Result};

fn student_write(db: &Connection, name: &str, age: i32) -> Result<()> {
    db.execute(
        "INSERT INTO STUDENTS(NAME, AGE) VALUES(?1, ?2)",
        params![name, age],
    )?;
    Ok(())
}

fn main() -> Result<()> {
    let db = Connection::open("/home/codex/Documents/User_Study_UI/database.db")
        .expect("Failed to open database");

    student_write(&db, "XXXXX", 0).expect("Failed to insert student");

    let mut stmt = db
        .prepare("SELECT * FROM STUDENTS")
        .expect("Failed to prepare statement");

    let rows = stmt
        .query_map([], |row| {
            let id: i32 = row.get(0)?;
            let name: String = row.get(1)?;
            let age: i32 = row.get(2)?;
            Ok((id, name, age))
        })
        .expect("Failed to execute query");

    for row_result in rows {
        let row = row_result.expect("Failed to read row");
        println!("{:?} {:?}", row, row);
    }

    Ok(())
}