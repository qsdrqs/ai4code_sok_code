/// This function recursively prints a signed integer with thousand separators.
///
/// # Arguments
///
/// * `num` - The `i32` integer to be printed.
fn print_num(num: i32) {
    // Handle negative numbers: print a '-' and recurse with the positive value.
    // Note: This has the same limitation as the C code for i32::MIN,
    // as -i32::MIN overflows. For a direct translation, this is acceptable.
    if num < 0 {
        print!("-");
        print_num(-num);
        return;
    }

    // Base case: If the number is less than 1000, print it directly
    // without any separators.
    if num < 1000 {
        print!("{}", num);
        return;
    }
    
    // Recursive step:
    // 1. Call print_num for the "thousands" part of the number (integer division).
    print_num(num / 1000);
    
    // 2. After the higher-order digits are printed, print a comma followed by
    //    the last three digits. The format specifier `{:03}` pads the number
    //    with leading zeros to ensure it's always three digits long (e.g., 45 becomes "045").
    print!(",{:03}", num % 1000);
}

/// The main entry point of the program.
fn main() {
    // Call the function with the example number.
    print_num(-12345);
    
    // Print a newline at the end for clean terminal output.
    println!();
}