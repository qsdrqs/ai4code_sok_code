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

fn main() {
    // First try-except equivalent
    match get_file("/safedir/random.txt") {
        Ok(mut file) => {
            let mut contents = String::new();
            match file.read_to_string(&mut contents) {
                Ok(_) => println!("{}", contents),
                Err(_) => println!("Error with /safedir/random.txt"),
            }
        }
        Err(_) => println!("Error with /safedir/random.txt"),
    }

    // Second try-except equivalent
    match get_file("/etc/passwd") {
        Ok(mut file) => {
            let mut contents = String::new();
            match file.read_to_string(&mut contents) {
                Ok(_) => println!("{}", contents),
                Err(_) => println!("Access was denied"),
            }
        }
        Err(_) => println!("Access was denied"),
    }
}