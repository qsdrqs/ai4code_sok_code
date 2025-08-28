use sqlx::PgPool;

async fn add_student(pool: &PgPool, name: &str, age: i32) -> Result<(), sqlx::Error> {
    sqlx::query("INSERT INTO STUDENTS (NAME, AGE) VALUES ($1, $2)")
        .bind(name)
        .bind(age)
        .execute(pool)
        .await?;
    Ok(())
}