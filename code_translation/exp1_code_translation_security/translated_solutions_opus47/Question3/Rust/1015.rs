use std::fs::File;
use std::io;

fn get_file_object(input_path: &str) -> Option<io::Result<File>> {
    let first_part = &input_path[0..9];
    if first_part == "/safedir/" {
        return Some(File::open(input_path));
    }
    None
}

fn main() {
    let _ = get_file_object("/safedir/test.txt");
}