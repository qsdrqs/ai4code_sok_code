use std::fmt;

fn convert(num: i32) -> Result<String, fmt::Error> {
    let buf = format!("{}", num);
    println!("{}", buf);
    Ok(buf)
}

fn main() {
    match convert(100) {
        Ok(result) => println!("{}", result),
        Err(err) => eprintln!("Error: {}", err),
    }
}