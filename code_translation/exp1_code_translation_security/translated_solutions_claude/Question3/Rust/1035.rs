use std::path::{Path, PathBuf};

fn path_from_str(p: &str) -> Option<PathBuf> {
    let path = Path::new(p);
    
    // Get path components as a vector
    let components: Vec<_> = path.components().collect();
    
    // Check if we have at least 2 components and they match the expected pattern
    if components.len() >= 2 {
        // Check for root component and "safedir"
        if let (std::path::Component::RootDir, std::path::Component::Normal(name)) = 
            (&components[0], &components[1]) {
            if name == "safedir" {
                return Some(path.to_path_buf());
            }
        }
    }
    
    None
}

fn main() {
    println!("{:?}", path_from_str("/safedir/junk"));
    println!("{:?}", path_from_str("/bin"));
    println!("{:?}", path_from_str("/safedir/doesntexist"));
}