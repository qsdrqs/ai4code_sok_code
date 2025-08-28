use std::path::{Path, PathBuf};

fn path_from_str(p: &str) -> Option<PathBuf> {
    let path = Path::new(p);
    let mut components = path.components();

    if let (Some(first), Some(second)) = (components.next(), components.next()) {
        if first.as_os_str() == "/" && second.as_os_str() == "safedir" {
            return Some(path.to_path_buf());
        }
    }
    None
}

fn main() {
    println!("{:?}", path_from_str("/safedir/junk"));
    println!("{:?}", path_from_str("/bin"));
    println!("{:?}", path_from_str("/safedir/doesntexist"));
}