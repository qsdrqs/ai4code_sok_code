use std::fs::File;
use std::path::Path;

/// Replicates the behavior of the original Python `find_path`.
/// * Prints `"safe!"` and returns `Some(File)` if the parent directory
///   of `path` is exactly `/safedir`.
/// * Otherwise prints `"not safe!"` and returns `None`.
fn find_path<P: AsRef<Path>>(path: P) -> Option<File> {
    let safe_path = Path::new("/safedir");
    let path_ref  = path.as_ref();

    // `parent()` in Rust is analogous to Python's `os.path.dirname`.
    if let Some(parent) = path_ref.parent() {
        if parent == safe_path {
            println!("safe!");
            // Mimic Python's `open(path)`. If the open fails,
            // print the error and fall through to `None`.
            match File::open(path_ref) {
                Ok(file) => return Some(file),
                Err(err) => eprintln!("Could not open file: {}", err),
            }
        } else {
            println!("not safe!");
        }
    } else {
        // No parent (e.g., the path is just "/"); treat as not safe.
        println!("not safe!");
    }

    None
}

fn main() {
    // These calls mirror the two calls made in the Python script.
    let _ = find_path("/safedir/random.txt");
    let _ = find_path("/home/codex/Documents/User_Study_UI");
}