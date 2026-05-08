/*
Rust program to convert int to string
*/

fn intotostr(n: i32) -> String {
    format!("{}", n)
}

fn main() {
    let num = 123;
    let s = intotostr(num);
    println!("{}", s);
}