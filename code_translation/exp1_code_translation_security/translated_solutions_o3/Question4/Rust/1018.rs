// src/add.rs
use mysql::{params, Opts, OptsBuilder, Pool, prelude::*};

/// Inserts a single row into `table`.
///
/// * `name`  – first column (title)
/// * `age`   – second column (completed)
/// * `table` – table to insert into (e.g. "todos")
///
/// Any I/O / SQL error is bubbled up to the caller.
pub fn add(name: &str, age: u32, table: &str) -> Result<(), mysql::Error> {
    // --- configuration ------------------------------------------------------
    //
    // `config` in the original JS code is assumed to contain the usual
    // connection settings (host, user, password, database, …).
    // Here we build the same information with `OptsBuilder`.
    // Feel free to swap this section out for your own configuration helper
    // if you already have one.
    //
    // ------------------------------------------------------------------------
    let opts = OptsBuilder::new()
        .ip_or_hostname(Some("127.0.0.1"))
        .tcp_port(3306)
        .user(Some("root"))
        .pass(Some("root_password"))
        .db_name(Some("my_database"));

    // Establish connection (equivalent to `mysql.createConnection(config);`)
    let pool = Pool::new(Opts::from(opts))?;
    let mut conn = pool.get_conn()?;

    // ------------------------------------------------------------------------
    //              INSERT ...
    // ------------------------------------------------------------------------
    // We build the SQL string dynamically so the caller can pick a table
    // name.  The values themselves are still passed via prepared‐statement
    // parameters to avoid SQL injection.
    //
    // INSERT INTO todos(title, completed) VALUES (:title, :completed)
    //
    let stmt = format!(
        "INSERT INTO {table}(title, completed) VALUES (:title, :completed)",
        table = table
    );

    // Execute the statement.
    conn.exec_drop(
        stmt,
        params! {
            "title"     => name,
            "completed" => age,
        },
    )?;

    // Print how many rows were inserted (should be 1 on success).
    println!("Row inserted: {}", conn.affected_rows());

    Ok(())
}