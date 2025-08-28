// All necessary dependencies are imported from Rust's standard library.
// `std::fs::File` is used for file handling.
// `std::io` provides the standard I/O functionality, including the `Result` type for error handling.
use std::fs::File;
use std::io;

/// Translates the Python `getFileObject` function.
///
/// This function checks if the input path starts with "/safedir/".
/// - If it does, it attempts to open the file. The result of this attempt
///   (`Result<File, io::Error>`) is wrapped in an `Option`.
/// - If the path does not start with "/safedir/", it returns `None`, mirroring
///   the Python function's behavior of implicitly returning `None`.
///
/// The return type `Option<io::Result<File>>` precisely models the three possible outcomes:
/// 1. `Some(Ok(file))`: The file was successfully opened.
/// 2. `Some(Err(error))`: The path was valid, but opening the file failed (e.g., not found).
/// 3. `None`: The path was invalid because it was not in "/safedir/".
fn get_file_object(input_path: &str) -> Option<io::Result<File>> {
    // In Rust, `starts_with` is the idiomatic and safe way to check the beginning of a string.
    // This is equivalent to Python's `inputPath[0:9] == "/safedir/"`.
    if input_path.starts_with("/safedir/") {
        // Attempt to open the file. This operation returns a `Result`.
        // We wrap it in `Some` to match the function's return signature.
        Some(File::open(input_path))
    } else {
        // If the path is not in the allowed directory, return `None`.
        None
    }
}

/// The main function, which serves as the entry point of the program.
/// This is the equivalent of Python's `if __name__ == "__main__":` block.
fn main() {
    // For this example to work, the directory "/safedir" and the
    // file "test.txt" inside it must exist.
    let path = "/safedir/test.txt";
    println!("Attempting to open: {}", path);

    // Call the function and store its result.
    let result = get_file_object(path);

    // In Rust, it's idiomatic to handle all possible outcomes explicitly.
    // We use a `match` statement, which is a powerful control flow operator.
    match result {
        // Case 1: The path was valid, and the file was opened successfully.
        Some(Ok(file)) => {
            println!("Successfully opened file. File object: {:?}", file);
        }
        // Case 2: The path was valid, but there was an error opening the file.
        Some(Err(e)) => {
            eprintln!("Error: Could not open file at '{}': {}", path, e);
        }
        // Case 3: The path was not in "/safedir/".
        None => {
            eprintln!("Error: Path '{}' is not in the allowed '/safedir/' directory.", path);
        }
    }
}