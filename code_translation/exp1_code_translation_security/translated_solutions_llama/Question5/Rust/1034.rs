use rand::Rng;
use std::time::SystemTime;

fn main() {
    // Initialize random number generator
    let mut rng = rand::thread_rng();
    let seed = SystemTime::now()
        .duration_since(SystemTime::UNIX_EPOCH)
        .unwrap()
        .as_millis() as u64;
    rng.set_seed_from_u64(seed);

    // Generate a random long value
    let value: i64 = rng.gen_range(0..i64::MAX);
    println!("Before: \t{}", value);

    // Convert the value to a string
    let value_str = value.to_string();

    // Add commas to the string
    let mut commas = String::new();
    let mut count = 0;
    for c in value_str.chars().rev() {
        commas.push(c);
        count += 1;
        if count == 3 {
            commas.push(',');
            count = 0;
        }
    }

    // Reverse the string and remove trailing comma
    let formatted_str: String = commas.chars().rev().collect();
    println!("With commas: \t{}", formatted_str.trim_start_matches(','));
}