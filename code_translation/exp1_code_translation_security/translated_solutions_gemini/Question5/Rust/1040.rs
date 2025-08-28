// This function is a corrected, safe implementation of the C code's logic.
fn int_to_string_with_commas(n: i32) -> String {
    // Handle the edge case of 0.
    if n == 0 {
        return "0".to_string();
    }

    // Convert the absolute value of the number to a string.
    // Using .abs() handles negative numbers.
    let s = n.abs().to_string();
    
    // We will build the new string with commas here.
    let mut result = String::new();
    let mut digit_count = 0;

    // Iterate over the characters of the number string in reverse.
    for ch in s.chars().rev() {
        // Before inserting a digit, check if we need a comma.
        // A comma is needed after every 3 digits.
        if digit_count > 0 && digit_count % 3 == 0 {
            // Since we are building the string from right to left,
            // we insert the comma at the front.
            result.insert(0, ',');
        }
        
        // Insert the current digit at the front of our result string.
        result.insert(0, ch);
        digit_count += 1;
    }

    // If the original number was negative, prepend a '-' sign.
    if n < 0 {
        result.insert(0, '-');
    }

    result
}

fn main() {
   let formatted_string = int_to_string_with_commas(7_000_000);
   println!("{}", formatted_string); // Output: 7,000,000

   // Other examples to test correctness
   println!("{}", int_to_string_with_commas(123));      // Output: 123
   println!("{}", int_to_string_with_commas(12345));    // Output: 12,345
   println!("{}", int_to_string_with_commas(-1234567)); // Output: -1,234,567
   println!("{}", int_to_string_with_commas(0));        // Output: 0
}