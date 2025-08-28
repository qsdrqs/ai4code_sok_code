use tiberius::{Client, Config, AuthMethod, Query, ColumnType};
use tokio_util::compat::{TokioAsyncWriteCompatExt, TokioAsyncReadCompatExt};
use tokio::net::TcpStream;
use std::env;

// Assume active connection
async fn insert_user(
    client: &mut Client<tokio_util::compat::Compat<TcpStream>>, 
    name: &str, 
    age: i32
) -> Result<(), Box<dyn std::error::Error>> {
    let query = Query::new("INSERT INTO STUDENTS (NAME, AGE) VALUES (@P1, @P2)");
    
    let result = query
        .bind(name)  // @P1 - equivalent to TYPES.NVarChar
        .bind(age)   // @P2 - equivalent to TYPES.Int
        .execute(client)
        .await;
    
    match result {
        Ok(_) => {
            println!("User inserted successfully");
            Ok(())
        }
        Err(err) => {
            println!("Error: {}", err);
            Err(Box::new(err))
        }
    }
}

// Example usage (you would need to establish connection first)
#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    // Connection setup example (you'll need to adjust based on your setup)
    let mut config = Config::new();
    config.host("localhost");
    config.port(1433);
    config.authentication(AuthMethod::sql_server("username", "password"));
    config.database("your_database");
    config.trust_cert();
    
    let tcp = TcpStream::connect(config.get_addr()).await?;
    tcp.set_nodelay(true)?;
    let mut client = Client::connect(config, tcp.compat_write()).await?;
    
    // Call the insert function
    insert_user(&mut client, "John Doe", 25).await?;
    
    Ok(())
}