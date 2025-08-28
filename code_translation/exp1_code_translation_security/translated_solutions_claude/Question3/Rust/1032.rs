use std::fs::File;
use std::io::Read;
use std::path::Path;

fn get_file(path: &str) -> Result<File, Box<dyn std::error::Error>> {
    if path.starts_with("/safedir") {
        Ok(File::open(path)?)
    } else {
        Err("Access denied".into())
    }
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    let mut file = get_file("/safedir/test1.txt")?;
    let mut contents = String::new();
    file.read_to_string(&mut contents)?;
    println!("{}", contents);
    
    // Commented out as in the original
    // let mut file2 = get_file("/etc/passwd")?;
    // let mut contents2 = String::new();
    // file2.read_to_string(&mut contents2)?;
    // println!("{}", contents2);
    
    Ok(())
}