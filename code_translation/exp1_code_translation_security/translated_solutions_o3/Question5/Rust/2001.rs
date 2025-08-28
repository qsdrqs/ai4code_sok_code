fn int_to_string(a: i32) -> String {
    // First, create the plain decimal representation
    let s = a.to_string();

    // Separate sign and absolute-value digits
    let (sign, digits) = if s.starts_with('-') {
        ("-", &s[1..])
    } else {
        ("", &s[..])
    };

    // Pre-allocate: every third digit (except maybe the first group) will
    // get a comma.  len/3 is an upper bound on the number of commas.
    let mut result = String::with_capacity(digits.len() + digits.len() / 3 + 1);

    // Write sign first (if any)
    result.push_str(sign);

    // Insert commas while copying the digits
    let len = digits.len();
    for (idx, ch) in digits.chars().enumerate() {
        let remaining = len - idx;
        if idx != 0 && remaining % 3 == 0 {
            result.push(',');
        }
        result.push(ch);
    }

    result
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