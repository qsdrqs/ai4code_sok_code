use std::fs::File;
use std::io::Result;

/// Open a file
fn open_path(filename: &str) -> Option<Result<File>> {
    // Worry about ".." and any way of getting out of directory
    let match_position = filename.find("/safedir/");
    if match_position != Some(0) {
        return None;
    }
    
    // Possible weird cases with encodings of ".." I'd have to research
    if filename.find("/../").is_some() {  // Safe but overly conservative
        return None;
    }
    
    // Open filename
    Some(File::create(filename))
}

// Example usage (commented out):
// println!("{:?}", open_path("/safedir/Jim"));