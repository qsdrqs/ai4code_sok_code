use std::fs::File;
use std::io;
use std::path::{Path, PathBuf};

const SAFE: &str = "/safedir/";

fn get_file(path: &str) -> io::Result<File> {
    // Construct the full path
    let test_path = Path::new(SAFE).join(path).canonicalize()?;
    
    // Check if test_path is within SAFE directory
    if !test_path.starts_with(Path::new(SAFE).canonicalize()?) {
        return Err(io::Error::new(
            io::ErrorKind::PermissionDenied,
            format!("Filename {} is not in {} directory", test_path.display(), SAFE),
        ));
    }
    
    // Open the file
    File::open(test_path)
}

fn main() -> io::Result<()> {
    let file = get_file("example.txt")?;
    // Use the file
    drop(file); // Example usage, file will be closed when it goes out of scope
    Ok(())
}