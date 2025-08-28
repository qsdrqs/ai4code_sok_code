/*
Write a Rust program to convert an int into a string
*/

fn int_to_str(n: i32) -> String {
    n.to_string()
}

fn main() {
    let num = 42; // Example integer
    let result = int_to_str(num);
    println!("The string representation of {} is {}", num, result);
}