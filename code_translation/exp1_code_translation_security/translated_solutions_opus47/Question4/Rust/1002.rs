use tiberius::{Client, Query};
use tokio::net::TcpStream;
use tokio_util::compat::Compat;

// assume active conn
async fn insert_user(conn: &mut Client<Compat<TcpStream>>, name: &str, age: i32) {
    let mut request = Query::new("INSERT INTO STUDENTS (NAME, AGE) VALUES (@P1, @P2);");
    request.bind(name);
    request.bind(age);

    if let Err(err) = request.execute(conn).await {
        println!("{:?}", err);
    }
}