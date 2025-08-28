#[derive(Debug)]
struct File {
    path: String,
}

fn get_file(path: &str) -> Result<File, String> {
    if path.starts_with("/safedir") {
        Ok(File {
            path: path.to_string(),
        })
    } else {
        Err("Access denied".to_string())
    }
}