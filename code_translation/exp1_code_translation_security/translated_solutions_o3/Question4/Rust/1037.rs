use mysql::{prelude::*, Pool, Result};

/// Inserts a new row into the STUDENTS table.
///
/// The `name` and `age` parameters are kept to reflect the original
/// JavaScript signature, although they are not used in the SQL shown here.
fn insert_student(_name: &str, _age: u32) -> Result<()> {
    // 1. Establish a connection.
    //    `CONFIG` (or the equivalent connection string) will be supplied by the
    //    surrounding project, so we just reference it here.
    let pool = Pool::new(CONFIG)?;            // <-- provided elsewhere
    let mut conn = pool.get_conn()?;          // acquire a single connection

    // 2. Prepare the INSERT statement.
    //    (Same constant values as in the original JS.)
    let sql = r#"INSERT INTO STUDENTS(title, completed)
                 VALUES('Learn how to insert a new row', true)"#;

    // 3. Execute the query.
    conn.query_drop(sql)?;

    // 4. Connection is closed automatically when `conn` goes out of scope.
    Ok(())
}

fn main() -> Result<()> {
    insert_student("XXXXX", 0)?;
    Ok(())
}