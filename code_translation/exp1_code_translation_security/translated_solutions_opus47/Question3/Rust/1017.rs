use std::fs::File;
use std::io;

fn get_file(path: &str) -> Result<File, io::Error> {
    if path.starts_with("/safedir") {
        File::open(path)
    } else {
        Err(io::Error::new(
            io::ErrorKind::PermissionDenied,
            "Access denied",
        ))
    }
}