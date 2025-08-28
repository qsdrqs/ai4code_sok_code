use std::fs::File;
use std::io;
use std::path::Path;
use std::process;

fn get_file_object(path: &str) -> File {
    if check_dir(path) {
        match File::open(path) {
            Ok(file) => file,
            Err(_) => {
                println!("Error");
                process::exit(1);
            }
        }
    } else {
        process::exit(1);
    }
}

fn check_dir(path: &str) -> bool {
    path.starts_with("/safedir")
}

fn main() {
    match get_file_object("/safedir/test1.txt") {
        file => println!("{:?}", file),
    }
}