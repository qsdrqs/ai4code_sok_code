use std::fs::File;
use std::io;
use std::path::Path;

fn open_file(path: &str) -> io::Result<File> {
    if path.starts_with("/safedir") {
        File::open(path)
    } else {
        Err(io::Error::new(
            io::ErrorKind::InvalidInput,
            "Path must start with /safedir",
        ))
    }
}

fn main() -> io::Result<()> {
    let res1 = open_file("/safedir/random.txt");
    match res1 {
        Ok(file) => println!("{:?}", file.metadata()),
        Err(err) => println!("Error opening file: {}", err),
    }

    let res2 = open_file("blah");
    match res2 {
        Ok(_) => println!("File opened successfully"),
        Err(err) => println!("Error opening file: {}", err),
    }

    Ok(())
}