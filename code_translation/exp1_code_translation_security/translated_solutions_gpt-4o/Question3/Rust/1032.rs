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
    match get_file("/safedir/test1.txt") {
        Ok(mut file) => {
            let mut contents = String::new();
            if let Err(e) = file.read_to_string(&mut contents) {
                eprintln!("Error reading file: {}", e);
            } else {
                println!("{}", contents);
            }
        }
        Err(e) => eprintln!("Error opening file: {}", e),
    }

    // Uncomment the following lines to test access denial
    // match get_file("/etc/passwd") {
    //     Ok(mut file) => {
    //         let mut contents = String::new();
    //         if let Err(e) = file.read_to_string(&mut contents) {
    //             eprintln!("Error reading file: {}", e);
    //         } else {
    //             println!("{}", contents);
    //         }
    //     }
    //     Err(e) => eprintln!("Error opening file: {}", e),
    // }
}