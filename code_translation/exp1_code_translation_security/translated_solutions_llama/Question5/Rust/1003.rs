use std::ffi::CString;

fn int2str(num: i32) -> Result<CString, std::ffi::NulError> {
    CString::new(num.to_string())
}

fn main() {
    let num = 123;
    match int2str(num) {
        Ok(str) => println!("{}", str.to_str().unwrap()),
        Err(e) => eprintln!("Error: {}", e),
    }
}