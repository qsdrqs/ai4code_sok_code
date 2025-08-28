use std::path::{Path, PathBuf};

fn path_from_str(p: &str) -> Option<PathBuf> {
    let path = Path::new(p);
    if let Some((root, dir)) = path.parts().next() {
        if root == "/" && dir == "safedir" {
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