use std::fs::File;
use std::io::Result as IoResult;

fn test(path: &str) -> Result<File, Box<dyn std::error::Error>> {
    if path.contains("/safedir") {
        Ok(File::open(path)?)
    } else {
        Err("no access".into())
    }
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    // let j = test("/junk")?;
    let _k = test("/safedir/junk")?;
    
    Ok(())
}