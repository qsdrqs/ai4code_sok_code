use odbc_api::{ConnectionOptions, Environment, IntoParameter};
use std::error::Error;

fn add_student(name: &str, age: i32) -> Result<(), Box<dyn Error>> {
    // Create an ODBC environment (similar to creating an ActiveXObject)
    let env = Environment::new()?;

    // Open a connection (equivalent to ADODB.Connection)
    let connection = env.connect_with_connection_string(
        "", // Connection string
        ConnectionOptions::default(),
    )?;

    // Use a parameterized query to prevent SQL injection
    let command = "INSERT INTO STUDENTS VALUES (?, ?)";
    connection.execute(
        command,
        (&name.into_parameter(), &age.into_parameter()),
    )?;

    // Connection is automatically closed when dropped (RAII)
    Ok(())
}