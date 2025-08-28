use std::fs::File;

fn func_path(path: &str) -> Result<File, String> {
    if path.contains("/safedir") {
        match File::open(path) {
            Ok(file) => Ok(file),
            Err(e) => Err(e.to_string()),
        }
    } else {
        Err("file not found or unsafe for access.".to_string())
    }
}

fn main() {
    let result = func_path("/safedir/Jim");
    println!("{:?}", result);
}