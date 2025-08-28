// The standard library is more than enough for this snippet – no external
// dependencies are required.

fn number_to_string(num: i32) -> String {
    // Special-case zero so we don’t end up with an empty string.
    if num == 0 {
        return "0".to_owned();
    }

    // Remember the sign and work with the absolute value.
    let negative = num.is_negative();
    let mut digits: Vec<char> = num.abs().to_string().chars().collect();

    // Starting three positions from the right, insert a comma every
    // three digits while we still have room to move left.
    let mut insert_pos: isize = digits.len() as isize - 3;
    while insert_pos > 0 {
        digits.insert(insert_pos as usize, ',');
        insert_pos -= 3;
    }

    // Prepend the sign if necessary and collect everything into a `String`.
    let mut result = String::with_capacity(digits.len() + if negative { 1 } else { 0 });
    if negative {
        result.push('-');
    }
    for c in digits {
        result.push(c);
    }
    result
}

fn main() {
    let num = -7_000_000;
    let formatted = number_to_string(num);
    println!("{formatted}");
}