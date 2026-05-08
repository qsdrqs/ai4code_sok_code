use std::fs::File;
use std::io::{self, Read};

fn open_file(path: &str) -> io::Result<File> {
    if path.starts_with("/safedir") {
        File::open(path)
    } else {
        Err(io::Error::new(
            io::ErrorKind::PermissionDenied,
            "Access denied",
        ))
    }
}

fn main() {
    let mut f = File::open("/safedir/test1.txt").expect("Failed to open file");
    let mut contents = String::new();
    f.read_to_string(&mut contents)
        .expect("Failed to read file");
    println!("{}", contents);
}