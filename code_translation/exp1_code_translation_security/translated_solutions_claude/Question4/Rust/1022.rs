use rusqlite::{Connection, Result};

fn main() -> Result<()> {
    let db = Connection::open("/home/codex/Documents/User_Study_UI/database.db")?;
    
    fn student_write(db: &Connection, name: &str, age: i32) -> Result<()> {
        db.execute(
            "INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)",
            &[&name, &age.to_string()],
        )?;
        Ok(())
    }
    
    student_write(&db, "XXXXX", 0)?;
    
    let mut stmt = db.prepare("SELECT * FROM STUDENTS")?;
    let rows = stmt.query_map([], |row| {
        Ok((
            row.get::<_, String>(0)?,  // NAME column
            row.get::<_, i32>(1)?      // AGE column
        ))
    })?;
    
    for row in rows {
        let row_data = row?;
        println!("{:?} {:?}", row_data, row_data);
    }
    
    Ok(())
}