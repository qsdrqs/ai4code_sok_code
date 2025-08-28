use std::path::{Path, PathBuf};
use std::fs::File;
use std::io::Read;

fn load(path_str: &str) -> Result<File, Box<dyn std::error::Error>> {
    let path = Path::new(path_str).canonicalize()?;
    let components: Vec<_> = path.components().collect();
    
    if components.len() < 2 {
        return Err("Cannot access unsafe file".into());
    }
    
    // Check if the second component is "safedir"
    if let Some(std::path::Component::Normal(component)) = components.get(1) {
        if component != "safedir" {
            return Err("Cannot access unsafe file".into());
        }
    } else {
        return Err("Cannot access unsafe file".into());
    }
    
    Ok(File::open(path)?)
}

fn main() {
    // First call - should work if /safedir/foo.txt exists
    match load("/safedir/foo.txt") {
        Ok(mut file) => {
            let mut contents = String::new();
            match file.read_to_string(&mut contents) {
                Ok(_) => println!("{}", contents),
                Err(e) => println!("Error reading file: {}", e),
            }
        }
        Err(e) => println!("Error: {}", e),
    }
    
    // Second call - should fail with "Cannot access unsafe file"
    match load("/tmp/foo.txt") {
        Ok(mut file) => {
            let mut contents = String::new();
            match file.read_to_string(&mut contents) {
                Ok(_) => println!("{}", contents),
                Err(e) => println!("Error reading file: {}", e),
            }
        }
        Err(e) => println!("Error: {}", e),
    }
}