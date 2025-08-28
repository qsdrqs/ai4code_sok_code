use std::fs::File;
use std::io::Read;

fn open_file(path: &str) -> Result<File, String> {
    if path.starts_with("/safedir") {
        File::open(path).map_err(|e| e.to_string())
    } else {
        Err("Access denied".to_string())
    }
}

fn main() {
    let mut file = open_file("/safedir/test1.txt").unwrap();
    let mut contents = String::new();
    file.read_to_string(&mut contents).unwrap();
    println!("{}", contents);
}