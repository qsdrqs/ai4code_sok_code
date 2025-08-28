use sqlx::{Pool, MySql, Error};

/// Inserts a new student into the `STUDENTS` table.
///
/// # Arguments
/// * `pool` – An open `sqlx` MySQL connection pool.
/// * `name` – The student’s name.
/// * `age` – The student’s age.
///
/// # Errors
/// Returns any error yielded by the database driver.
pub async fn add_student(
    pool: &Pool<MySql>,
    name: &str,
    age: i32,
) -> Result<(), Error> {
    // NOTE: Using parameterised SQL is safer than string concatenation —
    // it avoids SQL-injection issues and lets the driver handle quoting.
    sqlx::query("INSERT INTO STUDENTS (name, age) VALUES (?, ?)")
        .bind(name)
        .bind(age)
        .execute(pool)
        .await?;

    println!("1 record inserted");
    Ok(())
}