use std::f64;

/// This function is a direct translation of the C code's flawed logic.
/// It attempts to add commas to a number string but uses an incorrect algorithm.
///
/// Flaws from the original C code replicated here:
/// 1. The number of commas is calculated incorrectly (`round(len / 3)`).
/// 2. Commas are inserted from the beginning of the string, not the end (e.g., "1,000,000" becomes "1,00,000,0").
fn convert_to_string_buggy(num: i32) -> String {
    // C: char* string = malloc(...); snprintf(string, ...);
    // Rust: Convert the number to a String. This is much simpler and safer.
    let s = num.to_string();

    // C: int numOfCommas = round(strlen(string)/3);
    // Rust: Replicating the flawed calculation. For 100,000 (len 6), this incorrectly yields 2 commas.
    let num_of_commas = (s.len() as f64 / 3.0).round() as usize;
    
    // C: printf("%i\n", numOfCommas);
    // This was a debug print in the C code.
    println!("(Debug) Flawed number of commas calculated: {}", num_of_commas);

    // C: char *newString = malloc(...); for loop to copy and insert commas.
    // Rust: We build a new String. The loop replicates the C code's flawed insertion logic.
    // The original C loop was severely broken and would read past the end of the buffer.
    // This Rust version implements the *intent* of that loop safely.
    let mut new_string = String::with_capacity(s.len() + num_of_commas);
    
    // This loop mimics adding a comma after every 3rd character from the *start*.
    for (i, ch) in s.chars().enumerate() {
        new_string.push(ch);
        // C: if (i % 3 == 0 && i != ...) { newString[i + 1] = ","; }
        // This logic is wrong for number formatting but is what the C code attempted.
        // We also need to avoid adding a comma at the very end.
        if (i + 1) % 3 == 0 && i != s.len() - 1 {
            new_string.push(',');
        }
    }

    new_string
}

fn main() {
    println!("--- Running the Buggy Translation ---");
    // The C code called this with 2000.
    // The flawed logic produces "200,0"
    let result_buggy = convert_to_string_buggy(2000);
    println!("Result for 2000: \"{}\"\n", result_buggy);

    let result_buggy_2 = convert_to_string_buggy(1_000_000);
    println!("Result for 1,000,000: \"{}\"\n", result_buggy_2);
    
    println!("--- Running the Idiomatic and Correct Rust Version ---");
    let result_correct = format_with_commas(2000);
    println!("Result for 2000: \"{}\"\n", result_correct);

    let result_correct_2 = format_with_commas(1_000_000);
    println!("Result for 1,000,000: \"{}\"\n", result_correct_2);
    
    let result_correct_3 = format_with_commas(1234567890);
    println!("Result for 1,234,567,890: \"{}\"\n", result_correct_3);
}

// The correct version is defined below for the main function to use.