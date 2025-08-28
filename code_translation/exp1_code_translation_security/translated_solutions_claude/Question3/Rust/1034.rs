use std::fs::File;
use std::io::Read;
use std::process;

fn get_file_object(path: &str) -> File {
    if check_dir(path) {
        match File::open(path) {
            Ok(file_object) => file_object,
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
    let chars: Vec<char> = path.chars().collect();
    if chars.len() >= 8 
        && chars[0] == '/' 
        && chars[1] == 's' 
        && chars[2] == 'a' 
        && chars[3] == 'f' 
        && chars[4] == 'e' 
        && chars[5] == 'd' 
        && chars[6] == 'i' 
        && chars[7] == 'r' {
        true
    } else {
        false
    }
}

fn main() {
    let file = get_file_object("/safedir/test1.txt");
    println!("{:?}", file);
}