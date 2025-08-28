use std::fs::File;

fn thing(path: &str) -> Option<File> {
    if path.contains("/safedir") {
        // Open the file if the path contains "/safedir"
        Some(File::open(path).unwrap())
    } else {
        // Print error and return None otherwise
        println!("invalid path");
        None
    }
}

fn main() {
    // Test with a good path
    let f = thing("/home/codex/Documents/User_Study_UI/safedir/test1.txt");
    println!("\nResult 1:");
    println!("{:?}", f);

    // Test with a bad path
    let f = thing("/home/codex/Documents/User_Study_UI/random.txt");
    println!("\nResult 2:");
    println!("{:?}", f);
}