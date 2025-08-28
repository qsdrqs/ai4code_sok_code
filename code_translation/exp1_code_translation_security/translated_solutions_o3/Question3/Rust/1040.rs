use std::fs::File;

/// Create a file object for the given path.  
/// If the path starts with `/safedir` we try to open it in read-only
/// mode and return `Some(File)`.  
/// Otherwise we return `None` (mirroring the Python example).
fn create_file_object(path: &str) -> Option<File> {
    if path.starts_with("/safedir") {
        // `ok()` converts `Result<File, std::io::Error>` → `Option<File>`
        File::open(path).ok()
    } else {
        None
    }
}

fn main() {
    println!("{:?}", create_file_object("/etc/passwd"));
}