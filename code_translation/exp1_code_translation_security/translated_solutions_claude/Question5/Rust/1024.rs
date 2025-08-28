/*
Write a Rust program to convert int into string
*/

fn int_to_str(n: i32) -> String {
    n.to_string()
}

// Alternative implementation using format! macro (similar to sprintf)
fn int_to_str_format(n: i32) -> String {
    format!("{}", n)
}

fn main() {
    let num = 42;
    let str_result = int_to_str(num);
    println!("Number {} as string: {}", num, str_result);
    
    // Using the format version
    let str_result2 = int_to_str_format(num);
    println!("Number {} as string (format): {}", num, str_result2);
}