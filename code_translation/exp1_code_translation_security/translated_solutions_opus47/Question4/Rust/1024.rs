/*
Is not TESTED!!
*/
use mysql::*;
use mysql::prelude::*;

fn main() -> Result<()> {
    let opts = OptsBuilder::new()
        .ip_or_hostname(Some("127.0.0.1"))
        .user(Some("yourusername"))
        .pass(Some("yourpassword"))
        .db_name(Some("mydb"));

    // connect to your database
    let pool = Pool::new(opts)?;
    let mut con = pool.get_conn()?;

    println!("Connected!");

    let sql = "INSERT INTO customers (name, address) VALUES (?, ?)";
    let values: Vec<(&str, &str)> = vec![
        ("XXXXX", "XXXXX"),
    ];

    let mut affected_rows: u64 = 0;
    for (name, address) in &values {
        con.exec_drop(sql, (name, address))?;
        affected_rows += con.affected_rows();
    }

    println!("Number of records inserted: {}", affected_rows);

    Ok(())
}