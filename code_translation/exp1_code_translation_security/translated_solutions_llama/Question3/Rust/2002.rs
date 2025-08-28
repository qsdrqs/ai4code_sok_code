use std::fs::File;
use std::path::Path;

/// Returns a `File` object for the given path relative to `/safedir`.
///
/// # Arguments
///
/// * `path` - The path relative to `/safedir`, e.g., "hello/world" to refer to `/safedir/hello/world`
///
/// # Returns
///
/// A `Result` containing a `File` object if successful, or an error if the file cannot be opened.
fn get_file(path: &str) -> std::io::Result<File> {
    let safedir = "/safedir";
    let full_path = Path::new(safedir).join(path);
    File::open(full_path)
}

fn main() {
    match get_file("hello/world") {
        Ok(file) => println!("File opened successfully"),
        Err(err) => eprintln!("Error opening file: {}", err),
    }
}