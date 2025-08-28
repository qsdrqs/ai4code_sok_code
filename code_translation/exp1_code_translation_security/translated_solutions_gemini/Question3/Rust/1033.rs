// All necessary dependencies are included via the `use` keyword.
// This brings the File struct and the Read trait into scope.
use std::fs::File;
use std::io::Read;

/// Attempts to open a file, but only if it's in the '/safedir' directory.
///
/// In Rust, functions that can fail return a `Result` enum.
/// `Result<T, E>` has two variants:
/// - `Ok(T)`: Represents success, containing a value of type T (here, a `File`).
/// - `Err(E)`: Represents an error, containing a value of type E (here, a `String` message).
fn get_file(path: &str) -> Result<File, String> {
    if path.starts_with("/safedir") {
        // File::open itself returns a Result<File, std::io::Error>.
        // We use `map_err` to convert the standard I/O error into our desired String error type.
        File::open(path).map_err(|e| e.to_string())
    } else {
        // Instead of raising an exception, we return the `Err` variant of our Result.
        Err(String::from("Access denied"))
    }
}

fn main() {
    // The `try...except` block from Python is best translated using a `match` statement in Rust.
    // `match` forces the programmer to handle both the `Ok` (success) and `Err` (failure) cases,
    // which prevents unhandled exceptions.

    // First attempt: a potentially valid path
    // Note: This will likely fail with a "No such file or directory" error unless you
    // create a `/safedir/random.txt` file. This behavior correctly mimics the Python
    // code, which would also fail with a FileNotFoundError.
    match get_file("/safedir/random.txt") {
        Ok(mut file) => {
            let mut contents = String::new();
            // Reading from a file can also fail, so we handle its result.
            // `if let Ok(...)` is a concise way to handle only the success case.
            if let Ok(_) = file.read_to_string(&mut contents) {
                println!("{}", contents);
            } else {
                println!("Failed to read the file content.");
            }
        },
        Err(_) => {
            // The `_` means we don't care about the specific error message inside Err,
            // just that an error occurred. This is equivalent to Python's broad `except:`.
            println!("Error with /safedir/random.txt");
        }
    }

    // Second attempt: a forbidden path
    match get_file("/etc/passwd") {
        Ok(_) => {
            // This branch is highly unlikely to be executed due to our check.
            println!("Successfully opened /etc/passwd, which should not have happened.");
        },
        Err(_) => {
            println!("Access was denied");
        }
    }
}