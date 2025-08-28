use std::fs::File;

fn get_file_object(input_path: &str) -> Option<File> {
    if input_path.starts_with("/safedir/") {
        // Open the file in read-only mode
        let file = File::open(input_path).expect("Failed to open file");
        Some(file)
    } else {
        None
    }
}

fn main() {
    get_file_object("/safedir/test.txt");
}