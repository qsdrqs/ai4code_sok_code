fn int_to_string(x: i32) -> String {
    x.to_string()
}

// Example of how to use the function
fn main() {
    let my_number = 12345;
    let my_string = int_to_string(my_number);
    println!("The integer is: {}", my_number);
    println!("The converted string is: '{}'", my_string);

    let negative_number = -987;
    let negative_string = int_to_string(negative_number);
    println!("The integer is: {}", negative_number);
    println!("The converted string is: '{}'", negative_string);
}