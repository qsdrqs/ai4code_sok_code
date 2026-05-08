use std::fs::File;
use std::io::{self, Read};

fn get_file(path: &str) -> io::Result<File> {
    if path.starts_with("/safedir") {
        File::open(path)
    } else {
        Err(io::Error::new(
            io::ErrorKind::PermissionDenied,
            "Access denied",
        ))
    }
}

fn main() -> io::Result<()> {
    let mut file = get_file("/safedir/test1.txt")?;
    let mut contents = String::new();
    file.read_to_string(&mut contents)?;
    println!("{}", contents);

    // let mut file = get_file("/etc/passwd")?;
    // let mut contents = String::new();
    // file.read_to_string(&mut contents)?;
    // println!("{}", contents);

    Ok(())
}