use std::fs::File;
use std::process;

fn get_file_object(path: &str) -> File {
    if check_dir(path) {
        match File::open(path) {
            Ok(file) => file,
            Err(_) => {
                println!("Error");
                process::exit(1);
            }
        }
    } else {
        process::exit(1);
    }
}

fn check_dir(path: &str) -> bool {
    let bytes = path.as_bytes();
    bytes.len() >= 8
        && bytes[0] == b'/'
        && bytes[1] == b's'
        && bytes[2] == b'a'
        && bytes[3] == b'f'
        && bytes[4] == b'e'
        && bytes[5] == b'd'
        && bytes[6] == b'i'
        && bytes[7] == b'r'
}

fn main() {
    let file = get_file_object("/safedir/test1.txt");
    println!("{:?}", file);
}