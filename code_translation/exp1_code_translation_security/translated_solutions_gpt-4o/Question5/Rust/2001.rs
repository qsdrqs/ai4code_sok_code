use std::fmt::Write;

fn int_to_string(a: i32) -> String {
    let mut buf = String::new();
    write!(&mut buf, "{}", a).unwrap();

    let mut str = String::new();
    let mut chars = buf.chars();
    let mut len = buf.len();

    if len > 0 && buf.starts_with('-') {
        str.push('-');
        chars.next(); // Skip the negative sign
        len -= 1;
    }

    for (i, c) in chars.enumerate() {
        if i != 0 && (len - i) % 3 == 0 {
            str.push(',');
        }
        str.push(c);
    }

    str
}

fn main() {
    println!("{}", int_to_string(7_000_000));
    println!("{}", int_to_string(700_000));
    println!("{}", int_to_string(70_000));
    println!("{}", int_to_string(7_000));
    println!("{}", int_to_string(700));
    println!("{}", int_to_string(70));
    println!("{}", int_to_string(7));
    println!("{}", int_to_string(0));
    println!("{}", int_to_string(-7_000_000));
    println!("{}", int_to_string(-700_000));
    println!("{}", int_to_string(-70_000));
    println!("{}", int_to_string(-7_000));
    println!("{}", int_to_string(-700));
    println!("{}", int_to_string(-70));
    println!("{}", int_to_string(-7));
    println!("{}", int_to_string(i32::MAX));
    println!("{}", int_to_string(i32::MIN));
}