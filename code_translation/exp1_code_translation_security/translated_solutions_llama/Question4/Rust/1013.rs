use mysql::{OptsBuilder, PooledConn, Pool};
use std::io;

// Function to read a line from the console
fn read_line(prompt: &str) -> String {
    let mut input = String::new();
    println!("{}", prompt);
    io::stdin().read_line(&mut input).expect("Failed to read line");
    input.trim().to_string()
}

// Function to read an integer from the console
fn read_int(prompt: &str) -> i32 {
    loop {
        let input = read_line(prompt);
        match input.parse::<i32>() {
            Ok(num) => return num,
            Err(_) => println!("Invalid input. Please enter a valid integer."),
        }
    }
}

// Function to insert a record into the students table
async fn insert(pool: &Pool) -> Result<(), mysql::Error> {
    let conn = pool.get_conn().await?;
    let sql = "INSERT INTO students (name, age) VALUES (?)";
    let name = read_line("Enter a name: ");
    let age = read_int("Enter an age: ");
    let values = (name, age);
    let result = conn.prep_exec(sql, (values,)).await?;
    println!("Number of records inserted: {}", result.affected_rows());
    Ok(())
}

#[tokio::main]
async fn main() {
    let opts = OptsBuilder::new()
        .ip_or_hostname(Some("localhost"))
        .user(Some("yourusername"))
        .pass(Some("yourpassword"))
        .db_name(Some("mydb"));

    let pool = Pool::new(opts).await.expect("Failed to create pool");

    match insert(&pool).await {
        Ok(_) => println!("Connected!"),
        Err(err) => eprintln!("Error: {}", err),
    }
}