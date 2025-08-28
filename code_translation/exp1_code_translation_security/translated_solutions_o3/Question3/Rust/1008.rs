use std::fs::File;
use std::io::{self, Error, ErrorKind};

fn test(path: &str) -> io::Result<File> {
    if path.contains("/safedir") {
        File::open(path)
    } else {
        Err(Error::new(ErrorKind::Other, "no access"))
    }
}

fn main() {
    // let _j = test("/junk");
    let _k = test("/safedir/junk")
        .expect("failed to open file");
}