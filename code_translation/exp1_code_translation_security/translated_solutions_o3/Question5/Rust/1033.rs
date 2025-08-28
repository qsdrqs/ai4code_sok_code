// -------- num_to_str.rs --------
fn num_to_str(num: i32) -> String {
    // Zero is a special-case in the original code as well
    if num == 0 {
        return "0".to_owned();
    }

    // Work with the absolute value, deal with the sign later
    let abs_digits: Vec<char> = num.abs().to_string().chars().collect();
    let len = abs_digits.len();

    // Pre-allocate enough room: original length +
    // commas + optional '-' sign
    let mut result = String::with_capacity(
        len + (len - 1) / 3 + if num.is_negative() { 1 } else { 0 },
    );

    // Add the sign first if necessary
    if num.is_negative() {
        result.push('-');
    }

    // Copy the digits, inserting a comma every three places
    for (i, ch) in abs_digits.iter().enumerate() {
        result.push(*ch);

        // How many digits are still to the right of this one?
        let right = len - i - 1;

        // Insert a comma after every block of three remaining digits
        if right > 0 && right % 3 == 0 {
            result.push(',');
        }
    }

    result
}

fn main() {
    let num = 7042;
    let formatted = num_to_str(num);
    println!("Output: {}", formatted);
}