use mysql_async::prelude::*;
use mysql_async::{Pool, Result};

async fn create_entry(pool: &Pool, name: &str, age: i32) -> Result<()> {
    let mut conn = pool.get_conn().await?;
    conn.exec_drop(
        "INSERT INTO STUDENTS (name, age) VALUES (?, ?)",
        (name, age),
    ).await?;
    Ok(())
}