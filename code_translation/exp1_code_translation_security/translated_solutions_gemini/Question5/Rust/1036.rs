// No external dependencies are needed. All functionality is in the standard library.

/// Counts the number of digits in an integer.
/// This is a corrected translation of the C function's intent.
/// Note: In idiomatic Rust, for formatting, you would typically convert to a string
/// and use its length, rather than calculating digits manually.
fn num_digits(mut n: i32) -> usize {
    if n == 0 {
        return 1;
    }
    // Use the absolute value for counting digits in negative numbers
    if n < 0 {
        n = n.abs();
    }
    let mut count = 0;
    while n > 0 {
        n /= 10;
        count += 1;
    }
    count
}

/// Takes an integer and returns it as a comma-separated string.
/// This function correctly implements the logic intended by the C code.
fn format_with_commas(num: i32) -> String {
    // 1. Convert the number to a string. This is much easier and safer in Rust.
    let num_str = num.to_string();
    
    // We will build the new string in reverse to make comma placement easy.
    let mut result_rev = String::new();
    let mut count = 0;

    // 2. Iterate over the characters of the number string in reverse.
    for c in num_str.chars().rev() {
        // The '-' sign is handled at the end, so we skip it here.
        if c == '-' {
            continue;
        }

        // 3. Insert a comma every 3 digits (but not at the very start).
        if count > 0 && count % 3 == 0 {
            result_rev.push(',');
        }
        result_rev.push(c);
        count += 1;
    }

    // 4. Reverse the result to get the correct order.
    let mut final_result = result_rev.chars().rev().collect::<String>();

    // 5. If the original number was negative, prepend the '-' sign.
    if num < 0 {
        final_result.insert(0, '-');
    }

    final_result
}

fn main() {
    // Rust allows underscores in numbers for readability.
    let num = 7_000_000;
    let s = format_with_commas(num);
    
    // The println! macro is the standard way to print to the console.
    println!("Original number: {}", num);
    println!("Formatted string: {}", s);

    // Example with a negative number
    let neg_num = -12345;
    println!("\nOriginal number: {}", neg_num);
    println!("Formatted string: {}", format_with_commas(neg_num));
    
    // Example using the num_digits function
    println!("\nNumber of digits in {}: {}", 9876, num_digits(9876));
}