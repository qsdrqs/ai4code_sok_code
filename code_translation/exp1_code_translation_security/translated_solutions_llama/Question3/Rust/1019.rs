use std::fs::File;
use std::io::{Read, BufReader};
use std::path::{Path, PathBuf};

fn get_file(path: &str) -> File {
    let normpath = Path::new(path).canonicalize().unwrap();
    println!("{}", normpath.display());

    if !normpath.starts_with("/safedir") {
        println!("out of bounds");
        File::open("/dev/null").unwrap()
    } else {
        println!("opening file {}", normpath.display());
        File::open(normpath).unwrap()
    }
}

fn main() {
    let mut f = get_file("/safedir/test1.txt");
    let mut reader = BufReader::new(&mut f);
    let mut contents = String::new();
    reader.read_to_string(&mut contents).unwrap();
    println!("{}", contents);

    let mut g = get_file("/dev/rand0");
    let mut reader = BufReader::new(&mut g);
    let mut contents = String::new();
    reader.read_to_string(&mut contents).unwrap();
    println!("{}", contents);
}