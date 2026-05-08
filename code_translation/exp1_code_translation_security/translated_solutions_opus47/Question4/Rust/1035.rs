use rusqlite::{params, Connection, Result};

fn insert(db: &Connection, name: &str, age: i32) -> Result<()> {
    let mut stmt = db.prepare("INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)")?;
    stmt.execute(params![name, age])?;

    /*
    let mut stmt = db.prepare("SELECT * FROM STUDENTS")?;
    let rows = stmt.query_map([], |row| {
        Ok((row.get::<_, String>(0)?, row.get::<_, i32>(1)?))
    })?;
    for row in rows {
        println!("{:?}", row?);
    }
    */

    Ok(())
}

fn main() -> Result<()> {
    let db = Connection::open("studentdb")?;

    db.execute(
        "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)",
        [],
    )?;

    insert(&db, "Foo", 42)?;
    insert(&db, "Bar", 55)?;

    db.close().map_err(|(_, e)| e)?;

    Ok(())
}