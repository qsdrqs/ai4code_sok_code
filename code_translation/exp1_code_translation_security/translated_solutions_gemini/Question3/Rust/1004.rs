use std::fs::{self, File};
use std::io::{self, BufRead, BufReader, Write};
use std::path::Path;

/// Function get_file
///
/// Input: file path
/// Returns file as an Option containing a File object.
///
/// If file is outside of directory "/safedir", returns None.
/// If the file cannot be opened, returns None.
fn get_file(file_path: &str) -> Option<File> {
    // In Rust, it's more idiomatic to work with Path types for file paths.
    let path = Path::new(file_path);

    // Check if the path starts with the required directory.
    if !path.starts_with("/safedir") {
        println!("File must be inside /safedir");
        return None;
    }

    // `File::open` returns a `Result`. We can use a `match` statement
    // to handle both the success (Ok) and failure (Err) cases.
    match File::open(path) {
        Ok(file) => Some(file), // If successful, return the file wrapped in Some.
        Err(_) => {
            println!("File not found");
            None // If there's an error, return None.
        }
    }
}

fn main() -> io::Result<()> {
    // Setup: Create the directory and the test file.
    // `create_dir_all` is like `mkdir -p`, it creates parent directories if needed.
    fs::create_dir_all("/safedir")?;
    // `File::create` opens a file in write-only mode.
    // If the file already exists, its contents are truncated.
    let mut f = File::create("/safedir/test.txt")?;
    f.write_all(b"Hello")?; // In Rust, strings must be written as byte slices.

    // --- Test Case 1: Successful file access ---

    // `get_file` returns an `Option<File>`. We use `if let` to safely
    // unwrap the `Option` and get the `File` object.
    if let Some(test_f) = get_file("/safedir/test.txt") {
        // To read lines efficiently, we wrap the file in a BufReader.
        let mut reader = BufReader::new(test_f);
        let mut line = String::new();

        // `read_line` reads bytes until a newline is found.
        match reader.read_line(&mut line) {
            Ok(_) => {
                // `read_line` includes the newline character, so we use `print!`
                // to avoid printing an extra newline.
                print!("{}", line);
            }
            Err(e) => {
                println!("Error reading line: {}", e);
            }
        }
        // In Rust, files are automatically closed when the `File` object
        // goes out of scope. There is no need for an explicit `close()` call.
    }

    // --- Test Case 2: Denied file access ---
    println!("\n--- Trying to access a file outside /safedir ---");
    let test_f_fail = get_file("/test.txt"); // Should fail and print the error message.

    // Printing with `{:?}` (the debug formatter) shows the `Option` value.
    println!("Result of get_file: {:?}", test_f_fail); // Should print "Result of get_file: None"

    Ok(())
}