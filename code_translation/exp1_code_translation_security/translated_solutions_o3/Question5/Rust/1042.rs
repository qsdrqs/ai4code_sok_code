// No external crates are required; everything uses the Rust standard library.

fn int_to_string(mut num: i32) -> String {
    // Special-case 0 so we don’t return an empty string.
    if num == 0 {
        return "0".to_owned();
    }

    // Handle a possible leading minus sign (the original C code didn’t,
    // but it costs almost nothing to add support here).
    let mut negative = false;
    if num < 0 {
        negative = true;
        num = -num;
    }

    // Collect characters from least-significant to most-significant digit.
    let mut buf: Vec<char> = Vec::with_capacity(32); // more than enough
    let mut count = 0;

    while num != 0 {
        let digit = (num % 10) as u8;
        buf.push((b'0' + digit) as char);

        num /= 10;
        count += 1;

        // Insert a comma after every third digit, as long as
        // we’re not at the most-significant position.
        if count == 3 && num != 0 {
            buf.push(',');
            count = 0;
        }
    }

    if negative {
        buf.push('-');
    }

    // Currently the characters are reversed; fix that.
    buf.reverse();

    // Convert Vec<char> into a String.
    buf.iter().collect()
}

fn main() {
    let formatted = int_to_string(7_000_000);
    println!("{}", formatted);  // prints: 7,000,000
}