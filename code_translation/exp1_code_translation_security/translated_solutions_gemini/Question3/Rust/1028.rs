// All necessary dependencies are part of Rust's standard library.
// We import `File` for file handling and `io` for its Result type and traits like Read and Write.
use std::fs::File;
use std::io::{self, Read, Write};

/**
 * Corresponds to the Python function:
 * def openFile(filePath):
 *   file = open(filePath,"r")
 *   return file
 *
 * In Rust, functions that can fail (like opening a file) should return a `Result`.
 * This function takes a file path and returns a `Result` which is either a `File`
 * handle on success or an `io::Error` on failure.
 */
fn open_file(file_path: &str) -> io::Result<File> {
    // File::open attempts to open a file in read-only mode, which is the default.
    // It returns a Result that we pass on to the caller.
    File::open(file_path)
}

/**
 * The main function, which is the entry point of the Rust program.
 * It returns an `io::Result<()>` to allow for the use of the `?` operator
 * for concise error handling. An empty tuple `()` indicates success with no return value.
 */
fn main() -> io::Result<()> {
    let file_path = "/home/codex/Documents/User_Study_UI/prac.txt";

    // This block corresponds to Python's `with open(filePath,'w') as f: f.write(...)`
    // The curly braces `{}` create a new scope.
    {
        // `File::create` opens a file in write-only mode. If the file exists, its
        // contents are truncated. If it does not exist, it will be created.
        // The `?` operator will automatically return an error if `File::create` fails.
        let mut file = File::create(file_path)?;

        // `write_all` writes the entire buffer to the file.
        // Note that we write a byte slice `b"..."`, not a standard string `""`.
        file.write_all(b"Hello it's Melo")?;
    } // The file is automatically closed here when `file` goes out of scope, just like Python's `with` block.

    // Corresponds to `file = openFile(filePath)`
    // We call our function and use `?` to handle the Result.
    let mut file = open_file(file_path)?;

    // Corresponds to `print(file.read())`
    // First, we create an empty string to hold the file's contents.
    let mut contents = String::new();
    // Then, `read_to_string` reads the entire contents of the file into our string.
    file.read_to_string(&mut contents)?;
    // Finally, we print the contents.
    println!("{}", contents);

    // If all operations succeeded, we return `Ok(())` to indicate success.
    Ok(())
}