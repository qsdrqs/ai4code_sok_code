use std::fs::File;
use std::io;
use std::path::Path;

fn open_file(path: &str) -> io::Result<File> {
    let safe_dir = "/safedir";
    let path_abs = Path::new(path).canonicalize()?;
    let safe_dir_abs = Path::new(safe_dir).canonicalize().unwrap_or_else(|_| panic!("Failed to canonicalize safe directory"));

    if path_abs.starts_with(safe_dir_abs) {
        File::open(path)
    } else {
        Err(io::Error::new(io::ErrorKind::PermissionDenied, "Access Denied"))
    }
}

fn main() {
    match open_file("/safedir/test.txt") {
        Ok(file) => println!("{:?}", file.metadata().unwrap().len()),
        Err(err) => println!("Error: {}", err),
    }
}