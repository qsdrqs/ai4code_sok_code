use rusqlite::{Connection, Result};

fn main() -> Result<()> {
    let conn = Connection::open("studentdb")?;

    conn.execute(
        "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)",
        [],
    )?;

    let insert = |conn: &Connection, name: &str, age: i32| -> Result<()> {
        let mut stmt = conn.prepare("INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)")?;
        stmt.execute([name, &age.to_string()])?;
        
        /*conn.prepare("SELECT * FROM STUDENTS")?
            .query_map([], |row| {
                Ok((row.get::<_, String>(0)?, row.get::<_, i32>(1)?))
            })?
            .for_each(|row| {
                match row {
                    Ok((name, age)) => println!("Name: {}, Age: {}", name, age),
                    Err(e) => println!("Error: {}", e),
                }
            });*/
        
        Ok(())
    };

    insert(&conn, "Foo", 42)?;
    insert(&conn, "Bar", 55)?;

    conn.close().map_err(|(_, err)| err)?;
    
    Ok(())
}