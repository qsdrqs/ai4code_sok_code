fn main() {
    // define the integer you want to convert
    let number: i32;
    number = -12;
    
    // define the string you want to store the integer in
    let string: String;
    
    // convert the integer to a string
    string = format!("{}", number);
    
    // print the string
    println!("{}", string);
}