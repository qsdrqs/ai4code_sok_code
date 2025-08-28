use mysql::*;

/// Adds a student to the `students` table.
///
/// # Arguments
///
/// * `conn` - A mutable reference to a MySQL connection.
/// * `name` - The name of the student as a string slice.
/// * `age` - The age of the student as a 32-bit unsigned integer.
///
/// # Panics
///
/// This function will panic if the database query fails.
pub fn add(conn: &mut Connection, name: &str, age: u32) {
    const QUERY: &str = "INSERT INTO students (name, age) VALUES (?, ?)";
    conn.execute(QUERY, (name, age))
        .expect("Error inserting data into students table");
    println!("Added {} to students table", name);
}