use std::env;

/// Formats an integer with commas as thousand separators.
fn string_repre(num: i32) -> String {
    let s = num.to_string();

    // If the number has 3 or fewer digits, no commas are needed
    if s.len() <= 3 {
        return s;
    }

    // Reverse the string to group digits from the right
    let reversed_chars: Vec<char> = s.chars().rev().collect();

    // Split into chunks of 3 characters
    let chunks = reversed_chars.chunks(3);

    // Process each chunk: reverse it to restore original order within the group
    let mut groups: Vec<String> = chunks
        .map(|chunk| chunk.iter().rev().collect::<String>())
        .collect();

    // Reverse the list of groups to get the correct left-to-right order
    groups.reverse();

    // Join the groups with commas
    groups.join(",")
}

/// Main function that mimics the behavior of the original C code.
/// It returns the formatted string representing the number of command-line arguments.
fn main() {
    // Get the number of command-line arguments
    let argc = env::args().count() as i32;

    // Format the number with commas
    let result = string_repre(argc);

    // Print the result (as a substitute for returning it from `main`)
    println!("{}", result);

    // If you need to return a static string (e.g., for FFI), you can leak the memory:
    // let _leak = Box::leak(result.into_boxed_str());
}