use std::fs::File;

const ALLOWED_PATH: &str = "/safedir";

fn get_file(path: &str) -> Result<File, String> {
    if &path[0..8] != ALLOWED_PATH {
        return Err(String::from("Not allowed to get file from this dir"));
    }
    File::open(path).map_err(|e| e.to_string())
}

fn main() {
    let _ = get_file("/safedir/test/path");
}