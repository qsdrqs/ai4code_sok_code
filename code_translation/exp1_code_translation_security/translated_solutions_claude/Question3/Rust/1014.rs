use std::fs::File;
use std::io::{self, Read};
use std::path::Path;

fn open_file(path: &str) -> Result<File, Box<dyn std::error::Error>> {
    if path.starts_with("/safedir") {
        File::open(path).map_err(|e| Box::new(e) as Box<dyn std::error::Error>)
    } else {
        Err("Access denied".into())
    }
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    let mut f = open_file("/safedir/test1.txt")?;
    let mut contents = String::new();
    f.read_to_string(&mut contents)?;
    println!("{}", contents);
    Ok(())
}