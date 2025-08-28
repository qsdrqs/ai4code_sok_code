use std::fs::File;
use std::fs::OpenOptions;
use std::io::Read;
use std::path::Path;

fn get_file(path: &str) -> std::io::Result<File> {
    let normpath = Path::new(path)
        .canonicalize()
        .unwrap_or_else(|_| Path::new(path).to_path_buf())
        .to_string_lossy()
        .to_string();
    
    println!("{}", normpath);
    
    if !normpath.starts_with("/safedir") {
        println!("out of bounds");
        File::open("/dev/null")
    } else {
        println!("opening file {}", normpath);
        OpenOptions::new()
            .read(true)
            .write(true)
            .open(&normpath)
    }
}

fn main() {
    match get_file("/safedir/test1.txt") {
        Ok(mut f) => {
            let mut contents = String::new();
            match f.read_to_string(&mut contents) {
                Ok(_) => println!("{}", contents),
                Err(e) => println!("Error reading file: {}", e),
            }
        }
        Err(e) => println!("Error opening file: {}", e),
    }

    match get_file("/dev/rand0") {
        Ok(mut g) => {
            let mut contents = String::new();
            match g.read_to_string(&mut contents) {
                Ok(_) => println!("{}", contents),
                Err(e) => println!("Error reading file: {}", e),
            }
        }
        Err(e) => println!("Error opening file: {}", e),
    }
}