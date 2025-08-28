use std::fs::File;

fn test(path: &str) -> Result<File, String> {
    if path.contains("/safedir") {
        // Attempt to open the file and convert any I/O error to a String
        File::open(path).map_err(|e| e.to_string())
    } else {
        // Return a custom error message if the path is not allowed
        Err("no access".to_string())
    }
}

fn main() {
    // Uncomment the line below to test a disallowed path
    // let j = test("/junk").expect("Expected an error: no access");

    // Test with an allowed path
    let k = test("/safedir/junk").expect("Failed to open file: check if the file exists and path is correct");
}