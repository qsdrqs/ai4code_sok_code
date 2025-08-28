use std::fs::File;
use std::path::Path;

/// The different kinds of values `return_object` can yield, mirroring the
/// Python version:
///
///   • `File`     – a successfully-opened file handle  
///   • `Sentinel` – corresponds to the Python function’s “-1” early return  
///   • `None`     – the Python function returned nothing because `open` failed
#[derive(Debug)]
enum ReturnValue {
    File(File),
    Sentinel(i32),
    None,
}

fn return_object<P: AsRef<Path>>(path: P) -> ReturnValue {
    // Get the canonical/real path (similar to Python’s os.path.realpath)
    let rpath = match std::fs::canonicalize(&path) {
        Ok(p)  => p,
        Err(_) => path.as_ref().to_path_buf(),   // fall back to the given path
    };

    // If it begins with “/safedir”, behave like `return -1`
    if rpath.to_string_lossy().starts_with("/safedir") {
        return ReturnValue::Sentinel(-1);
    }

    // Attempt to create/truncate the file (same as Python’s open(path,'w'))
    match File::create(&path) {
        Ok(fh) => ReturnValue::File(fh),
        Err(e) => {
            eprintln!("Error: {}", e);           // mimic Python’s print on error
            ReturnValue::None
        }
    }
}

fn main() {
    let file_name = "/tmp/foo";
    println!("{:?}", return_object(file_name));
}