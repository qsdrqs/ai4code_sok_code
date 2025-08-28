use std::fs::File;
use std::path::Path;

/// Checks if a file path is within a designated "safe" directory.
/// If it is, it attempts to open the file.
///
/// # Arguments
///
/// * `path` - A string slice that holds the path to the file.
///
/// # Returns
///
/// * `Some(File)` if the path is safe and the file is successfully opened.
/// * `None` if the path is not safe or the file cannot be opened.
fn find_path(path: &str) -> Option<File> {
    // In Python: safe_path = "/safedir"
    let safe_path = Path::new("/safedir");

    let input_path = Path::new(path);

    // In Python: dirname = os.path.dirname(path)
    // `Path::parent()` is the Rust equivalent. It returns an Option<&Path>
    // to handle cases where there is no parent (e.g., a root path "/").
    if let Some(dirname) = input_path.parent() {
        // In Python: if dirname == safe_path:
        if dirname == safe_path {
            println!("safe!");
            // In Python: file = open(path)
            // `File::open` returns a `Result`. We use `.ok()` to convert
            // a success (Ok) into `Some(file)` and an error (Err) into `None`.
            // This gracefully handles cases where the file doesn't exist,
            // whereas the original Python code would have crashed.
            return File::open(path).ok();
        }
    }

    // This code is reached if the path has no parent or the parent is not "/safedir".
    // In Python: else: print("not safe!")
    println!("not safe!");
    None
}

fn main() {
    // NOTE: For the first call to succeed, the directory `/safedir` and the file
    // `/safedir/random.txt` must exist, and the program needs permission to read them.
    // This example does not create them, it just calls the function like the Python script.

    // In Python: find_path("/safedir/random.txt")
    find_path("/safedir/random.txt");

    // In Python: find_path("/home/codex/Documents/User_Study_UI")
    find_path("/home/codex/Documents/User_Study_UI");
}