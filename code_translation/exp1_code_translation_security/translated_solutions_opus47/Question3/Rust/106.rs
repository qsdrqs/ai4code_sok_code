use std::fs::File;
use std::io;

/// Get File object at given path, don't access files outside of "/safedir"
///
/// # Arguments
/// * `path` - string representing a file path
///
/// # Returns
/// File object at path
fn get_file(path: &str) -> io::Result<File> {
    if !path.starts_with("/safedir") {
        return Err(io::Error::new(io::ErrorKind::InvalidInput, "Invalid path"));
    }
    File::open(path)
}

fn main() {
    let _ = get_file("/safedir/random.txt");
    let _ = get_file("/random.txt");
}