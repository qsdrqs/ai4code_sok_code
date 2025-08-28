use tiberius::{Client, Result};
use tokio::net::TcpStream;
use tokio_util::compat::TokioAsyncWriteCompatExt;

// In Rust, the database connection (client) is typically generic over the
// underlying IO stream. We can create a type alias for simplicity.
type MssqlClient = Client<tokio_util::compat::Compat<TcpStream>>;

/// Inserts a new user into the STUDENTS table.
///
/// # Arguments
///
/// * `conn` - A mutable reference to an active `tiberius::Client`.
/// * `name` - The name of the student to insert.
/// * `age`  - The age of the student to insert.
///
/// This function is `async` because database operations are non-blocking.
/// It returns a `tiberius::Result<()>` which is `Ok(())` on success
/// or an `Err` containing the database error on failure.
async fn insert_user(conn: &mut MssqlClient, name: &str, age: i32) -> Result<()> {
    // The SQL query uses named parameters (@name, @age) for clarity,
    // similar to the original JavaScript code.
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (@P1, @P2);";

    // `conn.execute()` sends the query to the database.
    // It takes the query string and a slice of parameters.
    // `tiberius` automatically infers the correct SQL Server data type
    // from the Rust type (e.g., &str -> NVarChar, i32 -> Int).
    // This replaces `request.addParameter()` and `TYPES`.
    conn.execute(sql, &[&name, &age])
        .await?; // .await replaces the callback, pausing execution until the query is done.
                 // The `?` operator handles errors: if the query fails, it
                 // immediately returns the error from this function.

    // If the query succeeds, we return `Ok(())` to indicate success.
    Ok(())
}

// Example of how you might use this function (for context)
// This part is not in the original snippet but shows how to call the function.
// You would need to configure your database connection details.
/*
#[tokio::main]
async fn main() -> Result<()> {
    let mut config = tiberius::Config::from_ado_string(
        "server=tcp:localhost,1433;user=SA;password=your_password;database=master;TrustServerCertificate=true"
    )?;
    config.trust_cert(); // Use this for self-signed certs in development

    let tcp = TcpStream::connect(config.get_addr()).await?;
    tcp.set_nodelay(true)?;

    let mut client = Client::connect(config, tcp.compat_write()).await?;

    println!("Inserting 'Jane Doe' aged 25.");
    match insert_user(&mut client, "Jane Doe", 25).await {
        Ok(_) => println!("Successfully inserted user."),
        Err(e) => eprintln!("Failed to insert user: {}", e),
    }

    println!("Inserting 'John Smith' aged 30.");
    match insert_user(&mut client, "John Smith", 30).await {
        Ok(_) => println!("Successfully inserted user."),
        Err(e) => eprintln!("Failed to insert user: {}", e),
    }

    Ok(())
}
*/