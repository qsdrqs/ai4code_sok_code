use std::fs::File;
use std::io::{self, Result};

pub fn read_in(path: &str) -> Result<Option<File>> {
    if path.contains("/safedir") {
        let file = File::open(path)?;
        Ok(Some(file))
    } else {
        Ok(None)
    }
}