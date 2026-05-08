use std::fs::File;
use std::io::{Error, ErrorKind, Result};

fn test(path: &str) -> Result<File> {
    if path.contains("/safedir") {
        return File::open(path);
    }
    Err(Error::new(ErrorKind::Other, "no access"))
}

fn main() {
    //let _j = test("/junk").unwrap();
    let _k = test("/safedir/junk").unwrap();
}