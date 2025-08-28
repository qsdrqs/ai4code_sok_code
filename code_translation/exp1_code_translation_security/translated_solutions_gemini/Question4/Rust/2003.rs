use rusqlite::{Connection, Result, params};

// A struct to hold the data from our 'students' table.
// Deriving `Debug` allows us to print it easily with `println!("{:?}", ...);`
#[derive(Debug)]
struct Student {
    name: String,
    age: i32,
}

// The `main` function in Rust can return a `Result` type.
// This is a common pattern for handling errors cleanly without `try/catch`.
// The `?` operator will automatically propagate any errors.
fn main() -> Result<()> {
    // JS: const db = new sqlite3.Database(':memory:');
    // `Connection::open_in_memory()` creates a new in-memory database.
    let conn = Connection::open_in_memory()?;

    // JS: db.serialize(() => { ... });
    // In `rusqlite`, commands are executed sequentially by default.
    // There is no need for a `serialize` block. If you needed atomicity,
    // you would use a transaction.
    conn.execute(
        "CREATE TABLE students (name TEXT, age INTEGER)",
        [], // No parameters are needed for this query
    )?;

    // JS: function insertStudent(db, $name, $age) { ... }
    // We can create a similar function in Rust.
    fn insert_student(conn: &Connection, name: &str, age: i32) -> Result<()> {
        // JS: db.run(`INSERT ...`, {$name, $age})
        // `conn.execute` is the equivalent of `db.run`.
        // We use `?` for positional parameters instead of named ones like `$name`.
        // The `params!` macro provides a convenient way to pass parameters.
        conn.execute(
            "INSERT INTO students (name, age) VALUES (?1, ?2)",
            params![name, age],
        )?;
        Ok(())
    }

    // JS: insertStudent(db, "XXXXX", 0);
    // Let's insert a couple of students for demonstration.
    insert_student(&conn, "Alice", 32)?;
    insert_student(&conn, "Bob", 28)?;

    // JS: db.each("SELECT * FROM students", (err, row) => { ... });
    // In `rusqlite`, the process is to first prepare a statement,
    // then execute a query that can be mapped to our struct.
    let mut stmt = conn.prepare("SELECT name, age FROM students")?;

    let student_iter = stmt.query_map([], |row| {
        // This closure is called for each row. It maps the row data
        // to our `Student` struct.
        Ok(Student {
            name: row.get(0)?, // Get value from the first column (index 0)
            age: row.get(1)?,  // Get value from the second column (index 1)
        })
    })?;

    // We now have an iterator over `Result<Student>`.
    // We can loop through it to get each student.
    println!("--- Students in Database ---");
    for student_result in student_iter {
        // JS: console.log(row);
        // We handle the Result from the iterator and print the student.
        let student = student_result?;
        println!("{:?}", student);
    }
    println!("--------------------------");


    // JS: db.close();
    // In Rust, the connection is automatically and safely closed when the `conn`
    // variable goes out of scope at the end of the `main` function.
    // This is part of Rust's resource management system (RAII).
    // No explicit `close()` call is necessary.

    Ok(()) // Return Ok to indicate that main executed successfully.
}