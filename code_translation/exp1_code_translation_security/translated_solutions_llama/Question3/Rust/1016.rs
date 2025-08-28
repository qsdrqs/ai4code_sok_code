use std::fs::File;
use std::io;

fn path_to_file(path: &str) -> io::Result<Option<File>> {
    if path.contains("/safedir") {
        File::open(path).map(Some)
    } else {
        Ok(None)
    }
}

fn main() {
    match path_to_file("/safedir/random.txt") {
        Ok(Some(file)) => println!("{:?}", file),
        Ok(None) => println!("None"),
        Err(err) => println!("Error: {}", err),
    }

    match path_to_file("/notsafedir/random.txt") {
        Ok(Some(file)) => println!("{:?}", file),
        Ok(None) => println!("None"),
        Err(err) => println!("Error: {}", err),
    }
}