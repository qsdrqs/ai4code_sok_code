use std::fs::File;
use std::io;
use std::path::Path;

fn find_path(path: &str) -> io::Result<()> {
    let safe_path = "/safedir";
    let path = Path::new(path);
    let dirname = path.parent().unwrap_or(Path::new(""));

    if dirname == Path::new(safe_path) {
        let file = File::open(path)?;
        println!("safe!");
        Ok(())
    } else {
        println!("not safe!");
        Ok(())
    }
}

fn main() {
    find_path("/safedir/random.txt").unwrap();
    find_path("/home/codex/Documents/User_Study_UI").unwrap();
}