use std::fs::File;
use std::io::{Read, Write};
use std::fs::OpenOptions;

fn open_file(file_path: &str) -> std::io::Result<File> {
    File::open(file_path)
}

fn main() -> std::io::Result<()> {
    let file_path = "/home/codex/Documents/User_Study_UI/prac.txt";
    
    // Write to file
    {
        let mut file = File::create(file_path)?;
        file.write_all(b"Hello it's Melo")?;
    }
    
    // Read from file
    let mut file = open_file(file_path)?;
    let mut contents = String::new();
    file.read_to_string(&mut contents)?;
    println!("{}", contents);
    
    Ok(())
}