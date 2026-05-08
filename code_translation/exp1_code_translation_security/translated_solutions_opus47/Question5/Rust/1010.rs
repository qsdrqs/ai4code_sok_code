fn main() {
    println!("   {}", string_it(-123457));
}

// Return a String containing the expansion of the signed int
fn string_it(value: i32) -> String {
    // Even if 64 bit int... only about 18 characters.
    // Add room for sign and 6 commas
    // Far less than 30 total.
    let mut returned = String::with_capacity(30);
    let mut val = value;

    if val < 0 {
        returned.push('-');
        val = -val;
    }

    // Convert to string (equivalent to sprintf)
    let mut s = val.to_string();
    let length = s.len();
    let mut comma_count = (length - 1) / 3;

    let mut end = s.len();

    while comma_count > 0 {
        let start = end - 3;
        // Print substring from 'start' to end (equivalent to printf("%s\n", start))
        println!("{}", &s[start..]);

        // Insert comma at position 'start'
        s.insert(start, ',');

        comma_count -= 1;
        end = start;
    }

    returned.push_str(&s);
    returned
}