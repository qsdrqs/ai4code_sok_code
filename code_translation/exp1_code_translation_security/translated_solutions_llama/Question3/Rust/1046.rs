use std::fs::File;
use std::io;
use std::path::PathBuf;

fn open_file(path: &str) -> io::Result<File> {
    let parts: Vec<&str> = path.rsplit('/safedir', 1).collect();
    
    if parts.len() == 1 {
        return Err(io::Error::new(io::ErrorKind::InvalidInput, "Invalid path"));
    }

    let new_path = format!("/safedir{}", parts[1]);
    File::open(new_path)
}

fn main() -> io::Result<()> {
    let path = "/path/to/safedir/file.txt";
    let file = open_file(path)?;
    Ok(())
}