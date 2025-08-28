use std::cmp;

/// Converts an integer to a string with commas separating every three digits.
fn string_it(value: i32) -> String {
    let negative = value < 0;

    // Handle the special case of zero
    if value == 0 {
        return "0".to_string();
    }

    // Convert the absolute value to a string
    let num_str = value.abs().to_string();

    // Reverse the string to group digits from the right
    let reversed: Vec<char> = num_str.chars().rev().collect();

    // Split the reversed string into chunks of 3 characters
    let mut chunks = Vec::new();
    let mut i = 0;
    while i < reversed.len() {
        let end = i + 3;
        let chunk = &reversed[i..cmp::min(end, reversed.len())];
        chunks.push(chunk.to_vec());
        i = end;
    }

    // Reverse the list of chunks to maintain correct digit order
    chunks.reverse();

    // Build the final formatted string
    let mut result = String::new();
    if negative {
        result.push('-');
    }

    for (idx, chunk) in chunks.iter().enumerate() {
        if idx > 0 {
            result.push(',');
        }
        // Reverse each chunk to restore original digit order
        let s: String = chunk.iter().rev().collect();
        result.push_str(&s);
    }

    result
}

fn main() {
    // Example usage
    println!("   {}", string_it(-123457));
}