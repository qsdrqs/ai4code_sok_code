use sqlserver::{Connection, Request, Value, ColumnType};

// Assume active connection
fn insert_user(conn: &Connection, name: &str, age: i32) -> Result<(), sqlserver::Error> {
    let mut request = Request::new("INSERT INTO STUDENTS (NAME, AGE) VALUES (@NAME, @AGE);", conn)?;

    request.add_parameter("NAME", ColumnType::NVarChar, Value::String(name.to_string()))?;
    request.add_parameter("AGE", ColumnType::Int, Value::Int(age))?;

    conn.execute(&request)?;

    Ok(())
}

fn main() -> Result<(), sqlserver::Error> {
    // Establish a connection to the database
    let conn = Connection::new("Server=my_server;Database=my_database;User Id=my_user;Password=my_password;")?;

    // Insert a user
    insert_user(&conn, "John Doe", 30)?;

    Ok(())
}