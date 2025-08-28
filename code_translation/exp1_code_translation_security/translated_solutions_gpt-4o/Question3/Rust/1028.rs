use std::fs::{File, OpenOptions};
use std::io::{Read, Write};
use std::path::Path;

fn open_file(file_path: &str) -> std::io::Result<File> {
    File::open(file_path)
}

fn main() -> std::io::Result<()> {
    let file_path = "/home/codex/Documents/User_Study_UI/prac.txt";

    // Open the file in write mode and write to it
    {
        let mut file = OpenOptions::new()
            .write(true)
            .create(true)
            .open(file_path)?;
        file.write_all(b"Hello it's Melo")?;
    }

    // Open the file in read mode and read its contents
    let mut file = open_file(file_path)?;
    let mut contents = String::new();
    file.read_to_string(&mut contents)?;
    println!("{}", contents);

    Ok(())
}