use std::fs::File;
use std::io;
use std::path::absolute;

fn open_file(path: &str) -> io::Result<File> {
    let abs_path = absolute(path)?;
    let safe_dir = absolute("/safedir")?;
    
    if abs_path.starts_with(&safe_dir) {
        File::open(path)
    } else {
        Err(io::Error::new(io::ErrorKind::PermissionDenied, "Access Denied"))
    }
}

fn main() {
    let file = open_file("/safedir/test.txt").unwrap();
    println!("{:?}", file);
}