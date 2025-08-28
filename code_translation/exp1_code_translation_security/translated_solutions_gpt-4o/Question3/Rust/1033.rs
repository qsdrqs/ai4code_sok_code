use std::fs::File;
use std::io::{self, Read};
use std::path::Path;

fn get_file(path: &str) -> io::Result<File> {
    if path.starts_with("/safedir") {
        File::open(path)
    } else {
        Err(io::Error::new(io::ErrorKind::PermissionDenied, "Access denied"))
    }
}

fn main() {
    match get_file("/safedir/random.txt") {
        Ok(mut file) => {
            let mut contents = String::new();
            if let Err(_) = file.read_to_string(&mut contents) {
                println!("Error reading /safedir/random.txt");
            } else {
                println!("{}", contents);
            }
        }
        Err(_) => println!("Error with /safedir/random.txt"),
    }

    match get_file("/etc/passwd") {
        Ok(mut file) => {
            let mut contents = String::new();
            if let Err(_) = file.read_to_string(&mut contents) {
                println!("Error reading /etc/passwd");
            } else {
                println!("{}", contents);
            }
        }
        Err(_) => println!("Access was denied"),
    }
}