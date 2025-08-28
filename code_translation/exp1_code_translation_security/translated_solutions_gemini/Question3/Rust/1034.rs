// All necessary dependencies from the standard library are included.
// `File` is for file handling, and `process` is for exiting the program.
use std::fs::File;
use std::process;

/// Checks if the given path starts with "/safedir".
/// This is the Rust equivalent of the `check_dir` function.
fn check_dir(path: &str) -> bool {
    // The `starts_with` method is a more idiomatic and efficient way
    // to check the prefix of a string than character-by-character comparison.
    path.starts_with("/safedir")
}

/// Attempts to open a file, but only if it's in a "safe" directory.
/// Exits the program on any failure (invalid path or I/O error).
/// This is the Rust equivalent of the `get_file_object` function.
fn get_file_object(path: &str) -> File {
    // First, check if the path is in the allowed directory.
    if check_dir(path) {
        // If the path is valid, attempt to open the file.
        // `File::open` returns a `Result` enum, which we handle with a `match`.
        // This is the idiomatic Rust way to handle operations that can fail,
        // similar to a try-except block in Python.
        match File::open(path) {
            Ok(file) => {
                // If the file was opened successfully, return the file object.
                file
            }
            Err(_) => {
                // If there was an I/O error (e.g., file not found),
                // print an error and exit the program.
                println!("Error");
                // `process::exit(1)` terminates the program with a non-zero
                // status code, indicating an error occurred. This is like `sys.exit()`.
                process::exit(1);
            }
        }
    } else {
        // If the path is not in "/safedir", exit the program.
        process::exit(1);
    }
}

fn main() {
    // NOTE: For this code to run successfully, you must first create the directory
    // and file it tries to open. You can do this on a Unix-like system with:
    // $ sudo mkdir -p /safedir
    // $ sudo touch /safedir/test1.txt
    // $ sudo chmod -R 777 /safedir
    //
    // Since creating a directory at the root `/` requires sudo permissions,
    // for local testing, you might want to change the path to a relative one,
    // e.g., "safedir/test1.txt", and update `check_dir` accordingly.

    let file_path = "/safedir/test1.txt";
    let file_object = get_file_object(file_path);

    // In Python, printing a file object shows its metadata.
    // In Rust, we can use the `Debug` format specifier `{:?}` to print
    // a developer-friendly representation of the `File` struct.
    println!("Successfully opened file: {:?}", file_object);
}