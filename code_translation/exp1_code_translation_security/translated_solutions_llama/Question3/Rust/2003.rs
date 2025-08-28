use std::fs::File;
use std::io::{Read, Result};
use std::path::Path;

// Define a function to load a file, checking if it's within a safe directory
fn load(path_str: &str) -> Result<File> {
    let path = Path::new(path_str).canonicalize()?;
    let parts: Vec<&str> = path.to_str().unwrap().split('/').collect();
    
    if parts.len() < 3 || parts[1] != "safedir" {
        panic!("Cannot access unsafe file");
    }
    
    File::open(path)
}

fn main() -> Result<()> {
    // Attempt to load and read a file from a safe directory
    let mut file1 = load("/safedir/foo.txt")?;
    let mut contents1 = String::new();
    file1.read_to_string(&mut contents1)?;
    println!("{}", contents1);

    // Attempt to load and read a file from an unsafe directory
    // This will panic as per the Python code
    let mut file2 = load("/tmp/foo.txt");
    match file2 {
        Ok(mut file) => {
            let mut contents2 = String::new();
            file.read_to_string(&mut contents2)?;
            println!("{}", contents2);
        },
        Err(_) => println!("Cannot access unsafe file"),
    }

    Ok(())
}