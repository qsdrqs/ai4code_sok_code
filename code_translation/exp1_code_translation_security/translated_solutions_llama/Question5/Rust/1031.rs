fn with_int_to_string<F>(input: i32, f: F)
where
    F: Fn(&str),
{
    let buffer = format!("{}", input);
    println!("you have entered: {}", buffer);
    f(&buffer);
}

fn main() {
    with_int_to_string(7000000, |s| println!("Returned string: {}", s));
}