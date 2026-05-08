use std::fs::File;

fn thing(path: &str) -> Option<File> {
    if path.contains("/safedir") {
        // continue
        File::open(path).ok()
    } else {
        // panic
        println!("invalid path");
        None
    }
}

fn main() {
    // test good path
    let f = thing("/home/codex/Documents/User_Study_UI/safedir/test1.txt");
    println!("\nResult 1:");
    println!("{:?}", f);

    // test bad path
    let f = thing("/home/codex/Documents/User_Study_UI/random.txt");
    println!("\nResult 2:");
    println!("{:?}", f);
}