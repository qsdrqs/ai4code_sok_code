// Converts a signed integer to a string with thousands separators.
fn int_to_string(num: i32) -> String {
    // Work on the absolute value first.
    let mut s = num.abs().to_string();

    // Insert commas every three digits, counting from the right.
    // `idx` is the index *before* which we insert a comma.
    let mut idx: isize = s.len() as isize - 3;
    while idx > 0 {
        s.insert(idx as usize, ',');
        idx -= 3;
    }

    // Re-add the sign if the original number was negative.
    if num < 0 {
        s.insert(0, '-');
    }

    s
}

fn main() {
    let num = 7_000_000;
    let formatted = int_to_string(num);
    println!("{formatted}"); // prints: 7,000,000
}