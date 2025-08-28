use std::path::{PathBuf, Path, Component};

fn path_from_str(p: &str) -> Option<PathBuf> {
    let path = PathBuf::from(p);
    let mut components = path.components();
    match (components.next(), components.next()) {
        (Some(Component::RootDir), Some(Component::Normal(s))) if s == "safedir" => Some(path),
        _ => None,
    }
}

fn main() {
    println!("{:?}", path_from_str("/safedir/junk"));
    println!("{:?}", path_from_str("/bin"));
    println!("{:?}", path_from_str("/safedir/doesntexist"));
}