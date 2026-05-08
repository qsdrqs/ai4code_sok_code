use std::fs::File;
use std::io::{Read, Write};

fn open_file(file_path: &str) -> File {
    File::open(file_path).expect("Failed to open file")
}

fn main() {
    let file_path = "/home/codex/Documents/User_Study_UI/prac.txt";
    
    {
        let mut f = File::create(file_path).expect("Failed to create file");
        f.write_all(b"Hello it's Melo").expect("Failed to write to file");
    }
    
    let mut file = open_file(file_path);
    let mut contents = String::new();
    file.read_to_string(&mut contents).expect("Failed to read file");
    println!("{}", contents);
}