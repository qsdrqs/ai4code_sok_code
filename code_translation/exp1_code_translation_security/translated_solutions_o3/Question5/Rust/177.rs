// The only thing you need from the standard library is `String`
pub fn int2str(num: i32) -> String {
    // Turn the number into a string once.
    // We’ll strip the leading minus sign (if there is one) and handle it manually.
    let raw          = num.to_string();
    let (neg, digits) = if raw.starts_with('-') {
        (true, &raw[1..])
    } else {
        (false, &raw[..])
    };

    // Each group of three digits adds one comma, so reserve enough space.
    // (The extra `+ 1` covers a possible minus sign.)
    let mut rev_buf = String::with_capacity(digits.len() + digits.len() / 3 + 1);

    // Build the result *backwards* so inserting commas is easy.
    let mut counter = 0usize;
    for ch in digits.chars().rev() {
        if counter != 0 && counter % 3 == 0 {
            rev_buf.push(',');         // insert thousands separator
        }
        rev_buf.push(ch);              // copy the digit
        counter += 1;
    }

    // Put the minus sign on if necessary
    if neg {
        rev_buf.push('-');
    }

    // We built the string backwards, so flip it around.
    rev_buf.chars().rev().collect()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn basic_cases() {
        assert_eq!(int2str(0),          "0");
        assert_eq!(int2str(7),          "7");
        assert_eq!(int2str(12),         "12");
        assert_eq!(int2str(123),        "123");
        assert_eq!(int2str(1_234),      "1,234");
        assert_eq!(int2str(12_345),     "12,345");
        assert_eq!(int2str(1_234_567),  "1,234,567");
        assert_eq!(int2str(-9876543),   "-9,876,543");
    }
}

// A tiny `main` so you can run it quickly
fn main() {
    for n in [0, 1, 12, 123, 1234, 12_345, 1_234_567, -9_876_543] {
        println!("{} -> {}", n, int2str(n));
    }
}