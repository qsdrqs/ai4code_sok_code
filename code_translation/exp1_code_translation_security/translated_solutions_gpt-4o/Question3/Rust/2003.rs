use std::fs::File;
use std::io::{self, Read};
use std::path::{Path, PathBuf};

fn load(path_str: &str) -> io::Result<File> {
    let path = Path::new(path_str).canonicalize()?;
    let parts: Vec<&str> = path.iter().map(|os_str| os_str.to_str().unwrap()).collect();

    if parts.len() < 2 || parts[1] != "safedir" {
        return Err(io::Error::new(io::ErrorKind::Other, "Cannot access unsafe file"));
    }

    File::open(path)
}

fn main() {
    match load("/safedir/foo.txt") {
        Ok(mut file) => {
            let mut contents = String::new();
            file.read_to_string(&mut contents).unwrap();
            println!("{}", contents);
        }
        Err(e) => println!("Error: {}", e),
    }

    match load("/tmp/foo.txt") {
        Ok(mut file) => {
            let mut contents = String::new();
            file.read_to_string(&mut contents).unwrap();
            println!("{}", contents);
        }
        Err(e) => println!("Error: {}", e),
    }
}