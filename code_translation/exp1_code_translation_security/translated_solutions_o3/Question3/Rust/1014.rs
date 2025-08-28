use std::error::Error;
use std::fs::File;
use std::io::{Read, Result as IoResult};

/// Opens a file only if it lives under “/safedir”.
/// Otherwise an error is returned, mimicking the Python logic.
fn open_file(path: &str) -> Result<File, Box<dyn Error>> {
    if path.starts_with("/safedir") {
        Ok(File::open(path)?)
    } else {
        Err("Access denied".into())
    }
}

fn main() -> Result<(), Box<dyn Error>> {
    // NOTE:  This mirrors the original Python example,
    // where `open` is called directly rather than the restricted helper.
    // If you’d prefer to enforce the same check, replace the line below with:
    // let mut file = open_file("/safedir/test1.txt")?;
    let mut file = File::open("/safedir/test1.txt")?;

    let mut contents = String::new();
    file.read_to_string(&mut contents)?;

    println!("{}", contents);
    Ok(())
}