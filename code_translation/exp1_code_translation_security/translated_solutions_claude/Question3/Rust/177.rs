use std::fs::File;
use std::path::Path;

fn path2obj(file_path: &str) -> Option<File> {
    // Open the file
    let f = match File::open(file_path) {
        Ok(file) => file,
        Err(_) => return None,
    };
    
    // Get the directory path
    let path = Path::new(file_path);
    let mut directory = path.parent();
    
    while let Some(dir) = directory {
        if let Some(dir_name) = dir.file_name() {
            if dir_name == "safedir" {
                return Some(f);
            }
        }
        directory = dir.parent();
    }
    
    None
}