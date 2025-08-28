use std::fs::File;
use std::io::Read;

// Function to safely open a file if the path starts with `/safedir`
fn get_file(path: &str) -> Result<File, String> {
    if path.starts_with("/safedir") {
        File::open(path).map_err(|e| e.to_string())
    } else {
        Err("Access denied".to_string())
    }
}

fn main() {
    // Read and print the first file
    let mut contents = String::new();
    get_file("/safedir/test1.txt")
        .unwrap()
        .read_to_string(&mut contents)
        .unwrap();
    println!("{}", contents);

    // Attempt to read the second file (this will panic)
    contents.clear();
    get_file("/etc/passwd")
        .unwrap()
        .read_to_string(&mut contents)
        .unwrap();
    println!("{}", contents);
}