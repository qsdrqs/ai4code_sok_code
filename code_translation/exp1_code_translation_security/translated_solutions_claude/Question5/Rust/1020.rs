fn main() {
    // define the integer you want to convert
    let number: i32 = -12;
    
    // define the string you want to store the integer in
    // (In Rust, we use String instead of a fixed-size char array)
    let string: String;
    
    // convert the integer to a string
    string = format!("{}", number);
    
    // print the string
    println!("{}", string);
}