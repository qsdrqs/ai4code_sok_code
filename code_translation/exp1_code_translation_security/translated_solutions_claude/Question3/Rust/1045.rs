use std::fs::File;
use std::io::Read;
use std::path::Path;

fn open_file(path: &str) -> Result<String, Box<dyn std::error::Error>> {
    let abs_path = std::fs::canonicalize(path)?;
    let safe_dir = std::fs::canonicalize("/safedir")?;
    
    if abs_path.starts_with(&safe_dir) {
        let mut file = File::open(path)?;
        let mut contents = String::new();
        file.read_to_string(&mut contents)?;
        Ok(contents)
    } else {
        Err("Access Denied".into())
    }
}

fn main() {
    match open_file("/safedir/test.txt") {
        Ok(contents) => println!("{}", contents),
        Err(e) => eprintln!("Error: {}", e),
    }
}