// NOTE: This code is **not** runtime–tested, it is a direct translation of the
// original JavaScript sample.

// Required crate: mysql = "23"
use mysql::{params, prelude::*, Pool};

fn main() -> Result<(), Box<dyn std::error::Error>> {
    // 1) Build the connection string
    let url = "mysql://yourusername:yourpassword@127.0.0.1:3306/mydb";

    // 2) Create a connection pool and fetch one connection
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;

    println!("Connected!");

    // 3) Data you want to insert
    let values: Vec<(String, String)> = vec![
        ("XXXXX".into(), "XXXXX".into()),
        // add more rows here if you like
    ];

    // 4) Perform a batch insert
    conn.exec_batch(
        r"INSERT INTO customers (name, address) VALUES (:name, :address)",
        values.iter().map(|(name, address)| {
            params! {
                "name" => name,
                "address" => address,
            }
        }),
    )?;

    // 5) Obtain number of affected rows *for this connection*
    let affected_rows = conn.affected_rows();

    println!("Number of records inserted: {}", affected_rows);

    Ok(())
}