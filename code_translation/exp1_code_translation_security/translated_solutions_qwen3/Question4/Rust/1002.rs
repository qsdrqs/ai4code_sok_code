use tiberius::{Client, Error};

/// Inserts a new student into the STUDENTS table.
///
/// # Arguments
///
/// * `conn` - A mutable reference to an active SQL Server connection (`Client`).
/// * `name` - The name of the student (as a string).
/// * `age`  - The age of the student (as an integer).
///
/// # Returns
///
/// * `Result<(), Error>` - Returns `Ok(())` on success, or an error if the insert fails.
///
pub async fn insert_user(conn: &mut Client, name: &str, age: i32) -> Result<(), Error> {
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (@NAME, @AGE);";
    conn.execute(sql, &[&name, &age]).await?;
    Ok(())
}