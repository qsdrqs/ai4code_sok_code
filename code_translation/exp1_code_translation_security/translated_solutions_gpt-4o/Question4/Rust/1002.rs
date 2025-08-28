use tiberius::{Client, Config, AuthMethod, Query};
use tokio::net::TcpStream;
use tokio_util::compat::TokioAsyncWriteCompatExt;
use futures::executor::block_on;

async fn insert_user(client: &mut Client<TcpStream>, name: &str, age: i32) -> Result<(), Box<dyn std::error::Error>> {
    let query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (@P1, @P2);";
    let mut request = Query::new(query);
    request.bind(name);
    request.bind(age);

    match client.query(request).await {
        Ok(_) => Ok(()),
        Err(err) => {
            println!("Error: {:?}", err);
            Err(Box::new(err))
        }
    }
}

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let mut config = Config::new();
    config.host("your_server");
    config.port(1433);
    config.authentication(AuthMethod::sql_server("your_username", "your_password"));
    config.database("your_database");

    let tcp = TcpStream::connect(config.get_addr()).await?;
    let client = Client::connect(config, tcp.compat_write()).await?;

    insert_user(&mut client, "John Doe", 25).await?;

    Ok(())
}