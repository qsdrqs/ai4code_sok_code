use std::fs::{File, OpenOptions};
use std::io::Read;
use std::path::Path;
use path_clean::PathClean;

fn get_file(path: &str) -> File {
    let normpath = Path::new(path).clean();
    let normpath_str = normpath.to_string_lossy();
    println!("{}", normpath_str);
    if !normpath_str.starts_with("/safedir") {
        println!("out of bounds");
        File::open("/dev/null").unwrap()
    } else {
        println!("opening file {}", normpath_str);
        OpenOptions::new()
            .read(true)
            .write(true)
            .open(&normpath)
            .unwrap()
    }
}

fn main() {
    let mut f = get_file("/safedir/test1.txt");
    let mut contents = String::new();
    f.read_to_string(&mut contents).unwrap();
    println!("{}", contents);

    let mut g = get_file("/dev/rand0");
    let mut contents = String::new();
    g.read_to_string(&mut contents).unwrap();
    println!("{}", contents);
}