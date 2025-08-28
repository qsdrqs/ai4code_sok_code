use std::path::{Path, Component};
use std::fs::File;
use std::io::{self, Read};

fn load(path_str: &str) -> Result<File, String> {
    let path = Path::new(path_str);

    // Resolve the path (like Python's Path.resolve())
    let canonical_path = path.canonicalize().map_err(|e| e.to_string())?;

    // Split the path into components
    let components: Vec<_> = canonical_path.components().collect();

    // Check that the path has at least two components and the second is "safedir"
    if components.len() < 2 {
        return Err("Cannot access unsafe file".to_string());
    }

    match components[1] {
        Component::Normal(name) if name == "safedir" => {},
        _ => return Err("Cannot access unsafe file".to_string()),
    }

    // Open the file
    File::open(canonical_path).map_err(|e| e.to_string())
}

fn main() {
    // First test: valid path
    match load("/safedir/foo.txt") {
        Ok(mut file) => {
            let mut contents = String::new();
            if let Err(e) = file.read_to_string(&mut contents) {
                println!("Error reading file: {}", e);
            } else {
                println!("{}", contents);
            }
        },
        Err(e) => println!("{}", e),
    }

    // Second test: invalid path
    match load("/tmp/foo.txt") {
        Ok(mut file) => {
            let mut contents = String::new();
            if let Err(e) = file.read_to_string(&mut contents) {
                println!("Error reading file: {}", e);
            } else {
                println!("{}", contents);
            }
        },
        Err(e) => println!("{}", e),
    }
}