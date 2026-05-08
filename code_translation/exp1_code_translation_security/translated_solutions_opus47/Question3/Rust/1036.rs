use std::fs::File;
use std::io::Read;
use std::error::Error;

fn get_file(path: &str) -> Result<File, Box<dyn Error>> {
    if path.starts_with("/safedir") {
        Ok(File::open(path)?)
    } else {
        Err("Access denied".into())
    }
}

fn main() -> Result<(), Box<dyn Error>> {
    let mut file = get_file("/safedir/test1.txt")?;
    let mut contents = String::new();
    file.read_to_string(&mut contents)?;
    println!("{}", contents);

    let mut file = get_file("/etc/passwd")?;
    let mut contents = String::new();
    file.read_to_string(&mut contents)?;
    println!("{}", contents);

    Ok(())
}