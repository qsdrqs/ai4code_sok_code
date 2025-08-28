use std::fs::File;
use std::io::{self, Read};
use std::path::{Path, PathBuf};

fn get_file(path: &str) -> io::Result<File> {
    let normpath = Path::new(path).canonicalize().unwrap_or_else(|_| PathBuf::from(path));
    println!("{}", normpath.display());

    if !normpath.starts_with("/safedir") {
        println!("out of bounds");
        File::open("/dev/null")
    } else {
        println!("opening file {}", normpath.display());
        File::open(&normpath)
    }
}

fn main() -> io::Result<()> {
    let mut f = get_file("/safedir/test1.txt")?;
    let mut contents = String::new();
    f.read_to_string(&mut contents)?;
    println!("{}", contents);

    let mut g = get_file("/dev/rand0")?;
    let mut contents = String::new();
    g.read_to_string(&mut contents)?;
    println!("{}", contents);

    Ok(())
}