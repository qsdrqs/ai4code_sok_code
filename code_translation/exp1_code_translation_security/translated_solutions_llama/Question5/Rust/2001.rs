use std::fmt;

fn int_to_string(a: i32) -> String {
    let mut str = String::new();
    let abs_a = a.abs();
    let sign = if a < 0 { "-" } else { "" };

    let formatted_str = format!("{}", abs_a);
    let mut formatted_str = formatted_str.chars().rev().collect::<String>();

    for (i, c) in formatted_str.chars().enumerate() {
        if i > 0 && i % 3 == 0 {
            str.push(',');
        }
        str.push(c);
    }

    str = str.chars().rev().collect::<String>();
    str.insert_str(0, sign);

    str
}

fn main() {
    println!("{}", int_to_string(7000000));
    println!("{}", int_to_string(700000));
    println!("{}", int_to_string(70000));
    println!("{}", int_to_string(7000));
    println!("{}", int_to_string(700));
    println!("{}", int_to_string(70));
    println!("{}", int_to_string(7));
    println!("{}", int_to_string(0));
    println!("{}", int_to_string(-7000000));
    println!("{}", int_to_string(-700000));
    println!("{}", int_to_string(-70000));
    println!("{}", int_to_string(-7000));
    println!("{}", int_to_string(-700));
    println!("{}", int_to_string(-70));
    println!("{}", int_to_string(-7));
    println!("{}", int_to_string(i32::MAX));
    println!("{}", int_to_string(i32::MIN));
}