// All dependencies are provided for context, as requested.
// In a real project, you would add a crate like `rusqlite` to your Cargo.toml.
mod adodb {
    // A mock error type for demonstration.
    #[derive(Debug)]
    pub struct Error(String);

    // A mock trait that real libraries use to handle different data types.
    pub trait ToSql {}
    impl ToSql for &str {}
    impl ToSql for u32 {}

    // The mock Connection struct.
    pub struct Connection;

    impl Connection {
        // In Rust, we typically open the connection with a connection string.
        // This returns a Result to handle potential errors (e.g., DB not found).
        pub fn open(_connection_string: &str) -> Result<Self, Error> {
            // println!("-- Connection opened --");
            Ok(Connection)
        }

        // This is the modern, safe way to execute a command.
        // It takes the SQL query and parameters separately to prevent injection.
        pub fn execute(&self, command: &str, params: &[&dyn ToSql]) -> Result<usize, Error> {
            // In a real library, this would send the command and parameters
            // to the database engine safely.
            // println!("Executing: {} with params", command);
            // Returns the number of rows affected.
            Ok(1)
        }
    }
    
    // In idiomatic Rust, the connection is automatically closed when it goes out of
    // scope (this is called RAII). There is no need for an explicit `connection.close()`.
}

// We bring our "library" components into scope.
use adodb::{Connection, ToSql};

/// Adds a new student to the database.
///
/// # Arguments
/// * `name` - The name of the student.
/// * `age` - The age of the student.
///
/// # Returns
/// A `Result` which is `Ok(())` on success or an `Err` if the database operation fails.
fn add_student(name: &str, age: u32) -> Result<(), adodb::Error> {
    // 1. The SQL command uses `?` as placeholders for parameters.
    // This is the standard way to create safe, parameterized queries.
    let command = "INSERT INTO STUDENTS VALUES (?, ?);";

    // 2. Open a connection. The `?` operator will automatically
    //    return an error if the connection fails.
    let connection = Connection::open("")?;

    // 3. Execute the command, passing the parameters in a separate slice.
    // The database driver ensures that the `name` and `age` values are
    // treated as data, not as part of the SQL command.
    connection.execute(command, &[&name, &age])?;

    // 4. In Rust, there is no need to call `connection.close()`.
    // The connection is automatically closed when the `connection` variable
    // goes out of scope at the end of the function. This is a core Rust
    // feature called RAII (Resource Acquisition Is Initialization).

    Ok(())
}

fn main() {
    // Example of how to call the function
    let student_name = "Alice";
    let student_age = 22;

    println!("Attempting to add student: {}, age: {}", student_name, student_age);

    match add_student(student_name, student_age) {
        Ok(_) => println!("Student added successfully!"),
        Err(e) => eprintln!("Failed to add student: {:?}", e),
    }
}